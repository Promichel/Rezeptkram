package de.schulprojekt.services.security;

import de.schulprojekt.dao.UserDao;
import de.schulprojekt.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Patrick Trautmann
 * <p/>
 * Contact: patrick.trautmann@gmail.com
 * Date: 16.12.11
 * Time: 20:09
 */
public class DatabaseUserService implements UserDetailsService {

    @Autowired
    UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        User user;

        try {
            user = dao.findByUsername(username);
        } catch (EmptyResultDataAccessException e) {

            throw new UsernameNotFoundException("Username don't exists!");

        }

        return user;
    }
}
