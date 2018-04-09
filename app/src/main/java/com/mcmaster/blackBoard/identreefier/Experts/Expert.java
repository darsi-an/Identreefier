package com.mcmaster.blackBoard.identreefier.Experts;

import com.mcmaster.blackBoard.identreefier.BlackBoard;
import com.mcmaster.blackBoard.identreefier.Models.LeafDetails;

import java.util.List;
import java.util.Map;

/**
 * Created by Darsi An on 2018-04-05.
 */

public abstract class Expert {
    public BlackBoard blackBoard;
    private String name;
    private boolean canContribute;

    public Expert(String name){
        this.name = name;

    }

    public Expert(String name, BlackBoard blkb){
        this.blackBoard = blkb;
        this.name = name;

    }

    public String getName(){
        return this.name;
    }

    public boolean checkEventCondition(){
        return canContribute;
    }

    public void handleEvent(){

    }

    public void loadRules(){

    }

    public void updateBB(){

    }




}

