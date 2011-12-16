package de.schulprojekt.controller;

import de.schulprojekt.bean.UserBean;
import de.schulprojekt.dao.UserDao;
import de.schulprojekt.entities.Group;
import de.schulprojekt.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick Trautmann
 * <p/>
 * Contact: patrick.trautmann@gmail.com
 * Date: 14.12.11
 * Time: 23:09
 */
public class SignupController {

    @Autowired
    private UserDao userDao;

    @Autowired
    @Qualifier("org.springframework.security.authenticationManager")
    protected AuthenticationManager authenticationManager;

    private Logger logger = LoggerFactory.getLogger(SignupController.class);

    private UserBean userBean;

    public SignupController() {
        logger.debug("Init SignupController()");
    }

    public String signUpNewUser() {

        //Fix
        User newUser = new User();

        newUser.setUsername(userBean.getUsername());

        List<Group> groups = new ArrayList<Group>();
        Group userGroup = new Group();
        userGroup.setGroupName("USER");

        groups.add(userGroup);
        newUser.setGroups(groups);

        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hashedPass = encoder.encodePassword(userBean.getPassword(), null);

        newUser.setPassword(hashedPass);

        logger.info("Save new User at Database. Data: {}", userBean.toString());
        userDao.insertNewUser(newUser);

        //Login the newly generated user
        loginUser();

        return "start.html?faces-redirect=true";
    }

    private void loginUser() {

        User newUser = userDao.findByUsername(userBean.getUsername());

        PasswordEncoder encoder = new Md5PasswordEncoder();
        String hashedPass = encoder.encodePassword(userBean.getPassword(), null);

        UsernamePasswordAuthenticationToken usernameAndPassword =
                new UsernamePasswordAuthenticationToken(
                        newUser.getUsername(), userBean.getPassword(), newUser.getAuthorities());
        usernameAndPassword.setDetails(newUser);

        // Authenticate, just to be sure
        Authentication auth = authenticationManager.authenticate(usernameAndPassword);

        // Place the new Authentication object in the security context.
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
