package com.example.root.mpchartrealmexample;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 10/10/16.
 */

public class Sector extends RealmObject {
    @PrimaryKey
    private long id;
    public String name;
    public Sector(){
    }

    public Sector(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
