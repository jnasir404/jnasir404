package com.jnasir.akka.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.jnasir.akka.Models.CommentsModel.Comments;
import com.jnasir.akka.Models.FilmAddMessage;
import com.jnasir.akka.Models.FilmModels.Films;
import com.jnasir.akka.messages.filmMessageBox.FilmAddCommentMessage;
import com.jnasir.akka.messages.filmMessageBox.FilmGetCommentMessage;
import com.jnasir.akka.messages.filmMessageBox.FilmGetMessage;
import com.jnasir.akka.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FilmActor extends AbstractActor {
    @Autowired
    private ActorRef sender;

    private final FilmRepository filmRepository;

    private List<Films> films = new ArrayList<>(Arrays.asList(
            new Films("id1", "userid1", "Updated Working Woman", "Working Woman is a 2018 Israeli drama film directed by Michal Aviad.[1] It was screened in the Contemporary World Cinema section at the 2018 Toronto International Film Festival.", "2020-11-23", "5", "10$", "country", "genre", "thumb4.jpg")
    ));

    public FilmActor() {
        filmRepository = new FilmRepository();

    }

    public static Props propsDefault() {
        return Props.create(FilmActor.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()

                .match(Films.class, msg -> {
                    sender = sender();
                    boolean result = seedFilms(msg);
                    sender.tell(result, self());
                })

                .match(FilmGetMessage.class, msg -> {
                    sender = sender();
                    Object result = getFilms(msg);
                    sender.tell(result, self());
                })

                .match(FilmAddMessage.class, msg -> {
                    sender = sender();
                    boolean result = addFilm(msg);
                    sender.tell(result, self());
                })
                .match(FilmAddCommentMessage.class, msg -> {
                    sender = sender();
                    boolean result = addComment(msg);
                    sender.tell(result, self());
                })
                .match(FilmGetCommentMessage.class, msg -> {
                    sender = sender();
                    Object result = getComment(msg);
                    sender.tell(result, self());
                })
                .build();
    }

    //Add seed films to DB
    private boolean seedFilms(Films msg) {
        return filmRepository.create(films);
    }

    //Get Films
    private Object getFilms(FilmGetMessage msg) {
        if (msg.getId() == null) {
            return filmRepository.getFilms();
        } else {
            return filmRepository.find(msg.getId().toString());
        }
    }


    //Get Comments
    private Object getComment(FilmGetCommentMessage msg) {
        if (msg.getFilm_id() == null) {
            return filmRepository.getComments();
        } else {
            return filmRepository.findFilmComment(msg.getFilm_id());
        }
    }

    //Add Film
    private boolean addFilm(FilmAddMessage msg) {

        Films film = new Films();
        film.setFilm_id("WebUser");
        film.setUserid("WebUser");
        film.setName(msg.getName());
        film.setDescription(msg.getDescription());
        film.setRelease_Date(msg.getRelease_Date());
        film.setRating(msg.getRating());
        film.setTicketPrice("10$");
        film.setCountry(msg.getCountry());
        film.setGenre(msg.getGenre());
        film.setPhoto("thumb4.jpg");

        return filmRepository.addFilm(film);

    }

    private boolean addComment(FilmAddCommentMessage msg) {
        Comments comment = new Comments();
        comment.setFilm_id(msg.getFilm_id());
        comment.setUser_id(msg.getUser_id());
        comment.setComment(msg.getComment());
        return filmRepository.createComment(comment);
    }

}
