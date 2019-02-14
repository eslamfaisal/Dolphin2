package com.fekrah.dolphin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {


    @SerializedName("id_order")
    @Expose
    private String  id_order;

    @SerializedName("client_id_fk")
    @Expose
    private String client_id_fk;

    @SerializedName("technical_id_fk")
    @Expose
    private String technical_id_fk;

    @SerializedName("service_id_fk")
    @Expose
    private String service_id_fk;

    @SerializedName("order_status")
    @Expose
    private String order_status;

    @SerializedName("date_notification")
    @Expose
    private String date_notification;

    @SerializedName("technical_arrival")
    @Expose
    private String technical_arrival;

    @SerializedName("order_date")
    @Expose
    private String order_date;

    @SerializedName("user_full_name")
    @Expose
    private String user_full_name;

    @SerializedName("user_phone")
    @Expose
    private String user_phone;

    @SerializedName("ar_user_specialization")
    @Expose
    private String ar_user_specialization;

    @SerializedName("en_user_specialization")
    @Expose
    private String en_user_specialization;

    @SerializedName("services_logo")
    @Expose
    private String services_logo;

    public Notification() {
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getClient_id_fk() {
        return client_id_fk;
    }

    public void setClient_id_fk(String client_id_fk) {
        this.client_id_fk = client_id_fk;
    }

    public String getTechnical_id_fk() {
        return technical_id_fk;
    }

    public void setTechnical_id_fk(String technical_id_fk) {
        this.technical_id_fk = technical_id_fk;
    }

    public String getService_id_fk() {
        return service_id_fk;
    }

    public void setService_id_fk(String service_id_fk) {
        this.service_id_fk = service_id_fk;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getDate_notification() {
        return date_notification;
    }

    public void setDate_notification(String date_notification) {
        this.date_notification = date_notification;
    }

    public String getTechnical_arrival() {
        return technical_arrival;
    }

    public void setTechnical_arrival(String technical_arrival) {
        this.technical_arrival = technical_arrival;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getAr_user_specialization() {
        return ar_user_specialization;
    }

    public void setAr_user_specialization(String ar_user_specialization) {
        this.ar_user_specialization = ar_user_specialization;
    }

    public String getEn_user_specialization() {
        return en_user_specialization;
    }

    public void setEn_user_specialization(String en_user_specialization) {
        this.en_user_specialization = en_user_specialization;
    }

    public String getServices_logo() {
        return services_logo;
    }

    public void setServices_logo(String services_logo) {
        this.services_logo = services_logo;
    }
}
