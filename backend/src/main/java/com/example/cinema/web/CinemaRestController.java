package com.example.cinema.web;

import com.example.cinema.dao.FilmRepository;
import com.example.cinema.dao.TicketRepository;
import com.example.cinema.entities.Film;
import com.example.cinema.entities.Ticket;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    // IMAGE_JPEG_VALUE c'est juste pr le navigateur car vous lui donner un tab de byte ms on va lui dire que ces donne represent image jpeg
    // car par defaut il utilise application.json c.ad il suppose que les donne sont des donnes json
    @GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") long id) throws Exception{
        Film film = filmRepository.findById(id).get();
        String PhotoName = film.getPhoto();
        File file = new File(System.getProperty("user.home") + "/cinema/images/" + PhotoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path); //retourn un tab d'octet
    }

    @PostMapping("/payerTickets")
    @Transactional
    //RequestBody c'est comme vous dites les donnes du tickets sont envoyés ds le corps de la req en format json
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> listTicket = new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket -> {
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReservee(true);
            ticket.setCodePayement(ticketForm.getCodePayment());
            ticketRepository.save(ticket);
            listTicket.add(ticket);
        });
        return  listTicket;
    }
}
@Data
@ToString
class TicketForm {
    private String nomClient;
    private int codePayment;
    private List<Long> tickets = new ArrayList<>();
}
