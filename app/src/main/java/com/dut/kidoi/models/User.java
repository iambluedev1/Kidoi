package com.dut.kidoi.models;

import com.dut.kidoi.repositories.FirebaseRepository;

public class User {

    private String login;
    private String email;
    private final String documentId;

    public User(String login, String email, String documentId) {
        this.email = email;
        this.login = login;
        this.documentId = documentId;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void save(){
        FirebaseRepository.getInstance().updateUser(this);
    }
}
