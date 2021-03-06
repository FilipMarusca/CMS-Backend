package com.ubb.cms;

import com.ubb.cms.utils.PaperStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Entity
@Table(name = "paper")
public class Paper implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private ConferenceSession session;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "edition_id", referencedColumnName = "id")
    private Edition edition;

    @Column(name = "status")
    private PaperStatus status;

    @Column(name = "title")
    private String title;

    @Column(name = "topic")
    private String topic;

    @Column(name = "pdf")
    @Lob
    private byte[] paperPDF;

    @Column(name = "pdf_summary")
    @Lob
    private byte[] summaryPDF;

    public Paper(int id, ConferenceSession session, User author, Edition edition, PaperStatus status, String title, String topic, byte[] paperPDF, byte[] summaryPDF) {
        this.id = id;
        this.session = session;
        this.author = author;
        this.edition = edition;
        this.status = status;
        this.title = title;
        this.topic = topic;
        this.paperPDF = paperPDF;
        this.summaryPDF = summaryPDF;
    }

    public Paper(User author, Edition edition, PaperStatus status, String title, String topic, byte[] paperPDF, byte[] summaryPDF) {
        this.author = author;
        this.edition = edition;
        this.status = status;
        this.title = title;
        this.topic = topic;
        this.paperPDF = paperPDF;
        this.summaryPDF = summaryPDF;
    }


    public Paper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ConferenceSession getSession() {
        return session;
    }

    public void setSession(ConferenceSession session) {
        this.session = session;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public PaperStatus getStatus() {
        return status;
    }

    public void setStatus(PaperStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public byte[] getPaperPDF() {
        return paperPDF;
    }

    public void setPaperPDF(byte[] paperPDF) {
        this.paperPDF = paperPDF;
    }

    public byte[] getSummaryPDF() {
        return summaryPDF;
    }

    public void setSummaryPDF(byte[] summaryPDF) {
        this.summaryPDF = summaryPDF;
    }

    @Override
    public String toString() {
        return id+title+topic;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Paper paper = (Paper) o;

        return getId() == paper.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }




}
