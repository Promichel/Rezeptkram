package de.schulprojekt.dao;

import de.schulprojekt.entities.User;

/**
 * Created by Patrick Trautmann
 * <p/>
 * Contact: patrick.trautmann@gmail.com
 * Date: 16.12.11
 * Time: 20:07
 */
public interface UserDao {

    public User findByUsername(String username);

    public void insertNewUser(User user);

}
