package com.ocp12.ridemodele;


import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "sortie")
public class Sortie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="sortie_id")
    private int sortieId;

    @Column(name ="nom")
    private String nom;

    @Column(name = "date",updatable=false)
    private Date date;

    @Column(name="description")
    private String description;

    @Column(name="nbre_participants")
    private Integer nbrParticipants;

    @ManyToOne
    @JoinColumn(name="utilisateur_id")
    private Utilisateur organisateur;

    @Column(name = "niveau")
    private String niveau;

    @Column(name="hors_piste")
    private Boolean horspiste;

    @Column(name="duree")
    private Integer duree;

    @Column(name = "distance")
    private Double distance;

    @Column(name="nbr_etapes")
    private Integer nbrEtapes;

    @Column(name = "filename")
    private String filename;

    @Column(name = "statut")
    private String statut;

    @OneToMany(targetEntity=Participant.class, mappedBy="sortie",cascade=CascadeType.ALL)
    @ElementCollection(targetClass=Participant.class)
    private List<Participant> participants;


    public int getSortieId() {
        return sortieId;
    }

    public void setSortieId(int sortieId) {
        this.sortieId = sortieId;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Sortie() {
    }

    @Override
    public String toString() {
        return "Sortie{" +
                "sortieId=" + sortieId +
                ", nom='" + nom + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", nbrParticipants=" + nbrParticipants +
                ", organisateur=" + organisateur +
                ", niveau='" + niveau + '\'' +
                ", horspiste=" + horspiste +
                ", duree=" + duree +
                ", distance=" + distance +
                ", nbrEtapes=" + nbrEtapes +
                ", filename='" + filename + '\'' +
                ", statut='" + statut +
                '}';
    }
}
