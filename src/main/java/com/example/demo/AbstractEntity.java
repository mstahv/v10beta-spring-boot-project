package com.example.demo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Matti Tahvonen
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private int version;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(this.id == null) {
            return false;
        }

        if (obj instanceof AbstractEntity && obj.getClass().equals(getClass())) {
            return this.id.equals(((AbstractEntity) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
}