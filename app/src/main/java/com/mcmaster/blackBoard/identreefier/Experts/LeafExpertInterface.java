package com.mcmaster.blackBoard.identreefier.Experts;

import com.mcmaster.blackBoard.identreefier.Models.LeafDetails;

import java.util.Map;

public class LeafExpertInterface implements Expert_Interface {

    private LeafDetails[] rules;

    @Override
    public String getName() {
        return "LeafExpertInterface";
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