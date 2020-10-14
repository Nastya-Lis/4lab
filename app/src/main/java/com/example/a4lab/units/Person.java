package com.example.a4lab.units;

import android.net.Uri;
import android.provider.ContactsContract;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(value = Student.class),
                @JsonSubTypes.Type(value = Listener.class)
        }
)
public abstract class Person implements Serializable {
    String name;
    String surName;
    Integer age;
    String curse;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.contains("@"))
            this.email = email;
        else
            this.email = null;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNetworkReff() {
        return networkReff;
    }

    public void setNetworkReff(String networkReff) {
        this.networkReff = networkReff;
    }

    public String getPhotography() {
        return photography;
    }

    public void setPhotography(String photography) {
        this.photography = photography;
    }

    String email;
    String phone;
    String networkReff;
    String photography;

    public Person(){

    }

    public Person(String name,String surName,Integer age,String curse,
                  String email, String phone,String networkReff,String photography){
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.curse = curse;
        this.email = email;
        this.phone = phone;
        this.networkReff = networkReff;
        this.photography = photography;
    }

    protected Boolean checkName(String name){
        try{
            Integer.parseInt(name);
            return false;
        }
        catch(NumberFormatException e){
            return true;
        }
    }
    //Getter/Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(checkName(name) == true)
            this.name = name;
        else
            this.name = null;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age>=16 && age<=56)
        this.age = age;
        else
            this.age = null;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        if(checkName(surName) == true)
        this.surName = surName;
        else
            this.surName = null;
    }

    public String getCurse() {
        return curse;
    }

    public void setCurse(String curse) {
        if(checkName(curse) == true)
        this.curse = curse;
        else
            this.curse = null;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                ", curse='" + curse + '\'' +
                '}';
    }
}
