package com.prestashop.utilities;

import com.github.javafaker.Faker;

public class FakeData{
    private Faker faker =new Faker();

    private String fname= getFaker().name().firstName();
    private String lname= getFaker().name().lastName();
    private String phonenmbr= getFaker().phoneNumber().cellPhone();
    private String email= getFaker().internet().emailAddress();
    private String address= getFaker().address().fullAddress();
    private String city= getFaker().address().city();
    private String state= getFaker().address().state();
    private String zipcode= getFaker().address().zipCode();
    private String pnumber= getFaker().phoneNumber().cellPhone();

    public Faker getFaker() {
        return faker;
    }

    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhonenmbr() {
        return phonenmbr;
    }

    public void setPhonenmbr(String phonenmbr) {
        this.phonenmbr = phonenmbr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }
}
