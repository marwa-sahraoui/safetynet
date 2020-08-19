package com.openclassroom.safetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePersonWithStatistic {
    private List<Person> person;
    private int numberChild;
    private int numberAdult;


    public ResponsePersonWithStatistic(List<Person> person, int numberAdult, int numberChild) {
        this.person = person;
        this.numberAdult = numberAdult;
        this.numberChild = numberChild;
    }

    public ResponsePersonWithStatistic() {
        person = new ArrayList<>();
        numberAdult = 0;
        numberChild = 0;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public int getNumberAdult() {
        return numberAdult;
    }

    public void setNumberAdult(int numberAdult) {
        this.numberAdult = numberAdult;
    }

    public int getNumberChild() {
        return numberChild;
    }

    public void setNumberChild(int numberChild) {
        this.numberChild = numberChild;
    }

}
