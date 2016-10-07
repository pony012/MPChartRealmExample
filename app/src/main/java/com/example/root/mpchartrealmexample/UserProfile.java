package com.example.root.mpchartrealmexample;

import io.realm.RealmObject;

/**
 * Created by root on 7/10/16.
 */

public class UserProfile extends RealmObject {
    int uniqueId;
    String name;
    String[] sectors;

    UserProfile(){

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

    public String[] getSectors() {
        return sectors;
    }

    public void setSectors(String[] sectors) {
        this.sectors = sectors;
    }
}
