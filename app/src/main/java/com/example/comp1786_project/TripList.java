package com.example.comp1786_project;

public class TripList {
    private String trip_id;
    private String trip_name;
    private String trip_destination;
    private String trip_date;
    private String trip_requireAssessment;
    private String trip_description;

    public String getTrip_id(){
        return trip_id;
    }
    public void setTrip_id(String trip_id){
        this.trip_id = trip_id;
    }

    public String getTrip_name(){
        return trip_name;
    }
    public void setTrip_name(String trip_name){
        this.trip_name = trip_name;
    }

    public String getTrip_destination(){
        return trip_destination;
    }
    public void setTrip_destination(String trip_destination){
        this.trip_destination = trip_destination;
    }

    public String getTrip_date(){
        return trip_date;
    }
    public void setTrip_date(String trip_date){
        this.trip_date = trip_date;
    }

    public String getTrip_requireAssessment(){
        return trip_requireAssessment;
    }
    public void setTrip_requireAssessment(String trip_requireAssessment){
        this.trip_requireAssessment = trip_requireAssessment;
    }

    public String getTrip_description(){
        return trip_description;
    }
    public void setTrip_description(String trip_description){
        this.trip_description = trip_description;
    }

    public TripList(String trip_id, String trip_name, String trip_destination, String trip_date, String trip_requireAssessment, String trip_description){
        this.trip_id = trip_id;
        this.trip_name = trip_name;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
        this.trip_requireAssessment = trip_requireAssessment;
        this.trip_description = trip_description;
    }

}
