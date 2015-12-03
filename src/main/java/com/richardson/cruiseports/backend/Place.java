package com.richardson.cruiseports.backend;

/**
 * Created by cordyrichardson on 10/27/15.
 */
public class Place {
    private String placeID, name, address;
    double latitude, longitude;

    public Place (String placeID){
        this.placeID = placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }


}
