package com.dreamyDestination.yash.entity;

import java.util.ArrayList;

public class PlacesDetailListModel extends ArrayList<FeedbackModel>
{
    private String image_path;
    private String place_name;
    private String place_address;
    private String place_type;
    private String place_city;

    public PlacesDetailListModel( String image_path , String place_name, String place_address,String place_type,String place_city) {
        this.image_path = image_path;
        this.place_name = place_name;
        this.place_address = place_address;
        this.place_type = place_type;
        this.place_city = place_city;

    }

    public PlacesDetailListModel() {

    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_address() {
        return place_address;
    }

    public void setPlace_address(String place_address) {
        this.place_address = place_address;
    }

    public String getPlace_type() {
        return place_type;
    }

    public void setPlace_type(String place_type) {
        this.place_type = place_type;
    }

}



