package com.example.kwadrat.Models;

public class MyOfferModel {

    private String id, street, city_name, district_name, rent, flat_area, visibility, thumbnail;

    public MyOfferModel(String id, String street, String city_name, String district_name, String rent, String flat_area, String visibility, String thumbnail) {
        this.id = id;
        this.street = street;
        this.city_name = city_name;
        this.district_name = district_name;
        this.rent = rent;
        this.flat_area = flat_area;
        this.visibility = visibility;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public String getRent() {
        return rent;
    }

    public String getFlat_area() {
        return flat_area;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
