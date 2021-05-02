package com.example.qrscanner_appproject.data;

import java.util.Date;

public class Patient {
    private String Name;
    private String LastName;
    //Check if the import of date is good
    private Date DateOfBirth;
    private int SSN;
    private String BloodGroup;
    private double CostsOfHospitality;


    public Patient(String name, String lastName, Date dateOfBirth, int SSN, String bloodGroup, double costsOfHospitality) {
        Name = name;
        LastName = lastName;
        DateOfBirth = dateOfBirth;
        this.SSN = SSN;
        BloodGroup = bloodGroup;
        CostsOfHospitality = costsOfHospitality;
    }

    public Patient() {
    }

    public String getName() {
        return Name;
    }

    public String getLastName() {
        return LastName;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public int getSSN() {
        return SSN;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public double getCostsOfHospitality() {
        return CostsOfHospitality;
    }
}
