package com.company;

public class Hospitals {

    String name;
    String address;
    String available_capacity;
    String available_capacity_dose1;
    String available_capacity_dose2;
    String min_age_limt;
    String vaccine;

    public Hospitals(String name, String address, String available_capacity, String available_capacity_dose1, String available_capacity_dose2, String min_age_limt, String vaccine) {
        this.name = name;
        this.address = address;
        this.available_capacity = available_capacity;
        this.available_capacity_dose1 = available_capacity_dose1;
        this.available_capacity_dose2 = available_capacity_dose2;
        this.min_age_limt = min_age_limt;
        this.vaccine = vaccine;
    }
}
