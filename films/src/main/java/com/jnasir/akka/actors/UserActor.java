package com.jnasir.akka.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import com.jnasir.akka.Models.UserModel;
import com.jnasir.akka.messages.userMessageBox.UserAddMessage;
import com.jnasir.akka.messages.userMessageBox.UserDeleteMessage;
import com.jnasir.akka.messages.userMessageBox.UserGetMessage;
import com.jnasir.akka.messages.userMessageBox.UserUpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserActor extends AbstractActor {
    @Autowired
    private ActorRef sender;

    private List<UserModel> users = new ArrayList<>(Arrays.asList(
            new UserModel("id", "name", "email", "password"),
            new UserModel("id1", "name1", "email1", "password1"),
            new UserModel("id2", "name2", "email2", "password2"),
            new UserModel("id3", "name3", "email3", "password3")
    ));

    public static Props propsDefault() {
        return Props.create(UserActor.class);
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(UserGetMessage.class, msg -> {
                    sender = sender();
                    Object result = getUsers(msg);
                    sender.tell(result, self());
                })
                .match(UserAddMessage.class, msg -> {
                    sender = sender();
                    List<UserGetMessage> result = addUser(msg);
                    sender.tell(result, self());
                })
                .match(UserUpdateMessage.class, msg -> {
                    sender = sender();
                    UserModel result = updateUser(msg);
                    sender.tell(result, self());
                })
                .match(UserDeleteMessage.class, msg -> {
                    sender = sender();
                    List<UserModel> result = deleteUser(msg);
                    sender.tell(result, self());
                })
                .build();
    }

    //Add User
    private List addUser(UserAddMessage msg) {
        UserModel user = new UserModel();

        user.setId(msg.getId());
        user.setName(msg.getName());
        user.setEmail(msg.getEmail());
        user.setPassword(msg.getPassword());

        users.add(user);
        return users;
    }

    //Update user
    private UserModel updateUser(UserUpdateMessage msg) {
        UserModel user = new UserModel();
        user.setId(msg.getId());
        user.setName(msg.getName());
        user.setEmail(msg.getEmail());
        user.setPassword(msg.getPassword());

        for (int i = 0; i < users.size(); i++) {
            UserModel u = users.get(i);
            if (u.getId().equals(msg.getId())) {
                users.set(i, user);
            }
        }
        return user;
    }

    //Delete user
    private List<UserModel> deleteUser(UserDeleteMessage msg) {
        for (int i = 0; i < users.size(); i++) {
            UserModel u = users.get(i);
            if (u.getId().equals(msg.getId())) {
                users.remove(u);
            }
        }
        return users;
    }

    //Get User
    private Object getUsers(UserGetMessage msg) {
        Object userObj = new UserModel();
        if (msg.getId() == null){
            return users;
        }else{
            for (int i = 0; i < users.size(); i++) {
                UserModel user = users.get(i);
                if (user.getId().equals(msg.getId())) {
                    userObj = user;
                }
            }
            return userObj;
        }
    }
}
