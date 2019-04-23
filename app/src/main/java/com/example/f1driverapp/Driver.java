package com.example.f1driverapp;

import java.lang.ref.SoftReference;

public class Driver {
    private String driverId;
    private String code;
    private String url;
    private String givenName;
    private String familyName;
    private String dateOfBirth;
    private String nationality;





    public Driver() {
    }

    public Driver(String driverId, String code, String url, String givenName, String familyName, String dateOfBirth, String nationality)
    {
        this.driverId = driverId;
        this.code = code;
        this.url=url;
        this.givenName=givenName;
        this.familyName=familyName;
        this.dateOfBirth=dateOfBirth;
        this.nationality=nationality;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverID ='" + driverId + '\'' +
                ", code='" + code + '\'' +
                ", url='" + url + '\'' +
                ", giveName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
