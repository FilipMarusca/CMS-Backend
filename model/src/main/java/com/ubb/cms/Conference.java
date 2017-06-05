package com.ubb.cms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Alexandra Muresan on 4/10/2017.
 */
@Entity(name = "conference")
public class Conference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int    id;

    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    public Conference(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public Conference(String name) {
        this.name = name;
    }

    public Conference() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Conference that = (Conference) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
