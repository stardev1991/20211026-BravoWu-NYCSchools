package io.easyfreelance.app.nycschools.model;

import com.google.gson.annotations.SerializedName;

public class SatResult {
    @SerializedName("dbn")
    public String dbn;

    @SerializedName("school_name")
    public String schoolName;

    @SerializedName("num_of_sat_test_takers")
    public int numOfSatTestTakers;

    @SerializedName("sat_critical_reading_avg_score")
    public int satCriticalReadingAvgScore;

    @SerializedName("sat_math_avg_score")
    public int satMathAvgScore;

    @SerializedName("sat_writing_avg_score")
    public int satWritingAvgScore;
}