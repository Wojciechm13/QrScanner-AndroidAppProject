package com.example.qrscanner_appproject.data;

import java.util.Date;

public class Patient {
    private String Name;
    private String LastName;
    //Check if the import of date is good
    private String DateOfBirth;
    private long SSN;
    private String BloodGroup;
    private double CostsOfHospitality;


    public Patient() {
    }



    public String getName() {
        return Name;
    }

    public void setName(String name){
        this.Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public long getSSN() {
        return SSN;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public double getCostsOfHospitality() {
        return CostsOfHospitality;
    }
}