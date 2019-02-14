package com.fekrah.dolphin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterResponse {


    @SerializedName("success_resevation")
    @Expose
    private String success_resevation;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("user_name")
    @Expose
    private String user_name;

    @SerializedName("is_login")
    @Expose
    private String is_login;

    @SerializedName("user_phone")
    @Expose
    private String user_phone;

    @SerializedName("user_type")
    @Expose
    private String user_type;

    @SerializedName("user_full_name")
    @Expose
    private String user_full_name;

    @SerializedName("user_specialization_id_fk")
    @Expose
    private String user_specialization_id_fk;

    @SerializedName("user_email")
    @Expose
    private String user_email;

    @SerializedName("user_photo")
    @Expose
    private String user_photo;

    @SerializedName("user_token_id")
    @Expose
    private String user_token_id;

    @SerializedName("user_country")
    @Expose
    private String user_country;

    @SerializedName("user_nationality")
    @Expose
    private String user_nationality;

    @SerializedName("user_city")
    @Expose
    private String user_city;

    @SerializedName("user_address")
    @Expose
    private String user_address;

    @SerializedName("id_country")
    @Expose
    private String id_country;

    @SerializedName("ar_name")
    @Expose
    private String ar_name;

    @SerializedName("en_name")
    @Expose
    private String en_name;

    @SerializedName("ar_city_title")
    @Expose
    private String ar_city_title;

    @SerializedName("en_city_title")
    @Expose
    private String en_city_title;

    @SerializedName("success_signup")
    @Expose
    private int success_signup;

    @SerializedName("success_login")
    @Expose
    private int success_login;

    @SerializedName("error_msg")
    @Expose
    private List<String> error_msg;

    public String getSuccess_resevation() {
        return success_resevation;
    }

    public void setSuccess_resevation(String success_resevation) {
        this.success_resevation = success_resevation;
    }

    public RegisterResponse() {
    }

    public List<String> getError_msg() {
        return error_msg;
    }

    public void setError_msg(List<String> error_msg) {
        this.error_msg = error_msg;
    }

    public int getSuccess_login() {
        return success_login;
    }

    public void setSuccess_login(int success_login) {
        this.success_login = success_login;
    }

    public int getSuccess_signup() {
        return success_signup;
    }

    public void setSuccess_signup(int success_signup) {
        this.success_signup = success_signup;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getIs_login() {
        return is_login;
    }

    public void setIs_login(String is_login) {
        this.is_login = is_login;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_specialization_id_fk() {
        return user_specialization_id_fk;
    }

    public void setUser_specialization_id_fk(String user_specialization_id_fk) {
        this.user_specialization_id_fk = user_specialization_id_fk;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_token_id() {
        return user_token_id;
    }

    public void setUser_token_id(String user_token_id) {
        this.user_token_id = user_token_id;
    }

    public String getUser_country() {
        return user_country;
    }

    public void setUser_country(String user_country) {
        this.user_country = user_country;
    }

    public String getUser_nationality() {
        return user_nationality;
    }

    public void setUser_nationality(String user_nationality) {
        this.user_nationality = user_nationality;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

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
