package com.mcmaster.blackBoard.identreefier.Experts;

import com.mcmaster.blackBoard.identreefier.Models.LocationDetails;

import java.util.Map;

/**
 * Created by Darsi An on 2018-04-09.
 */

public class LocationExpert implements Expert {

    private LocationDetails[] rules;

    @Override
    public String getName() {
        return "LocationExpert";
    }

    @Override
    public boolean checkEventCondition() {
        return false;
    }

    @Override
    public void handleEvent() {

        System.out.println("Location not implemented yet");
    }

    @Override
    public Map<String, Double> updateBB() {
        return null;
    }

    @Override
    public void loadRules() {

    }
}
