package de.schulprojekt.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by Patrick Trautmann
 * <p/>
 * Contact: patrick.trautmann@gmail.com
 * Date: 16.12.11
 * Time: 20:19
 */
@Entity
public class Group implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic
    private String groupName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String getAuthority() {
        return groupName;
    }
}
