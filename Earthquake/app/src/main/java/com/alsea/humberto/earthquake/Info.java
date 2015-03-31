package com.alsea.humberto.earthquake;

/**
 * Created by humberto on 3/30/15.
 */
public class Info {

    private String magnitude;
    private String place;
    private String title;
    private String coordinates;


    public Info(String _magnitude,String _place, String _title, String _coordinates){
        this.magnitude = _magnitude;
        this.place = _place;
        this.title = _title;
        this.coordinates = _coordinates;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
