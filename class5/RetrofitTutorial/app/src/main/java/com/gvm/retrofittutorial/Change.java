package com.gvm.retrofittutorial;

import com.google.gson.annotations.SerializedName;

public class Change {

    private String subject;

    @SerializedName("title_page")
    private String titlePage;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}