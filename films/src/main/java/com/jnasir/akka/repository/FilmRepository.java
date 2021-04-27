package com.jnasir.akka.repository;

import com.jnasir.akka.Models.Films;
import com.jnasir.akka.jpa.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

public class FilmRepository {

    public FilmRepository() {

    }

    public boolean create(Films film) {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(film);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
            return true;
        }

    }

    public Optional<Films> find(UUID uuid) {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            final Optional<Films> film = Optional.ofNullable(entityManager.find(Films.class, uuid));
            entityManager.getTransaction().commit();
            return film;
        } finally {
            entityManager.close();
        }
    }

    public Films update(Films film) {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            final Films updatedFilm = entityManager.merge(film);
            entityManager.getTransaction().commit();
            return updatedFilm;
        } finally {
            entityManager.close();
        }
    }

    public Films remove(Films film) {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(film);
            entityManager.getTransaction().commit();
            return film;
        } finally {
            entityManager.close();
        }
    }

}
