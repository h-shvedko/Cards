package com.cards.shvedko.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class Users extends A_Models implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_visible", length = 1, nullable = false)
    private int isVisible;

    @SuppressWarnings("UnusedDeclaration")
    public Users() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public Users(Integer id, String name, String password, int isVisible) {
        this.setId(id);
        this.setName(name);
        this.setPassword(password);
        this.setIsVisible(isVisible);
    }

    @SuppressWarnings("UnusedDeclaration")
    public Users(String name, String password, int isVisible) {
        this.setId(-1);
        this.setName(name);
        this.setPassword(password);
        this.setIsVisible(isVisible);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
