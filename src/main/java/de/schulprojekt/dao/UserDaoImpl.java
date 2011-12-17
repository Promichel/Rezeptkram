package de.schulprojekt.dao;

import de.schulprojekt.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Patrick Trautmann
 * <p/>
 * Contact: patrick.trautmann@gmail.com
 * Date: 16.12.11
 * Time: 20:35
 */
@Repository(value = "userDao")
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findByUsername(String username) {

        Query query = em.createQuery("select u from User u where u.username = :username");
        query.setParameter("username", username);

        return (User) query.getSingleResult();
    }

    @Override
    @Transactional
    public void insertNewUser(User user) {
        em.persist(user);
    }
}
