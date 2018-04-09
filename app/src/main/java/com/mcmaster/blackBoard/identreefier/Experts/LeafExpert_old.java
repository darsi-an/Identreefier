package com.mcmaster.blackBoard.identreefier.Experts;

import com.mcmaster.blackBoard.identreefier.Models.LeafDetails;

import java.util.HashMap;

/**
 * Created by Darsi An on 2018-04-06.
 */

public class LeafExpert_old extends Expert_old {
    private HashMap<String,String> rules;
    private LeafDetails leafDetails;

    private String[] rule;
    private String name;


    public LeafExpert_old(){
        super("Leaf-Expert");
    }

    @Override
    public boolean checkEventCondition(){
        return true;
//        this.leafDetails = new LeafDetails();
//        if(blackBoard.list.containsKey("leaf")){
//            this.rules = blackBoard.list.get("leaf");
//            Log.v("leaf-rules",rules.toString());
//            return true;
//        }else{
//            return false;
//        }
    }

    @Override
    public void handleEvent(){

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
