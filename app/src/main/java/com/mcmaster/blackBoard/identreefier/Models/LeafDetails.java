package com.mcmaster.blackBoard.identreefier.Models;

public class LeafDetails {

    private String treeName;
    private String leafType;
    private String leafArrangement;
    private String leafGrouping;
    private String leafEdge;
    private String lobes;
    private String bladeStructure;
    private String leafBase;
    private String leafShape;
    private String needlesOrScales;
    private String needlesBundled;

    public LeafDetails(String name, String type,  String arrangement, String grouping ,String edge, String lobes, String bladeStructure, String base, String shape, String needlesOrScales, String needlesBundled) {
        this.treeName = name;
        this.leafType = type;
        this.leafArrangement = arrangement;
        this.leafGrouping = grouping;
        this.leafEdge = edge;
        this.lobes = lobes;
        this.bladeStructure = bladeStructure;
        this.leafBase = base;
        this.leafShape = shape;
        this.needlesOrScales = needlesOrScales;
        this.needlesBundled = needlesBundled;


    }


    public String getTreeName() {
        return this.treeName;
    }

    public String getType() {
        return this.leafType;
    }

    public String getArrangement() {
        return this.leafArrangement;
    }

    public String getLeafGrouping() {
        return this.leafGrouping;
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

    public String getNeedlesOrScales() {return needlesOrScales;}

    public String getNeedlesBundled() {return needlesBundled;}

}
