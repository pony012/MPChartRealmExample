package com.example.root.mpchartrealmexample;

import io.realm.RealmObject;

/**
 * Created by root on 10/10/16.
 */

public class Sector extends RealmObject {
    public int id;
    public String name;
    public Sector(){
    }

    public Sector(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
