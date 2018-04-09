package com.mcmaster.blackBoard.identreefier;

import java.io.Serializable;
import java.util.HashMap;

public class UserInput implements Serializable{


    String treeType;
    String leafletArrangement;
    String leafType;
    String leafEdge;
    String lobesOrNot;
    String leafBladeStructure;
    String leafBase;
    String leafShape;
    String leafBudArrangement;
    String barkColour;
    String barkTexture;
    Double lattitude;
    Double longitude;
    String city;

    HashMap<String,HashMap<String,String>> list;

    public HashMap<String,HashMap<String,String>> getDetails(){

        this.list = new HashMap<>();

        HashMap leafMap = new HashMap();
        leafMap.put("tree_type",treeType);
        leafMap.put("leaflet_arrangement",leafletArrangement);
        leafMap.put("leaf_type",leafType);
        leafMap.put("leafEdge",leafEdge);
        list.put("leaf",leafMap);


        HashMap barkMap = new HashMap();
        barkMap.put("barkColor",barkColour);
        barkMap.put("barkTexture", barkTexture);
        list.put("bark",barkMap);



        HashMap loc = new HashMap();
        loc.put("lattitude",lattitude);
        loc.put("longitude", longitude);
        list.put("location",loc);

        return list;

    }
}
