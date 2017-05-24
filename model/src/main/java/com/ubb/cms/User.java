package com.ubb.cms;

import com.ubb.cms.utils.UserTag;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int     id;
    @Column(name = "username")
    private String  username;
    @Column(name = "password")
    private String  password;
    @Column(name = "email")
    private String  email;
    @Column(name = "name")
    private String  name;
    @Column(name = "surname")
    private String  surname;
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
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", email='" + email + '\'' + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", tag='" + tag + '\'' + '}';
    }
}
