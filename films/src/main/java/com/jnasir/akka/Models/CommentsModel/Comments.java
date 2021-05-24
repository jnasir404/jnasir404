package com.jnasir.akka.Models.CommentsModel;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "film_id")
    private String film_id;

    @Column(name = "user_id")
    private String user_id;


    @NotNull(message = "Comment is compulsory")
    @Column(name = "comment", length = 2000)
    @Length(min = 3, message = "comment should be atleast 3 characters")
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

    public Comments(){}
    public Comments(int id, String film_id, String user_id, String comment){
        super();
        this.id = id;
        this.film_id = film_id;
        this.user_id = user_id;
        this.comment = comment;
    }

}