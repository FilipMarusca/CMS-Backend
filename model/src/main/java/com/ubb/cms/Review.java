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
@Table(name = "review")
public class Review implements Serializable {

    @EmbeddedId
    private UserPaperEmb userPaper;

    @Column(name = "status")
    private ReviewStatus status;

    @Column(name = "comment")
    private String comment;

    public Review(UserPaperEmb user_paper, ReviewStatus status, String comment) {
        this.userPaper = user_paper;
        this.status = status;
        this.comment = comment;
    }

    public Review() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "userPaper=" + userPaper +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                '}';
    }

    public UserPaperEmb getUserPaper() {
        return userPaper;
    }

    public void setUserPaper(UserPaperEmb user_paper) {
        this.userPaper = user_paper;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Review review = (Review) o;

        return getUserPaper() != null ? getUserPaper().equals(review.getUserPaper()) : review.getUserPaper() == null;
    }

    @Override
    public int hashCode() {
        return getUserPaper() != null ? getUserPaper().hashCode() : 0;
    }




}
