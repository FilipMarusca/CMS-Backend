package com.ubb.cms;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alexandra Muresan on 4/10/2017.
 */
@Entity
@javax.persistence.Table(name = "edition")
public class Edition implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "conference_id", referencedColumnName = "id")
    private Conference conference;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "paperSubmissionDeadline")
    private Date   paperSubmissionDeadline;

    @NotNull
    @Column(name = "finalDeadline")
    private Date   finalDeadline;

    @NotNull
    @Column(name = "beginningDate")
    private Date   beginningDate;

    @NotNull
    @Column(name = "endingDate")
    private Date   endingDate;

    public Edition(int id, Conference conference, Date beginningDate, Date endingDate, String name, Date paperSubmissionDeadline, Date finalDeadline) {
        this.id = id;
        this.conference = conference;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.name = name;
        this.paperSubmissionDeadline = paperSubmissionDeadline;
        this.finalDeadline = finalDeadline;
    }

    public Edition(Conference conference, Date beginningDate, Date endingDate, String name, Date paperSubmissionDeadline, Date finalDeadline) {
        this.conference = conference;
        this.beginningDate = beginningDate;
        this.endingDate = endingDate;
        this.name = name;
        this.paperSubmissionDeadline = paperSubmissionDeadline;
        this.finalDeadline = finalDeadline;
    }

    public Edition() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPaperSubmissionDeadline() {
        return paperSubmissionDeadline;
    }

    public void setPaperSubmissionDeadline(Date paperSubmissionDeadline) {
        this.paperSubmissionDeadline = paperSubmissionDeadline;
    }

    public Date getFinalDeadline() {
        return finalDeadline;
    }

    public void setFinalDeadline(Date finalDeadline) {
        this.finalDeadline = finalDeadline;
    }


    @Override
    public String toString() {
        return "Edition{" +
                "id=" + id +
                ", conference=" + conference +
                ", name='" + name + '\'' +
                ", paperSubmissionDeadline=" + paperSubmissionDeadline +
                ", finalDeadline=" + finalDeadline +
                ", beginningDate=" + beginningDate +
                ", endingDate=" + endingDate +
                '}';
    }
}
