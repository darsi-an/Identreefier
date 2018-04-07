package com.mcmaster.blackBoard.identreefier;

public class LeafDetails {

    private String treeName;
    private String leafArrangement;
    private String leafType;
    private String leafEdge;
    private String lobes;
    private String bladeStructure;
    private String leafBase;
    private String leafShape;

    public LeafDetails(String name, String arrangement, String type, String edge, String lobes, String bladeStructure, String base, String shape) {
        this.treeName = name;
        this.leafArrangement = arrangement;
        this.leafType = type;
        this.leafEdge = edge;
        this.lobes = lobes;
        this.bladeStructure = bladeStructure;
        this.leafBase = base;
        this.leafShape = shape;
    }

    public String getTreeName() {
        return this.treeName;
    }

    public String getArrangement() {
        return this.leafArrangement;
    }

    public String getType() {
        return this.leafType;
    }

    public String getEdge() {
        return this.leafEdge;
    }

    public String getLobes() {
        return this.lobes;
    }

    public String getBladeStructure() {
        return this.bladeStructure;
    }

    public String getBase() {
        return this.leafBase;
    }

    public String getShape() {
        return this.leafShape;
    }
}
