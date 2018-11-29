package com.example.bilalsalman.hogwartsexpress;

public class EmployeeDataForFireBase {


    String firstName;
    String lastNAme;
    String cnic;
    String email;
    String phoneNumber;
    String salary;
    String department;
    String iD;

    public EmployeeDataForFireBase() {
    }

    public EmployeeDataForFireBase(String firstName, String lastNAme, String cnic, String email, String phoneNumber, String salary, String department, String iD) {
        this.firstName = firstName;
        this.lastNAme = lastNAme;
        this.cnic = cnic;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.department = department;
        this.iD = iD;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNAme() {
        return lastNAme;
    }

    public void setLastNAme(String lastNAme) {
        this.lastNAme = lastNAme;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }
}
