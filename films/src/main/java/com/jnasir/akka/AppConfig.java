package com.jnasir.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.jnasir.akka.actors.FilmActor;
import com.jnasir.akka.actors.UserActor;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ActorSystem actorSystem(){
        ActorSystem actorSystem = ActorSystem.create("MyActorSystem", akkaConfig());
        return actorSystem;
    }

    @Bean
    public Config akkaConfig(){
        return ConfigFactory.load();
    }

    @Bean(name = {"UserActor"})
    public ActorRef userActor(){
        return actorSystem().actorOf(UserActor.propsDefault());
    }

    @Bean(name = {"FilmActor"})
    public ActorRef filmActor(){ return actorSystem().actorOf(FilmActor.propsDefault());
    }
}
