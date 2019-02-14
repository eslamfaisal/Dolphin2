package com.fekrah.dolphin.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TermsResponse {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("content")
    @Expose
    private String content;

    public TermsResponse() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
