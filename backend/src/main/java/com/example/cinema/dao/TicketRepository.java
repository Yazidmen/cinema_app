package com.example.cinema.dao;

import com.example.cinema.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource // ca veut dire que toutes les methodes que vous avez herites de JpaRepository sont accessible via une API rest
//Lorsqu'une classe est annotée avec @RepositoryRestResource, Spring Data crée automatiquement un ensemble de points de terminaison REST pour effectuer des opérations CRUD (Create, Read, Update, Delete) sur cette classe. Les endpoints générés par Spring Data suivent des conventions de nommage standard pour les ressources REST. Par exemple, une classe d'entité nommée "Personne" sera exposée sous l'URI "/personnes".
//
//En utilisant cette annotation, les développeurs peuvent facilement créer une API REST sans avoir à écrire manuellement le code pour chaque opération CRUD. L'annotation @RepositoryRestResource offre également la possibilité de personnaliser les points de terminaison générés, en utilisant les paramètres disponibles tels que "path", "collectionResourceRel", "excerptProjection", etc.

@CrossOrigin("*")
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
