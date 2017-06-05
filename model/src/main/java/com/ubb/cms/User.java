package com.ubb.cms;

import com.ubb.cms.utils.UserTag;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    //TODO proper validation messages for all of the entities
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int     id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 5, max = 32)
    @Column(name = "username")
    private String  username;

    @NotEmpty
    @Size(min = 5, max = 32)
    @Column(name = "password")
    private String  password;

    @NotEmpty
    @Email
    @Column(name = "email")
    private String  email;

    @NotEmpty
    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String  name;

    @NotEmpty
    @Size(min = 3, max = 50)
    @Column(name = "surname")
    private String  surname;

    @NotNull
    @Column(name = "tag")
    private UserTag tag;

    public User(int id, String username, String password, String email, String name, String surname, UserTag tag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.tag = tag;
    }

    public User(String username, String password, String email, String name, String surname, UserTag tag) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.tag = tag;
    }

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserTag getTag() {
        return tag;
    }

    public void setTag(UserTag tag) {
        this.tag = tag;
    }


    @Override
    public String toString() {
        return String.format("%s %s", getName(), getSurname());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
