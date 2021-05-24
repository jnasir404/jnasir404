package com.jnasir.akka.controllers;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import com.jnasir.akka.Models.CommentsModel.Comments;
import com.jnasir.akka.Models.FilmAddMessage;
import com.jnasir.akka.Models.FilmModels.FilmModel;
import com.jnasir.akka.Models.FilmModels.Films;
import com.jnasir.akka.messages.filmMessageBox.FilmAddCommentMessage;
import com.jnasir.akka.messages.filmMessageBox.FilmGetCommentMessage;
import com.jnasir.akka.messages.filmMessageBox.FilmGetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.Duration;

@RestController
@RequestMapping("/films")

public class FilmController {

    @Autowired()
    @Qualifier("FilmActor")
    ActorRef filmActor;


    @GetMapping("/seedFilms") //For Test Add films
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
        Object filmComments = Patterns.ask(filmActor, new FilmGetCommentMessage(id), Duration.ofMillis(2000)).toCompletableFuture().join();

        modelAndView.addObject("f", film);
        modelAndView.addObject("c", filmComments);
        modelAndView.setViewName("film"); // resources/template/register.html
        return modelAndView;
    }

    @GetMapping("/create") // Create Film
    public ModelAndView addFilms() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add_film"); // resources/template/register.html
        return modelAndView;
    }


    @RequestMapping(value="/add", method=RequestMethod.POST) //save film
    public ModelAndView addNewFilm(@Valid Films film, BindingResult bindingResult, ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();
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

    @RequestMapping(value="/comment", method=RequestMethod.POST) //Add comment
    public ModelAndView addComment(@Valid Comments comment, BindingResult bindingResult, ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }else{
            Object res = Patterns.ask(filmActor, new FilmAddCommentMessage(comment), Duration.ofMillis(2000)).toCompletableFuture().join();
            modelAndView.addObject("successMessage", "Comment is added successfully!");
        }

        Object film = Patterns.ask(filmActor, new FilmGetMessage(comment.getFilm_id()), Duration.ofMillis(2000)).toCompletableFuture().join();
        modelAndView.addObject("f", film);

        modelAndView.setViewName("film");
        return modelAndView;


    }

    private <T> T ask(ActorRef actor, Object msg, Class<T> returnTypeClass) {
        return (T) Patterns.ask(actor, msg, Duration.ofMillis(2000)).toCompletableFuture().join();
    }

}
