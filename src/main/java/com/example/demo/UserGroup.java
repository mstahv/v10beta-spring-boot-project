package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by mattitahvonenitmill on 12/05/16.
 */
@Entity
public class UserGroup extends AbstractEntity {

    private String name;

    @ManyToMany
    private List<Person> persons;

    public UserGroup() {}
    
    public UserGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "UserGroup{" + "name=" + name +'}';
    }
    
}
