package com.example.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titre;
    private String description;
    private String realisateur;
    private Date dateSortie;
    private double duree;
    private String photo;
    @OneToMany(mappedBy = "film")
    ////qd vous avez une association bidirectionnelle qd il va serializer en format json en va tomber ds une boucle infini
    //  car convertir une categorie en json il va mettre les films et ds les film il va mettre les categorie et dc une boucle infine
    //

    //comment dire c'est pas la peine de me donner la projection ds le film???
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // comme vous lui dites cette propriete qu'on va convertir un obj film en format json en lecture ce n'est pas la peine de prendre en consideration ca tu prend uniquement en ecriture
    private Collection<Projection> projections;
    @ManyToOne
    private Categorie categorie;
}
