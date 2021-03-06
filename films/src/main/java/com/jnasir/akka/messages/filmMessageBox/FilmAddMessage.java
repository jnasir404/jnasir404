package com.jnasir.akka.Models;


import com.jnasir.akka.Models.FilmModels.Films;

public class FilmAddMessage {

    private Integer id;
    private String userid;
    private String name;
    private String description;
    private String release_Date;
    private String rating;
    private String ticketPrice;
    private String country;
    private String genre;
    private String photo;



    public Integer getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease_Date() {
        return release_Date;
    }

    public void setRelease_Date(String release_Date) {
        this.release_Date = release_Date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public FilmAddMessage(){}
    public FilmAddMessage(Integer id, String userid, String name, String description, String release_Date, String rating, String ticketPrice, String country, String genre, String photo) {
        super();
        this.id = id;
        this.userid= userid;
        this.name = name;
        this.description = description;
        this.release_Date = release_Date;
        this.rating = rating;
        this.ticketPrice = ticketPrice;
        this.country = country;
        this.genre = genre;
        this.photo = photo;
    }

    public FilmAddMessage(Films film) {
        super();
        this.id = film.getId();
        this.userid= film.getUserid();
        this.name = film.getName();
        this.description = film.getDescription();
        this.release_Date = film.getRelease_Date();
        this.rating = film.getRating();
        this.ticketPrice = film.getTicketPrice();
        this.country = film.getCountry();
        this.genre = film.getGenre();
        this.photo = film.getPhoto();

    }

}
