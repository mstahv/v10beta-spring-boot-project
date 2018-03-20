package com.example.demo;

import com.vividsolutions.jts.geom.Point;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import java.util.Date;

/**
 * Created by mattitahvonenitmill on 12/05/16.
 */
@Entity
public class Person extends AbstractEntity {

    private String name;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;
    @Lob
    private Point location;

    public Person() {}
    
    public Person(String name, String mail) {
        this.name = name;
        this.email = mail;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", email=" + email + ", birthday=" + birthday + ", location=" + location + '}';
    }

}
