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


    public HashMap<String,String> getDetails(){

        HashMap<String, String> inputs = new HashMap<>();

        inputs.put("tree_type",treeType);
        inputs.put("leaflet_arrangement",leafletArrangement);
        inputs.put("leaf_type",leafType);
        inputs.put("leafEdge",leafEdge);
        inputs.put("isLobbed",lobesOrNot);
        inputs.put("leafBladeStructure",leafBladeStructure);
        inputs.put("leafBase",leafBase);
        inputs.put("leafShape",leafShape);
        inputs.put("needlesOrScales",needlesOrScales);
        inputs.put("needlesBundled",needlesBundled);

        inputs.put("barkColor",barkColour);
        inputs.put("barkColor2",barkColor2);
        inputs.put("barkTexture", barkTexture);

        if (lattitude != null) {
            inputs.put("lattitude", lattitude.toString());
        }
        if (longitude != null) {
            inputs.put("longitude", longitude.toString());
        }

        return inputs;

    }
}
