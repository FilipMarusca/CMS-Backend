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
}
