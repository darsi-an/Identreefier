package com.mcmaster.blackBoard.identreefier;

public class BarkDetails {

    String treeName;
    String colour;
    String texture;

    public BarkDetails(String treeName, String colour, String texture) {
        this.treeName = treeName;
        this.colour = colour;
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
