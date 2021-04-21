package com.jnasir.akka.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.jnasir.akka.Models.FilmModel;
import com.jnasir.akka.messages.filmMessageBox.FilmAddMessage;
import com.jnasir.akka.messages.filmMessageBox.FilmDeleteMessage;
import com.jnasir.akka.messages.filmMessageBox.FilmGetMessage;
import com.jnasir.akka.messages.filmMessageBox.FilmUpdateMessage;
import com.jnasir.akka.messages.userMessageBox.UserGetMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilmActor extends AbstractActor {
    @Autowired
    private ActorRef sender;

    private List<FilmModel> films = new ArrayList<>(Arrays.asList(
            new FilmModel("id1", "userid1", "Working Woman", "Working Woman is a 2018 Israeli drama film directed by Michal Aviad.[1] It was screened in the Contemporary World Cinema section at the 2018 Toronto International Film Festival.", "release_Date", "5", "ticketPrice", "country", "genre", "thumb4.jpg"),
            new FilmModel("id2", "userid1", "Brighton Rock", "Brighton Rock is a 2010 British crime film written and directed by Rowan Joffé and loosely based on Graham Greene's 1938 novel of the same name. The film stars Sam Riley, Andrea Riseborough, Andy Serkis, John Hurt, Sean Harris and Helen Mirren.", "release_Date", "rating", "ticketPrice", "country", "genre", "thumb5.jpg"),
            new FilmModel("id3", "userid2", "Total Recall", "When a man goes in to have virtual vacation memories of the planet Mars implanted in his mind, an unexpected and harrowing series of events forces him to go to the planet for real - or is he?", "release_Date", "rating", "ticketPrice", "country", "genre", "thumb6.jpg"),
            new FilmModel("id4", "userid2", "The City of Gold", "An anguished media magnate, Jonathan Davenport, accompanies his estranged lover to the Peruvian Amazon in pursuit of a reclusive artist living in rebel occupation. Despite their philanthropic intentions, the mission proves to be the harbinger of something dark and ominous rooted deep within Jonathan.", "release_Date", "rating", "ticketPrice", "country", "genre", "thumb7.jpg"),
            new FilmModel("id5", "userid3", "21 Bridges", "An embattled NYPD detective is thrust into a citywide manhunt for a pair of cop killers after uncovering a massive and unexpected conspiracy.", "release_Date", "rating", "ticketPrice", "country", "genre", "thumb8.jpg"),
            new FilmModel("id6", "userid3", "Star Wars", "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.", "release_Date", "rating", "ticketPrice", "country", "genre", "thumb9.jpg"),
            new FilmModel("id7", "userid4", "Captain Marvel", "Carol Danvers becomes one of the universe's most powerful heroes when Earth is caught in the middle of a galactic war between two alien races.", "release_Date", "rating", "ticketPrice", "country", "genre", "thumb2.jpg"),
            new FilmModel("id8", "userid4", "Guardians of the Galaxy", "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.", "release_Date", "rating", "ticketPrice", "country", "genre", "thumb1.jpg")

    ));

    public static Props propsDefault() {
        return Props.create(FilmActor.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(FilmGetMessage.class, msg -> {
                    sender = sender();
                    Object result = getFilms(msg);
                    sender.tell(result, self());
                })
                .match(FilmAddMessage.class, msg -> {
                    sender = sender();
                    FilmModel result = addFilm(msg);
                    sender.tell(result, self());
                })
                .match(FilmUpdateMessage.class, msg -> {
                    sender = sender();
                    FilmModel result = updateFilm(msg);
                    sender.tell(result, self());
                })
                .match(FilmDeleteMessage.class, msg -> {
                    sender = sender();
                    List<FilmModel> result = deleteFilm(msg);
                    sender.tell(result, self());
                })
                .build();
    }

    //Add User
    private FilmModel addFilm(FilmAddMessage msg) {
        FilmModel film = new FilmModel();
        film.setId(""+films.size()+1);
        film.setUserid("WebUser");
        film.setName(msg.getName());
        film.setDescription(msg.getDescription());
        film.setRelease_Date(msg.getRelease_Date());
        film.setRating(msg.getRating());
        film.setTicketPrice("10$");
        film.setCountry(msg.getCountry());
        film.setGenre(msg.getGenre());
        film.setPhoto("thumb4.jpg");

        films.add(film);
        return film;
    }

    //Update user
    private FilmModel updateFilm(FilmUpdateMessage msg) {
        FilmModel film = new FilmModel();
        film.setId(msg.getId());
        film.setUserid(msg.getUserid());
        film.setName(msg.getName());
        film.setDescription(msg.getDescription());
        film.setRelease_Date(msg.getRelease_Date());
        film.setRating(msg.getRating());
        film.setTicketPrice(msg.getTicketPrice());
        film.setCountry(msg.getCountry());
        film.setGenre(msg.getGenre());
        film.setPhoto(msg.getPhoto());


        for (int i = 0; i < films.size(); i++) {
            FilmModel f = films.get(i);
            if (f.getId().equals(msg.getId())) {
                films.set(i, film);
            }
        }
        return film;
    }

    //Delete user
    private List<FilmModel> deleteFilm(FilmDeleteMessage msg) {
        for (int i = 0; i < films.size(); i++) {
            FilmModel f = films.get(i);
            if (f.getId().equals(msg.getId())) {
                films.remove(f);
            }
        }
        return films;
    }

    //Get User
    private Object getFilms(FilmGetMessage msg) {
        Object filmObj = new FilmModel();
        if (msg.getId() == null) {
            return films;
        } else {
            for (int i = 0; i < films.size(); i++) {
                FilmModel film = films.get(i);
                if (film.getId().equals(msg.getId().split("-")[0])) {
                    filmObj = film;
                }
            }
            return filmObj;
        }
    }
}
