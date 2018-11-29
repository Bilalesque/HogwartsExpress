package com.example.bilalsalman.hogwartsexpress;

public class UserDataForFireBase {

    String firstName;
    String lastNAme;
    String cnic;
    String email;
    String creditCardNo;
    String phoneNumber;
    String address;
    String iD;

    public UserDataForFireBase() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastNAme(String lastNAme) {
        this.lastNAme = lastNAme;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public UserDataForFireBase(String firstName, String lastNAme, String cnic, String email, String creditCardNo, String phoneNumber, String address, String iD) {
        this.firstName = firstName;
        this.lastNAme = lastNAme;
        this.cnic = cnic;
        this.email = email;
        this.creditCardNo = creditCardNo;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.iD = iD;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastNAme() {
        return lastNAme;
    }

    public String getCnic() {
        return cnic;
    }

    public String getEmail() {
        return email;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getiD() {
        return iD;
    }
}