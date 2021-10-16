package com.whitewolf9.rest.webservices.restfulwebservices.users;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;


public class User {

    private Integer id;

    @Size(min = 2, message = "Name should at least have 2 characters.")
    private String name;

    @Past
    private Date birthdate;


    public User(Integer id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
