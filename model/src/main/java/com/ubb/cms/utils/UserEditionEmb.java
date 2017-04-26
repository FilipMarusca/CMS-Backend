package com.ubb.cms.utils;

import com.ubb.cms.Edition;
import com.ubb.cms.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Embeddable
public class UserEditionEmb implements Serializable{

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name="edition_id", referencedColumnName = "id")
    private Edition edition;


    public UserEditionEmb(User user, Edition edition)
    {
        this.user = user;
        this.edition = edition;
    }
}
