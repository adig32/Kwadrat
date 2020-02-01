package com.example.kwadrat.Models;

public class OfferToReviewModel {

    String id, city, street, district, user_id, owner_id;

    public OfferToReviewModel(String id, String city, String street, String district, String user_id, String owner_id) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.district = district;
        this.user_id = user_id;
        this.owner_id = owner_id;
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getOwner_id() {
        return owner_id;
    }
}
