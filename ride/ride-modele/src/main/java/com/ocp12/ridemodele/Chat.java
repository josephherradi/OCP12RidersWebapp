package com.ocp12.ridemodele;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="msg_id")
    private Integer id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Chat() {
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", date=" + date +
                ", utilisateur=" + utilisateur +
                ", message='" + message + '\'' +
                '}';
    }
}
