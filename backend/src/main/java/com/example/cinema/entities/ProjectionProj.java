package com.example.cinema.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

//regrouper les differents requetes qu'on pourra avoir ds une seul requetes qui contiendra toutes les infos qu'on aura besoin
@Projection(name="p1", types = {com.example.cinema.entities.Projection.class})
public interface ProjectionProj {
    public long getId();
    public double getPrix();
    public Date getDateProjection();
    public Salle getSalle();
    public Film getFilm();
    public Seance getSeance();
    public Collection<Ticket> getTickets();
}
