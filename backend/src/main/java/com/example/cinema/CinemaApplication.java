package com.example.cinema;

import com.example.cinema.entities.Film;
import com.example.cinema.entities.Ticket;
import com.example.cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {

    @Autowired
    private ICinemaInitService iCinemaInitService;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration; // pour exposer le id ds le resultat

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Film.class, Ticket.class); //a chaque fois qu'il va serializer un fillm il va integrer le id ds le resultat ds la rep http
        //repositoryRestConfiguration.exposeIdsFor(Salle.class);
		iCinemaInitService.initVilles();
		iCinemaInitService.initCinemas();
		iCinemaInitService.initSalles();
		iCinemaInitService.initPlaces();
		iCinemaInitService.initSeances();
		iCinemaInitService.initCategories();
		iCinemaInitService.initFilms();
		iCinemaInitService.initProjections();
		iCinemaInitService.initTickets();
    }
}