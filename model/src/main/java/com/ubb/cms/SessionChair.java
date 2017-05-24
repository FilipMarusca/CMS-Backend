package com.ubb.cms;

import com.ubb.cms.utils.UserEditionEmb;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Entity
@Table(name = "sessionChair")
public class SessionChair implements Serializable {

    @EmbeddedId
    private UserEditionEmb chair;

    public SessionChair(UserEditionEmb chair) {
        this.chair = chair;
    }

    public SessionChair() {
    }

    public UserEditionEmb getChair() {
        return chair;
    }

    public void setChair(UserEditionEmb chair) {
        this.chair = chair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SessionChair that = (SessionChair) o;

        return getChair() != null ? getChair().equals(that.getChair()) : that.getChair() == null;
    }

    @Override
    public int hashCode() {
        return getChair() != null ? getChair().hashCode() : 0;
    }
}
