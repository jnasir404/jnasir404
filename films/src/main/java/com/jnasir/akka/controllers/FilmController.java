package com.jnasir.akka.controllers;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import com.jnasir.akka.Models.FilmAddMessage;
import com.jnasir.akka.Models.FilmModel;
import com.jnasir.akka.Models.Films;
import com.jnasir.akka.messages.filmMessageBox.FilmGetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.Duration;
import java.util.ArrayList;

@RestController
@RequestMapping("/films")

public class FilmController {

    @Autowired()
    @Qualifier("FilmActor")
    ActorRef filmActor;


    @GetMapping("/seedFilms") // Get All films
    public Object getAllFilmsFromDB() {
        Object result = Patterns.ask(filmActor, new Films(), Duration.ofMillis(2000)).toCompletableFuture().join();
        return result;
    }


    @GetMapping("/") // Get All films
    public ModelAndView getAllFilms() {
        ModelAndView modelAndView = new ModelAndView();
        Object film = Patterns.ask(filmActor, new FilmGetMessage(), Duration.ofMillis(2000)).toCompletableFuture().join();
        modelAndView.addObject("f", film);
        modelAndView.setViewName("index"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}") // Get film by Id
    public ModelAndView getFilm(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        Object film = Patterns.ask(filmActor, new FilmGetMessage(id), Duration.ofMillis(2000)).toCompletableFuture().join();
        modelAndView.addObject("f", film);
        modelAndView.setViewName("film"); // resources/template/register.html
        return modelAndView;
    }

    @GetMapping("/create") // Get All films
    public ModelAndView addFilms() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add_film"); // resources/template/register.html
        return modelAndView;
    }


    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addNewFilm(@Valid Films film, BindingResult bindingResult, ModelMap modelMap) {

        //   public ModelAndView addNewFilm(@Valid ArrayList<FilmModel> films, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        // Check for the validations
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }else{
            Object res = Patterns.ask(filmActor, new FilmAddMessage(film), Duration.ofMillis(2000)).toCompletableFuture().join();
            modelAndView.addObject("successMessage", "Film is added successfully!");
        }



        modelAndView.addObject("film", new FilmModel());
        modelAndView.setViewName("add_film");
        return modelAndView;


    }

 /*
    @RequestMapping(method = RequestMethod.GET, value = "/{id}") // Get film by Id
    public ModelAndView getFilm(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Object film = Patterns.ask(filmActor, new FilmGetMessage(id), Duration.ofMillis(2000)).toCompletableFuture().join();
        modelAndView.addObject("f", film);
        modelAndView.setViewName("film"); // resources/template/register.html
        return modelAndView;
    }





    @RequestMapping(method = RequestMethod.PUT, value = "/add/{id}") // Update a Film
    public Object updateFilm(@RequestBody FilmModel filmModel, @PathVariable String id) throws URISyntaxException {
        try {
            return Patterns.ask(filmActor, new FilmUpdateMessage(Films), Duration.ofMillis(2000)).toCompletableFuture().join();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}") // Delete a Film
    public Object deleteFilm(@RequestBody FilmModel filmModel, @PathVariable String id) throws URISyntaxException {
        try {
            return Patterns.ask(filmActor, new FilmDeleteMessage(filmModel), Duration.ofMillis(2000)).toCompletableFuture().join();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    */

    private <T> T ask(ActorRef actor, Object msg, Class<T> returnTypeClass) {
        return (T) Patterns.ask(actor, msg, Duration.ofMillis(2000)).toCompletableFuture().join();
    }

}
