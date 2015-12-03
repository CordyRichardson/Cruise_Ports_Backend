package com.richardson.cruiseports.backend;

import java.util.ArrayList;
import java.util.List;

public class Port {
    //general port attributes
    private String name, cityName, address;
    private double latitude, longitude;

    //airport-related attributes
    private String parkingCost;
    private Airport airport1 = null, airport2 = null;

    //parking-related attributes
    private List<Place> parkingPlaceList;

    //hotel-related attributes
    private List<Place> hotelList;

    public Port(String name, String cityName, String address, String parkingCost, double latitude,
                double longitude){
        this.name = name;
        this.cityName = cityName;
        this.address = address;
        this.parkingCost = parkingCost;
        this.latitude = latitude;
        this.longitude = longitude;

        parkingPlaceList = new ArrayList<>();
        hotelList = new ArrayList<>();

    }


    //general port methods

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }

    public String getCityName(){
        return cityName;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public double getLatitude(){return latitude;}
    public double getLongitude(){return longitude;}


    //airport-related classes methods

    public void setAirport(String airportName, String distanceToPort, double latitude,
                           double longitude) {
        if (airport1 == null) {
            airport1 = new Airport(airportName, distanceToPort, latitude,longitude);
            return;
        } else if (airport2 == null)
            airport2 = new Airport(airportName, distanceToPort, latitude, longitude);

        else        //no more than 2 airports allowed
            return;
    }

    public Airport getAirport1(){
        return airport1;
    }

    public Airport getAirport2(){
        //returns null if no second airport
        return airport2;
    }

    private class Airport{
        private String airportName,distanceToPort;
        private double latitude, longitude;


        public Airport(String airportName, String distanceToPort, double latitude,
                       double longitude){

            this.airportName = airportName;
            this.distanceToPort = distanceToPort;
            this.latitude = latitude;
            this.longitude = longitude;

        }

        public void setAirportName(String airportName){
            this.airportName = airportName;
        }

        public String getAirportName(){
            return airportName;
        }

        public void setDistanceToPort (String distanceToPort){
            this.distanceToPort = distanceToPort;
        }

        public String getDistanceToPort(){
            return distanceToPort;
        }


    } // end airport class


    //parking-related classes and methods

    public void setParkingCost(String parkingCost){
        this.parkingCost = parkingCost;
    }

    public String getParkingCost(){
        return parkingCost;
    }

    public void addParkingPlace(Place place){
        parkingPlaceList.add(place);
    }

    public List<Place> getParkingPlaceList(){
        return parkingPlaceList;
    }

    //hotel-related classes and methods
    public void setHotelList(){

    }

    public List<Place>getHotelList(){
        return hotelList;
    }

    public void addHotelPlace(Place hotel){
        hotelList.add(hotel);
    }
}
