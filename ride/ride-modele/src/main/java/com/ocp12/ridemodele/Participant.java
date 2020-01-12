package com.ocp12.ridemodele;

import javax.persistence.*;

@Entity
@Table(name="participant")
public class Participant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="participant_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="sortie_id")
    private Sortie sortie;

    @OneToOne
    @JoinColumn(name = "utilisateur_id")
    private  Utilisateur utilisateur;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Participant() {
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", sortie=" + sortie +
                ", utilisateur=" + utilisateur +
                '}';
    }
}
