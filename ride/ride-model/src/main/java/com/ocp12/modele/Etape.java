package com.ocp12.modele;

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

}
