package com.skylabase.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

/**
 * Model class for system Roles
 */
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private Collection<Privilege> privileges;

    /**
     * Get the id of this {@code Role}
     *
     * @return role id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set {@code Role} id
     *
     * @param id the role id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name of this {@code Role}.
     *
     * @return the {@code Role} name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of {@code Role}
     *
     * @param name the role name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get users that have this {@code Role}
     *
     * @return the users that have this role.
     */
    public Collection<User> getUsers() {
        return users;
    }

    /**
     * Set users to this {@code Role}
     *
     * @param users
     */
    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    /**
     * Get all {@code Privileges} that this role has.
     *
     * @return privileges
     */
    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    /**
     * Set {@code Privileges} that this {@code Role} has
     *
     * @param privileges the privileges to set
     */
    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }

    /**
     * Add a new privilege to this role.
     *
     * @param privilege the privilege to be added
     */
    public void addPrivilege(Privilege privilege) {
        if (privileges == null) {
            privileges = new HashSet<>();
        }
        if (privilege != null && !containsPrivilege(privilege)) {
            privileges.add(privilege);
        }
    }

    /**
     * Remove a privilege from this {@code Role}
     *
     * @param privilege
     */
    public void removePrivilege(Privilege privilege) {
        if (privileges != null) {
            privileges.remove(privilege);
        }
    }

    /**
     * Check if this {@code Role} has a privilege
     *
     * @return the privilege to check for
     */
    public boolean hasPrivilege(Privilege privilege) {
        return containsPrivilege(privilege);
    }

    /**
     * Get a string representation of {@code Role}
     *
     * @return role name
     */
    public String toString() {
        return getName();
    }

    private boolean containsPrivilege(Privilege privilege) {
        for (Privilege p : privileges) {
            if (p.getName().equals(privilege.getName()))
                return true;
        }
        return false;
    }
}
