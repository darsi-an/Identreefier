package com.mcmaster.blackBoard.identreefier;

import java.util.Map;

public class LeafExpert implements Expert {

    private BarkDetails[] rules;

    @Override
    public String getName() {
        return "LeafExpert";
    }

    @Override
    public boolean checkEventCondition() {
        return false;
    }

    @Override
    public void handleEvent() {
        System.out.println("Leaf expert not implemented yet");
    }

    @Override
    public Map<String, Double> updateBB() {
        return null;
    }

    @Override
    public void loadRules() {
        
    }
}