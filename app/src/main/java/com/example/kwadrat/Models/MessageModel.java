package com.example.kwadrat.Models;

public class MessageModel {

    String message_from, about_flat, message, logged_user_email;

    public MessageModel(String message_from, String about_flat, String message, String logged_user_email) {
        this.message_from = message_from;
        this.about_flat = about_flat;
        this.message = message;
        this.logged_user_email = logged_user_email;
    }

    public String getMessage_from() {
        return message_from;
    }

    public String getAbout_flat() {
        return about_flat;
    }

    public String getMessage() {
        return message;
    }

    public String getLogged_user_email() {
        return logged_user_email;
    }
}
