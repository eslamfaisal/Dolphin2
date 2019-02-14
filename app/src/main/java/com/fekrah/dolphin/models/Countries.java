package com.fekrah.dolphin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Countries {

    @SerializedName("id_country")
    @Expose
    private String id_country;

    @SerializedName("ar_name")
    @Expose
    private String  ar_name;

    @SerializedName("en_name")
    @Expose
    private String en_name;

    @SerializedName("ar_nationality")
    @Expose
    private String  ar_nationality;

    @SerializedName("en_nationality")
    @Expose
    private String en_nationality;

    @SerializedName("phone_code")
    @Expose
    private String  phone_code;

    @SerializedName("sub_city")
    @Expose
    private List<City> cities;

    public String getId_country() {
        return id_country;
    }

    public void setId_country(String id_country) {
        this.id_country = id_country;
    }

    public String getAr_name() {
        return ar_name;
    }

    public void setAr_name(String ar_name) {
        this.ar_name = ar_name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getAr_nationality() {
        return ar_nationality;
    }

    public void setAr_nationality(String ar_nationality) {
        this.ar_nationality = ar_nationality;
    }

    public String getEn_nationality() {
        return en_nationality;
    }

    public void setEn_nationality(String en_nationality) {
        this.en_nationality = en_nationality;
    }

    public String getPhone_code() {
        return phone_code;
    }

    public void setPhone_code(String phone_code) {
        this.phone_code = phone_code;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public class City{

        @SerializedName("id_city")
        @Expose
        private String id_city;

        @SerializedName("ar_city_title")
        @Expose
        private String  ar_city_title;

        @SerializedName("en_city_title")
        @Expose
        private String en_city_title;

        public City() {
        }

        public String getId_city() {
            return id_city;
        }

        public void setId_city(String id_city) {
            this.id_city = id_city;
        }

        public String getAr_city_title() {
            return ar_city_title;
        }

        public void setAr_city_title(String ar_city_title) {
            this.ar_city_title = ar_city_title;
        }

        public String getEn_city_title() {
            return en_city_title;
        }

        public void setEn_city_title(String en_city_title) {
            this.en_city_title = en_city_title;
        }
    }
}
