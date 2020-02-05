package com.ocp12.rideconsumer.dto;

import com.ocp12.ridemodele.Utilisateur;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class SortieKmlDto {

    private int sortieId;

    private String nom;

    private Date date;

    private String description;

    private Integer nbrParticipants;


    private Utilisateur organisateur;

    private String niveau;

    private Boolean horspiste;

    private Integer duree;

    private Double distance;

    private Integer nbrEtapes;

    private MultipartFile file;

    public int getSortieId() {
        return sortieId;
    }

    public void setSortieId(int sortieId) {
        this.sortieId = sortieId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNbrParticipants() {
        return nbrParticipants;
    }

    public void setNbrParticipants(Integer nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    public Utilisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Utilisateur organisateur) {
        this.organisateur = organisateur;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public Boolean getHorspiste() {
        return horspiste;
    }

    public void setHorspiste(Boolean horspiste) {
        this.horspiste = horspiste;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getNbrEtapes() {
        return nbrEtapes;
    }

    public void setNbrEtapes(Integer nbrEtapes) {
        this.nbrEtapes = nbrEtapes;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
