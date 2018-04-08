package com.mcmaster.blackBoard.identreefier.Experts;

import java.util.HashMap;

/**
 * Created by Darsi An on 2018-04-06.
 */

public class LeafExpert extends Expert {
    private HashMap<String,String> rules;
    private String[] rule;
    private String name;


    public LeafExpert(){

    }

    @Override
    public boolean checkEventCondition(){
        boolean t = true;
        return t;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void updateBB(){

    }

    @Override
    public void loadRules(){

    }



}
