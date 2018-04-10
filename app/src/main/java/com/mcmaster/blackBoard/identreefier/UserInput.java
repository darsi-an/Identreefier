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
    //String leafBudArrangement;
    String needlesOrScales;
    String needlesBundled;
    String barkColour;
    String barkColor2;
    String barkTexture;
    Double lattitude;
    Double longitude;
    String city;

    HashMap<String,String> list;

    public HashMap<String,String> getDetails(){

        HashMap<String, String> leafMap = new HashMap<>();

        leafMap.put("tree_type",treeType);
        leafMap.put("leaflet_arrangement",leafletArrangement);
        leafMap.put("leaf_type",leafType);
        leafMap.put("leafEdge",leafEdge);
        leafMap.put("isLobbed",lobesOrNot);
        leafMap.put("leafBladeStructure",leafBladeStructure);
        leafMap.put("leafBase",leafBase);
        leafMap.put("leafShape",leafShape);
        //leafMap.put("leafBudArrangement",leafBudArrangement);
        leafMap.put("needlesOrScales",needlesOrScales);
        leafMap.put("needlesBundled",needlesBundled);

        HashMap barkMap = new HashMap();
        leafMap.put("barkColor",barkColour);
        leafMap.put("barkColor2",barkColor2);
        leafMap.put("barkTexture", barkTexture);


        HashMap loc = new HashMap();
//        leafMap.put("lattitude",lattitude.toString());
//        leafMap.put("longitude", longitude.toString());

        return leafMap;

    }
}
