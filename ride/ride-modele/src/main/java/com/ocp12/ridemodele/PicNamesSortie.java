package com.ocp12.ridemodele;

import javax.persistence.*;

@Entity
@Table(name = "picnamesortie")
public class PicNamesSortie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    private Integer sortieId;

    private String filename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSortieId() {
        return sortieId;
    }

    public void setSortieId(Integer sortieId) {
        this.sortieId = sortieId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "PicNamesSortie{" +
                "id=" + id +
                ", sortieId=" + sortieId +
                ", filename='" + filename + '\'' +
                '}';
    }
}
