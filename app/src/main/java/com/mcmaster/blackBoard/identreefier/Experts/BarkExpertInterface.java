package com.mcmaster.blackBoard.identreefier.Experts;

import com.mcmaster.blackBoard.identreefier.Models.BarkDetails;

import java.util.Map;

public class BarkExpertInterface implements Expert_Interface {

    private Map<String, Double> results;
    private BarkDetails[] rules;

    @Override
    public String getName() {
        return "BarkExpertInterface";
    }

    @Override
    public boolean checkEventCondition() {
        return false;
    }

    @Override
    public void handleEvent() {
        System.out.println("Bark expert not implemented yet");
    }

    @Override
    public Map<String, Double> updateBB() {
        return null;
    }

    @Override
    public void loadRules() {

    }
}
