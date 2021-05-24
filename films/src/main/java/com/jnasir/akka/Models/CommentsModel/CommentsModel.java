package com.jnasir.akka.Models.CommentsModel;


import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Service
public class CommentsModel {

    private int id;
    private String film_id;
    private String user_id;
    private String comment;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilm_id() {
        return film_id;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentsModel(){}
    public CommentsModel(int id, String film_id, String user_id, String comment){
        super();
        this.id = id;
        this.film_id = film_id;
        this.user_id = user_id;
        this.comment = comment;
    }

}