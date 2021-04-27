package com.jnasir.akka.Models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Films {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "film_id")
    private String film_id;

    @Column(name = "userid")
    private String userid;
    @Column(name = "name")
    private String name;
    @Column(name = "description",length = 2000)
    private String description;
    @Column(name = "release_Date")
    private String release_Date;
    @Column(name = "rating")
    private String rating;
    @Column(name = "ticketPrice")
    private String ticketPrice;
    @Column(name = "country")
    private String country;
    @Column(name = "genre")
    private String genre;
    @Column(name = "photo")
    private String photo;


    public int getId() {
        return id;
    }

    public String getFilm_id() {
        return film_id;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setId(int id) {
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

    public Films(){}

    public Films(String film_id,String userid, String name, String description, String release_Date, String rating, String ticketPrice, String country, String genre, String photo) {
        super();
        this.film_id = film_id;
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
}
