package com.ubb.cms;

import javax.persistence.*;
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
    private Edition edition;
    private Date    date;
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
}
