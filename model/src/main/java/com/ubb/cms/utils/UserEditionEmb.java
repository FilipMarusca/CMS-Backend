package com.ubb.cms.utils;

import com.ubb.cms.Edition;
import com.ubb.cms.User;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Embeddable
public class UserEditionEmb implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User    user;
    @ManyToOne
    @JoinColumn(name = "edition_id", referencedColumnName = "id")
    private Edition edition;


    public UserEditionEmb(User user, Edition edition) {
        this.user = user;
        this.edition = edition;
    }

    public UserEditionEmb() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserEditionEmb that = (UserEditionEmb) o;

        if (!user.equals(that.user)) {
            return false;
        }
        return edition.equals(that.edition);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + edition.hashCode();
        return result;
    }
}
