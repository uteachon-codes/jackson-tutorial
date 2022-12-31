package com.serverless.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Employee {
    private String name;
    private String city;
    private String department;
    private String designation;
    
    // @JsonIgnore
    private Address address;

    public Employee() {
        super();
    }

    public Employee(String name, String city, String department, String designation) {
        this.name = name;
        this.city = city;
        this.department = department;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getDepartment() {
        return department;
    }

    public String getDesignation() {
        return designation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }    
}
