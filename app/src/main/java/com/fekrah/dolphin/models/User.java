package com.fekrah.dolphin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {


    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private UserData data;

    public User() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public class UserData{

        @SerializedName("message")
        @Expose
        private String message;

        @SerializedName("user_token")
        @Expose
        private String user_token;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("email")
        @Expose
        private String email;



        public UserData() {
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
