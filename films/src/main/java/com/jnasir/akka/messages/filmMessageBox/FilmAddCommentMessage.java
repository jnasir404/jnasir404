package com.jnasir.akka.messages.filmMessageBox;

import com.jnasir.akka.Models.CommentsModel.Comments;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class FilmAddCommentMessage {
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

    public FilmAddCommentMessage(){}
    public FilmAddCommentMessage(Comments comments){
        super();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.id = comments.getId();
        this.film_id = comments.getFilm_id();
        this.user_id = auth.getName();
        this.comment = comments.getComment();
    }

}
