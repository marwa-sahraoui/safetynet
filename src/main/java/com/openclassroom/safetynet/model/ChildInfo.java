package com.openclassroom.safetynet.model;

import java.util.List;

public class ChildInfo {

    private String firstName;
    private String lastName;
    private int age;
    private List<Person> famille;

    public ChildInfo(String firstName, String lastName, int age, List<Person> famille) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.famille = famille;
    }

    public ChildInfo() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getFamille() {
        return famille;
    }

    public void setFamille(List<Person> famille) {
        this.famille = famille;
    }
}
