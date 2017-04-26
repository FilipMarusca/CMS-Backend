package com.ubb.cms;

import com.ubb.cms.utils.ReviewStatus;
import com.ubb.cms.utils.UserPaperEmb;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Entity
@Table(name="review")
public class Review implements Serializable {

    @EmbeddedId
    private UserPaperEmb user_paper;

    @Column(name="status")
    private ReviewStatus status;

    @Column(name="comment")
    private String comment;

    public Review(UserPaperEmb user_paper, ReviewStatus status, String comment) {
        this.user_paper = user_paper;
        this.status = status;
        this.comment = comment;
    }

    public UserPaperEmb getUser_paper() {
        return user_paper;
    }

    public void setUser_paper(UserPaperEmb user_paper) {
        this.user_paper = user_paper;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
