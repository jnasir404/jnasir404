package com.jnasir.akka.controllers;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import com.jnasir.akka.Models.UserModels.UserModel;
import com.jnasir.akka.messages.userMessageBox.UserAddMessage;
import com.jnasir.akka.messages.userMessageBox.UserDeleteMessage;
import com.jnasir.akka.messages.userMessageBox.UserGetMessage;
import com.jnasir.akka.messages.userMessageBox.UserUpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.time.Duration;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired()
    @Qualifier("UserActor")
    ActorRef userActor;

    @GetMapping("/getUsers/") // Get All Users
    public Object getAllUsers() {
        return Patterns.ask(userActor, new UserGetMessage(), Duration.ofMillis(2000)).toCompletableFuture().join();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUsers/{id}") // Get user by Id
    public Object getUser(@PathVariable String id) throws URISyntaxException {
        try {
            return Patterns.ask(userActor, new UserGetMessage(id), Duration.ofMillis(2000)).toCompletableFuture().join();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add/") // Add a user
    public Object addUser(@RequestBody UserModel userModel) throws URISyntaxException {
        try {
            return Patterns.ask(userActor, new UserAddMessage(userModel), Duration.ofMillis(2000)).toCompletableFuture().join();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/add/{id}") // Update a user
    public Object updateUser(@RequestBody UserModel userModel, @PathVariable String id) throws URISyntaxException {
        try {
            return Patterns.ask(userActor, new UserUpdateMessage(userModel), Duration.ofMillis(2000)).toCompletableFuture().join();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}") // Delete a user
    public Object deleteUser(@RequestBody UserModel userModel, @PathVariable String id) throws URISyntaxException {
        try {
            return Patterns.ask(userActor, new UserDeleteMessage(userModel), Duration.ofMillis(2000)).toCompletableFuture().join();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    private <T> T ask(ActorRef actor, Object msg, Class<T> returnTypeClass) {
        return (T) Patterns.ask(actor, msg, Duration.ofMillis(2000)).toCompletableFuture().join();
    }
}
