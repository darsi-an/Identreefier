package com.mcmaster.blackBoard.identreefier.Experts;

import java.util.List;
import java.util.Map;

/**
 * Created by Darsi An on 2018-04-05.
 */

public abstract class Expert {
    private List<Map<String ,Object >> keys;
    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return(name);

    }

    public boolean checkEventCondition(){
        return true;
    }

    public void handleEvent(){

    }

    public void loadRules(){

    }

    public void updateBB(){

    }



}

