package com.ocp12.rideconsumer.kmlparser;

public class CoordinatesBean {
    private String placeMarkName;
    private Double longiture;
    private Double latitude;

    public CoordinatesBean() {
    }

    public String getPlaceMarkName() {
        return placeMarkName;
    }

    public void setPlaceMarkName(String placeMarkName) {
        this.placeMarkName = placeMarkName;
    }

    public Double getLongiture() {
        return longiture;
    }

    public void setLongiture(Double longiture) {
        this.longiture = longiture;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
