package com.mcmaster.blackBoard.identreefier.Models;

/**
 * Created by Darsi An on 2018-04-09.
 */

public class LocationDetails {

    private String treeName;
    private String city;
    private String province;
    private String country;

    public LocationDetails(String name, String city) {
        this.treeName = name;
        this.city = city;
        this.province = "Ontario";
        this.country = "Canada";
    }
}
