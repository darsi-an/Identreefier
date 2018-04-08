package com.mcmaster.blackBoard.identreefier;

import java.util.Map;

public class BarkExpert implements Expert {

    private Map<String, Double> rules;

    @Override
    public String getName() {
        return "BarkExpert";
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
