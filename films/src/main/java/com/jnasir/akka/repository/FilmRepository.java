package com.jnasir.akka.repository;

import com.jnasir.akka.Models.CommentsModel.Comments;
import com.jnasir.akka.Models.FilmModels.Films;
import com.jnasir.akka.jpa.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.util.List;

public class FilmRepository {

    public FilmRepository() {

    }

    public boolean create(List<Films> films) {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();
        try {
            for (int i = 0; i < films.size(); i++) {
                entityManager.getTransaction().begin();
                entityManager.persist(films.get(i));
                entityManager.getTransaction().commit();
            }

        } catch (Exception e) {
            System.out.println("Error while inserting film: " + e);
            return false;
        } finally {
            entityManager.close();
        }
        return true;
    }


    public boolean addFilm(Films films) {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(films);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error while inserting film: " + e);
            return false;
        } finally {
            entityManager.close();
        }
        return true;
    }


    public Object getComments() {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();
        List<Tuple> rows = null;
        try {
            entityManager.getTransaction().begin();
            rows = entityManager.createNativeQuery("SELECT comment,user_id FROM  comments", Tuple.class).getResultList();
            entityManager.getTransaction().commit();
            return rows;
        } catch (Exception e) {
            System.out.println("Error getting all films: " + e);
        } finally {
            entityManager.close();
        }
        return rows;
    }

    public Object getFilms() {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();
        List<Tuple> rows = null;
        try {
            entityManager.getTransaction().begin();
            rows = entityManager.createNativeQuery("SELECT DISTINCT * FROM  films", Tuple.class).getResultList();
            entityManager.getTransaction().commit();
            return rows;
        } catch (Exception e) {
            System.out.println("Error getting all films: " + e);
        } finally {
            entityManager.close();
        }
        return rows;
    }


    public List<Object> findFilmComment(String id) {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();

        List<Object> row = null;
        try {
            String[] filmId = new String[0];

            if (id.contains("-")) {
                filmId = id.split("-");
            } else {
                filmId[0] = id;
            }

            entityManager.getTransaction().begin();
            row = entityManager.createNativeQuery("SELECT comment,user_id FROM  comments where film_id = '" + filmId[0] + "'", Tuple.class).getResultList();
            entityManager.getTransaction().commit();

            return row;
        } catch (Exception e) {
            System.out.println("Error getting all films: " + e);
        } finally {
            entityManager.close();
        }
        return row;
    }

    public List<Object> find(String id) {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();

        List<Object> row = null;
        try {

            String[] filmId = id.split("-");
            entityManager.getTransaction().begin();
            row = entityManager.createNativeQuery("SELECT DISTINCT * FROM  films where id = " + Integer.parseInt(filmId[0]), Tuple.class).getResultList();
            entityManager.getTransaction().commit();

            return row;
        } catch (Exception e) {
            System.out.println("Error getting all films: " + e);
        } finally {
            entityManager.close();
        }
        return row;
    }

    public boolean createComment(Comments comment) {
        final EntityManager entityManager = EntityManagerFactoryUtil.getInstance().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(comment);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error while inserting comment: " + e);
            return false;
        } finally {
            entityManager.close();
        }
        return true;
    }
}
