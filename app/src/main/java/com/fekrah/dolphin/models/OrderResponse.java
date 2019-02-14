package com.fekrah.dolphin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OrderResponse {

    @SerializedName("id_order")
    @Expose
    private String id_order;

    @SerializedName("service_id_fk")
    @Expose
    private String service_id_fk;

    @SerializedName("client_id_fk")
    @Expose
    private String client_id_fk;

    @SerializedName("technical_id_fk")
    @Expose
    private String technical_id_fk;

    @SerializedName("order_status")
    @Expose
    private String order_status;

    @SerializedName("client_read_alert")
    @Expose
    private String client_read_alert;

    @SerializedName("technical_read_alert")
    @Expose
    private String technical_read_alert;

    @SerializedName("order_date")
    @Expose
    private String order_date;

    @SerializedName("order_date_str")
    @Expose
    private String order_date_str;

    @SerializedName("date_notification")
    @Expose
    private String date_notification;

    @SerializedName("is_current")
    @Expose
    private String is_current;

    @SerializedName("technical_arrival")
    @Expose
    private String technical_arrival;

    @SerializedName("order_details")
    @Expose
    private String order_details;

    @SerializedName("order_voice")
    @Expose
    private String order_voice;

    @SerializedName("order_video")
    @Expose
    private String order_video;

    @SerializedName("order_image")
    @Expose
    private String order_image;

    @SerializedName("order_type")
    @Expose
    private String order_type;

    @SerializedName("order_hours")
    @Expose
    private String order_hours;

    @SerializedName("order_address")
    @Expose
    private String order_address;

    @SerializedName("order_address_lat")
    @Expose
    private String order_address_lat;

    @SerializedName("order_address_long")
    @Expose
    private String order_address_long;

    @SerializedName("date_of_add")
    @Expose
    private String date_of_add;

    @SerializedName("technical_evaluation")
    @Expose
    private String technical_evaluation;

    @SerializedName("evaluation_note")
    @Expose
    private String evaluation_note;

    @SerializedName("office_evaluation")
    @Expose
    private String office_evaluation;

    @SerializedName("customer_satisfaction_level")
    @Expose
    private String customer_satisfaction_level;

    @SerializedName("order_paid")
    @Expose
    private String order_paid;

    @SerializedName("user_full_name")
    @Expose
    private String user_full_name;

    @SerializedName("user_photo")
    @Expose
    private String user_photo;

    @SerializedName("user_phone")
    @Expose
    private String user_phone;

    @SerializedName("ar_user_specialization")
    @Expose
    private String ar_user_specialization;

    @SerializedName("en_user_specialization")
    @Expose
    private String en_user_specialization;

    public OrderResponse() {
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getService_id_fk() {
        return service_id_fk;
    }

    public void setService_id_fk(String service_id_fk) {
        this.service_id_fk = service_id_fk;
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

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getClient_read_alert() {
        return client_read_alert;
    }

    public void setClient_read_alert(String client_read_alert) {
        this.client_read_alert = client_read_alert;
    }

    public String getTechnical_read_alert() {
        return technical_read_alert;
    }

    public void setTechnical_read_alert(String technical_read_alert) {
        this.technical_read_alert = technical_read_alert;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_date_str() {
        return order_date_str;
    }

    public void setOrder_date_str(String order_date_str) {
        this.order_date_str = order_date_str;
    }

    public String getDate_notification() {
        return date_notification;
    }

    public void setDate_notification(String date_notification) {
        this.date_notification = date_notification;
    }

    public String getIs_current() {
        return is_current;
    }

    public void setIs_current(String is_current) {
        this.is_current = is_current;
    }

    public String getTechnical_arrival() {
        return technical_arrival;
    }

    public void setTechnical_arrival(String technical_arrival) {
        this.technical_arrival = technical_arrival;
    }

    public String getOrder_details() {
        return order_details;
    }

    public void setOrder_details(String order_details) {
        this.order_details = order_details;
    }

    public String getOrder_voice() {
        return order_voice;
    }

    public void setOrder_voice(String order_voice) {
        this.order_voice = order_voice;
    }

    public String getOrder_video() {
        return order_video;
    }

    public void setOrder_video(String order_video) {
        this.order_video = order_video;
    }

    public String getOrder_image() {
        return order_image;
    }

    public void setOrder_image(String order_image) {
        this.order_image = order_image;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_hours() {
        return order_hours;
    }

    public void setOrder_hours(String order_hours) {
        this.order_hours = order_hours;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getOrder_address_lat() {
        return order_address_lat;
    }

    public void setOrder_address_lat(String order_address_lat) {
        this.order_address_lat = order_address_lat;
    }

    public String getOrder_address_long() {
        return order_address_long;
    }

    public void setOrder_address_long(String order_address_long) {
        this.order_address_long = order_address_long;
    }

    public String getDate_of_add() {
        return date_of_add;
    }

    public void setDate_of_add(String date_of_add) {
        this.date_of_add = date_of_add;
    }

    public String getTechnical_evaluation() {
        return technical_evaluation;
    }

    public void setTechnical_evaluation(String technical_evaluation) {
        this.technical_evaluation = technical_evaluation;
    }

    public String getEvaluation_note() {
        return evaluation_note;
    }

    public void setEvaluation_note(String evaluation_note) {
        this.evaluation_note = evaluation_note;
    }

    public String getOffice_evaluation() {
        return office_evaluation;
    }

    public void setOffice_evaluation(String office_evaluation) {
        this.office_evaluation = office_evaluation;
    }

    public String getCustomer_satisfaction_level() {
        return customer_satisfaction_level;
    }

    public void setCustomer_satisfaction_level(String customer_satisfaction_level) {
        this.customer_satisfaction_level = customer_satisfaction_level;
    }

    public String getOrder_paid() {
        return order_paid;
    }

    public void setOrder_paid(String order_paid) {
        this.order_paid = order_paid;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
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
}
