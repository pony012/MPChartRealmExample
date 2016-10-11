package com.example.root.mpchartrealmexample;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by root on 7/10/16.
 */

public class UserProfile extends RealmObject {
    @PrimaryKey
    private long uniqueId;
    public String name;
    public RealmList<Sector> sectors;

    public UserProfile(){

    }

    public UserProfile(long uniqueId, String name, RealmList<Sector> sectors) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.sectors = sectors;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
