package com.ubb.cms.utils;

/**
 * Created by Raul on 02/05/2017.
 */
public enum UserTag {
    Admin("Admin"),
    Reviewer("Reviewer"),
    Author("Author"),
    SessionChair("Session Chair"),
    Participant("Participant")
    ;

    private String name;

    UserTag(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return this.name;
    }
}