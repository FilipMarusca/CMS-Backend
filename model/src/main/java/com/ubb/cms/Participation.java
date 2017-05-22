package com.ubb.cms;

import com.ubb.cms.utils.UserEditionEmb;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Entity
@Table(name = "participation")
public class Participation implements Serializable {

    @EmbeddedId
    private UserEditionEmb participant;

    @Column(name = "paid")
    private boolean paid;

    public Participation(UserEditionEmb participant, boolean paid) {
        this.participant = participant;
        this.paid = paid;
    }

    public Participation() {
    }

    public UserEditionEmb getParticipant() {
        return participant;
    }

    public void setParticipant(UserEditionEmb participant) {
        this.participant = participant;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
