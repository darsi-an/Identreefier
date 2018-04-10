package com.mcmaster.blackBoard.identreefier.Models;

import java.util.ArrayList;
import java.util.List;

public class BarkDetails {

    private String treeName;
    private String colour;

    private String texture;

    //String color2,String color3,
    public BarkDetails(String treeName, String colour, String texture) {
        this.treeName = treeName;
        this.colour = colour;
      //  this.colour2 = color2;
      //  this.colour3 = color3;
        this.texture = texture;
    }

    public String getTreeName() {
        return this.treeName;
    }

    public String getColour() {
        return this.colour;
    }

    public String getTexture() {
        return this.texture;
    }

}
