package de.schulprojekt.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Patrick Trautmann
 * <p/>
 * Contact: patrick.trautmann@gmail.com
 * Date: 16.12.11
 * Time: 20:04
 */
@Entity
@Transactional(value = "transactionManager")
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
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
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(groups.size());

        for (GrantedAuthority grantedAuthority : groups) {
            authorities.add(grantedAuthority);
        }

        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
