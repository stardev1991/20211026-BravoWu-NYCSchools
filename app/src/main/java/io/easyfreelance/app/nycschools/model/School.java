package io.easyfreelance.app.nycschools.model;

import com.google.gson.annotations.SerializedName;

public class School {
    @SerializedName("dbn")
    public String dbn;

    @SerializedName("school_name")
    public String schoolName;

    @SerializedName("overview_paragraph")
    public String description;
}
