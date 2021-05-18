package com.jnasir.akka.Models;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

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

    @NotNull(message="Name is compulsory")
    @Length(min=1, message="Name must be more that 1 characters")
    @Pattern(regexp="^[A-Za-z]*$", message="Name can have letters only")
    @Column(name = "name")
    private String name;

    @NotNull(message="Description is compulsory")
    @Column(name = "description",length = 2000)
    @Length(min=50, message="Description should be atleast 50 characters")
    private String description;

   // @NotNull(message="Film Year is compulsory")
    //@Pattern(regexp="^[0-9]*$", message="year can contain numbers only")
    //@Length(min=4, max=4, message="Year should be atleast 4 numbers")
    @Column(name = "release_Date")
    private String release_Date;

    @NotNull(message="Rating is compulsory, Only 1-5")
    @Pattern(regexp="(^$|[1-5]{1})", message="Rating should be between 1-5")
    @Column(name = "rating")
    private String rating;

   // @NotNull(message="Ticket price is compulsory")
    @Column(name = "ticket_price")
    private String ticket_price;

    @NotNull(message="Country is compulsory")
    @Pattern(regexp="^[A-Za-z]*$", message="Country can have letters only\"")
    @Column(name = "country")
    private String country;

    @NotNull(message="Genre is compulsory")
    @Pattern(regexp="^[A-Za-z0-9]*$", message="Genre can contain only letters")
    @Column(name = "genre")
    private String genre;

    //@NotNull(message="Photo is compulsory")
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
        return ticket_price;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticket_price = ticketPrice;
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
        this.ticket_price = ticketPrice;
        this.country = country;
        this.genre = genre;
        this.photo = photo;
    }
}
