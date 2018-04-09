package com.mcmaster.blackBoard.identreefier;

import java.util.Map;

public interface Expert {

    public abstract String getName();

    public abstract boolean checkEventCondition();

    public abstract void handleEvent();

    public abstract Map<String, Double> updateBB();

    public abstract void loadRules();
}
