package com.fb.smartfarm.Module.Monitor;

import java.util.List;

/**
 * Created by echo on 2017/5/7.
 */

public class MonitorPlaceBean {
    private String Place;
    private List<String> crop;

    public MonitorPlaceBean(){

    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public List<String> getCrop() {
        return crop;
    }

    public void setCrop(List<String> crop) {
        this.crop = crop;
    }
}
