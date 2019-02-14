package com.fekrah.dolphin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Specialize implements Serializable {

    @SerializedName("id_services")
    @Expose
    private String id_services;

    @SerializedName("ar_services_title")
    @Expose
    private String ar_services_title;

    @SerializedName("en_services_title")
    @Expose
    private String en_services_title;

    @SerializedName("services_logo")
    @Expose
    private String services_logo;


    public Specialize() {
    }

    public Specialize(String id_services, String ar_services_title) {
        this.id_services = id_services;
        this.ar_services_title = ar_services_title;
    }

    public String getId_services() {
        return id_services;
    }

    public void setId_services(String id_services) {
        this.id_services = id_services;
    }

    public String getAr_services_title() {
        return ar_services_title;
    }

    public void setAr_services_title(String ar_services_title) {
        this.ar_services_title = ar_services_title;
    }

    public String getEn_services_title() {
        return en_services_title;
    }

    public void setEn_services_title(String en_services_title) {
        this.en_services_title = en_services_title;
    }

    public String getServices_logo() {
        return services_logo;
    }

    public void setServices_logo(String services_logo) {
        this.services_logo = services_logo;
    }
}
