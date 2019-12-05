package com.example.bird;

public class Bird {

    public String birdname;
    public String zipcode;
    public String personame;
    public String email;
    public int importance;

    public Bird(String birdname, String zipcode, String personame, String email, int importance) {
        this.birdname = birdname;
        this.zipcode = zipcode;
        this.personame = personame;
        this.email = email;
        this.importance = importance;
    }

    public Bird() {
    }


    public void setImportance(int importance) {
        this.importance = importance;
    }
}
