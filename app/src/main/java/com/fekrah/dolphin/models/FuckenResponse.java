package com.fekrah.dolphin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FuckenResponse {

    @SerializedName("success_signup")
    @Expose
    private int success_signup;

    @SerializedName("success_resevation")
    @Expose
    private int success_resevation;

    @SerializedName("success_contact")
    @Expose
    private int success_contact;

    @SerializedName("success_confirm")
    @Expose
    private int success_confirm;

    public FuckenResponse() {
    }

    public int getSuccess_confirm() {
        return success_confirm;
    }

    public void setSuccess_confirm(int success_confirm) {
        this.success_confirm = success_confirm;
    }

    public int getSuccess_contact() {
        return success_contact;
    }

    public void setSuccess_contact(int success_contact) {
        this.success_contact = success_contact;
    }

    public int getSuccess_resevation() {
        return success_resevation;
    }

    public void setSuccess_resevation(int success_resevation) {
        this.success_resevation = success_resevation;
    }

    public int getSuccess_signup() {
        return success_signup;
    }

    public void setSuccess_signup(int success_signup) {
        this.success_signup = success_signup;
    }
}
