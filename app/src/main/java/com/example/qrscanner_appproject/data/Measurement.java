package com.example.qrscanner_appproject.data;

import java.sql.Timestamp;

public class Measurement {
    private String temp;
    private String bloodPressure;
    private String givenDrugs;
    private String description;
    private String healthCondition;
    private String date;
    private String time;

    public Measurement(String temp, String bloodPressure, String givenDrugs, String description, String healthCondition, String date, String time) {
        this.temp = temp;
        this.bloodPressure = bloodPressure;
        this.givenDrugs = givenDrugs;
        this.description = description;
        this.healthCondition = healthCondition;
        this.date = date;
        this.time = time;
    }

    public Measurement() {
    }

    public String getTemp() {
        return temp;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getGivenDrugs() {
        return givenDrugs;
    }

    public String getDescription() {
        return description;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "temp='" + temp + '\'' +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", givenDrugs='" + givenDrugs + '\'' +
                ", description='" + description + '\'' +
                ", healthCondition='" + healthCondition + '\'' +
                '}';
    }
}
