package com.example.root.mpchartrealmexample;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by root on 7/10/16.
 */

public class UserProfile extends RealmObject {
    public int uniqueId;
    public String name;
    public RealmList<Sector> sectors;

    public UserProfile(){

    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
