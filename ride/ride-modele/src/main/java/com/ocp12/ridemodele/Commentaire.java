package com.ocp12.ridemodele;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commentaire")
public class Commentaire {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="commentaire_id")
    private Integer id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Commentaire() {
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", participant=" + participant +
                ", date=" + date +
                '}';
    }
}
