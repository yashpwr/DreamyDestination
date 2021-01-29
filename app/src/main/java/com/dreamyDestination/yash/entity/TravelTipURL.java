package com.dreamyDestination.yash.entity;

import java.util.ArrayList;

public class TravelTipURL extends ArrayList<RegisteredUserModel>
{
    private String travel_description;
    private String travel_description_type;
    private String date_added;

    public TravelTipURL( String date_added , String travel_description, String travel_description_type) {
        this.travel_description = travel_description;
        this.travel_description_type = travel_description_type;
        this.date_added = date_added;

    }

    public TravelTipURL() {

    }

    public String getTravel_description() {
        return travel_description;
    }

    public void setTravel_description(String travel_description) {
        this.travel_description = travel_description;
    }

    public String getTravel_description_type() {
        return travel_description_type;
    }

    public void setTravel_description_type(String travel_description_type) {
        this.travel_description_type = travel_description_type;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

}

