package de.schulprojekt.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Patrick Trautmann
 * <p/>
 * Contact: patrick.trautmann@gmail.com
 * Date: 16.12.11
 * Time: 20:04
 */
@Entity
@Table(name = "User")
public class User implements UserDetails {
    @GeneratedValue
    @Id
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    private String username;

    public String getUsername() {
        return username;
    }

    @OneToOne(cascade = CascadeType.ALL)
    private UserGroup userGroup;

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup groups) {
        this.userGroup = groups;
    }

    @Override
    public boolean isAccountNonExpired() {
        //todo: implement
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //todo: implement
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //todo: implement
        return true;
    }

    @Override
    public boolean isEnabled() {
        //todo: implement
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    private String password;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(userGroup);

        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
