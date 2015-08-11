package com.sokirka.task.shared;

import java.io.Serializable;

/**
 * @author Eugine Sokirka.
 */
public class User implements Serializable {
    private long id;
    private String name;
    private String surName;
    private String email;
    private Role role;

    public User(long id, String name, String surName, String email, Role role) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.role = role;
    }

    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
