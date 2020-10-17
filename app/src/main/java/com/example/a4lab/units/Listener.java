package com.example.a4lab.units;

public class Listener extends Person {
    String Organization;

    public Listener(){

    }

    public Listener(String organization) {
        Organization = organization;
    }


    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        if(checkName(organization))
            Organization = organization;
        else
            Organization = null;
    }

    @Override
    public String toString() {
        return "Listener{" + super.toString() +
                ", Organization='" + Organization + '\'' +
                '}';
    }
}
