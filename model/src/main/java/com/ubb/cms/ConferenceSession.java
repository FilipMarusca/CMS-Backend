package com.ubb.cms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Entity
@Table(name = "session")
public class ConferenceSession implements Serializable {

    @Id
    private int     id;
    @ManyToOne
    @JoinColumn(name = "edition_id", referencedColumnName = "id")

    @NotNull
    private Edition edition;

    @NotNull
    private Date    date;

    @Size(min = 3, max = 255)
    private String  location;

    public ConferenceSession(int id, Edition edition, Date date, String location) {
        this.id = id;
        this.edition = edition;
        this.date = date;
        this.location = location;
    }

    public ConferenceSession() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConferenceSession that = (ConferenceSession) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
