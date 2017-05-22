package com.ubb.cms;

import com.ubb.cms.utils.PaperStatus;

import javax.persistence.*;

/**
 * Created by Alexandra Muresan on 4/11/2017.
 */
@Entity
@Table(name="paper")
public class Paper {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="session_id", referencedColumnName = "id")
    private ConferenceSession session;

    @ManyToOne
    @JoinColumn(name="author_id", referencedColumnName = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name="edition_id", referencedColumnName = "id")
    private Edition edition;

    @Column(name="status")
    private PaperStatus status;

    @Column(name="title")
    private String title;

    @Column(name="topic")
    private String topic;

    public Paper(int id, ConferenceSession session, User author, Edition edition, PaperStatus status, String title, String topic) {
        this.id = id;
        this.session = session;
        this.author = author;
        this.edition = edition;
        this.status = status;
        this.title = title;
        this.topic = topic;
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
}
