package com.ocp12.modele;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sortie")
public class Sortie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="sortie_id")
    private int sortieId;


    @Column(name = "date")
    private Date date;

    @Column(name="description")
    private String description;

    @Column(name="nbre_participants")
    private Integer nbrParticipants;

    @Column(name = "organisateur")
    private Utilisateur organisateur;

    @Column(name = "niveau")
    private String niveau;

    @Column(name="hors_piste")
    private Boolean horspiste;

    @Column(name="duree")
    private String duree;

    @Column(name = "distance")
    private Double distance;

    @Column(name="nbr_etapes")
    private Integer nbrEtapes;


}
