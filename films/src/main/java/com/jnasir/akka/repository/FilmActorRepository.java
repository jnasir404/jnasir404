package com.jnasir.akka.repository;

import com.jnasir.akka.Models.Films;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorRepository extends JpaRepository<Films,Integer> {

}
