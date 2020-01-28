package com.ocp12.ridemodele;

import javax.persistence.*;

@Entity
@Table(name = "etape")
public class Etape {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Column(name="etape_id")
private Integer etapeId;

private String nom;

private String description;

private Double distance;

private Double latitude;

private Double longitude;

@JoinColumn(name ="sortie_id",updatable = false)
@ManyToOne
private Sortie sortie;

    public Integer getEtapeId() {
        return etapeId;
    }

    public void setEtapeId(Integer etapeId) {
        this.etapeId = etapeId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }

    public Etape() {
    }

    @Override
    public String toString() {
        return "Etape{" +
                "etapeId=" + etapeId +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", distance=" + distance +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", sortie=" + sortie +
                '}';
    }
}
