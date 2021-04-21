package com.jnasir.akka.messages.userMessageBox;

import java.util.Arrays;
import java.util.List;

public class UserGetMessage {
    private String id;
    private String name;
    private String email;

    public UserGetMessage() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public UserGetMessage(String id){
        this.id = id;
    }

    public UserGetMessage(String id, String name, String email, String password) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
