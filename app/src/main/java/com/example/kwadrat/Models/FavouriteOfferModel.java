package com.example.kwadrat.Models;

public class FavouriteOfferModel {

    private String id, street, city_name, district_name, rent, flat_area, login, thumbnail, logged_user_email;

    public FavouriteOfferModel(String id, String street, String city_name, String district_name, String rent, String flat_area, String login, String thumbnail, String logged_user_email) {
        this.id = id;
        this.street = street;
        this.city_name = city_name;
        this.district_name = district_name;
        this.rent = rent;
        this.flat_area = flat_area;
        this.login = login;
        this.thumbnail = thumbnail;
        this.logged_user_email = logged_user_email;
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

    public String getLogin() {
        return login;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getLogged_user_email() {
        return logged_user_email;
    }

}
