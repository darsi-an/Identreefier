package com.mcmaster.blackBoard.identreefier.Experts;

import android.util.Log;

import com.mcmaster.blackBoard.identreefier.BlackBoard;
import com.mcmaster.blackBoard.identreefier.Models.LeafDetails;
import com.mcmaster.blackBoard.identreefier.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LeafExpert implements Expert {

    private static final String TAG = "BlackBoard";
    private HashMap<String, Double> results;
    private List<LeafDetails> listOfTrees;

    public BlackBoard blackBoard;
    //private Context blkbrd;

    public LeafExpert(BlackBoard blk){
        this.blackBoard = blk;
        this.listOfTrees =  new ArrayList<>();
    }

    @Override
    public String getName() {
        return "LeafExpert";
    }

    @Override
    public boolean checkEventCondition() {return true;}

    @Override
    public void handleEvent() {

        loadRules();
//        for(int i = 1 ; i < 21 ; i++){
//            Log.v("leafListTrees: " , listOfTrees.get(i).getTreeName());
//            Log.v("leafBase: ", listOfTrees.get(i).getBase());
//        }

        int length = listOfTrees.size();
        HashMap<String, Double> retList = new HashMap<>();
        for (int i = 1; i < length; i++) {
            double num = 1.0;
            double denom = 1.0;
            LeafDetails leaf = listOfTrees.get(i);
            HashMap<String, String> ui = blackBoard.userInput.getDetails();
//            Log.i("ui: ", ""+ui.get("leafBase"));
//            Log.i("leaf: ", leaf.getBase());
            if (ui.get("tree_type") != null && leaf.getType() != null && !ui.get("tree_type").equals("") && !leaf.getType().equals("")) {
                if (leaf.getType().equalsIgnoreCase(ui.get("tree_type"))) {
                    num += 1.0;
//                    Log.i("treetype: ", "true");
                }
                denom += 1.0;
            }
            if (ui.get("leaflet_arrangement") != null && leaf.getArrangement() != null && !ui.get("leaflet_arrangement").equals("") && !leaf.getArrangement().equals("")) {
                if (leaf.getArrangement().equalsIgnoreCase(ui.get("leaflet_arrangement"))) {
                    num += 1.0;
//                    Log.i("arrangement: ", "true");
                }
                denom += 1.0;
            }
            if (ui.get("leaf_type") != null && leaf.getLeafGrouping() != null && !ui.get("leaf_type").equals("") && !leaf.getLeafGrouping().equals("")) {
                if (leaf.getLeafGrouping().equalsIgnoreCase(ui.get("leaf_type"))) {
                    num += 1.0;
//                    Log.i("leaftype: ", "true");
                }
                denom += 1.0;
            }
            if (ui.get("leafEdge") != null && leaf.getEdge() != null && !ui.get("leafEdge").equals("") && !leaf.getEdge().equals("")) {
                if (leaf.getEdge().equalsIgnoreCase(ui.get("leafEdge"))) {
                    num += 1.0;
//                    Log.i("edge: ", "true");
                }
                denom += 1.0;
            }
            if (ui.get("isLobbed") != null && leaf.getLobes() != null && !ui.get("isLobbed").equals("") && !leaf.getLobes().equals("")) {
                if (leaf.getLobes().equals("1") && ui.get("isLobbed").equals("Lobbed")) {
                    num += 1.0;
//                    Log.i("lobbed: ", "true");
                }
                else if (leaf.getLobes().equals("0") && ui.get("isLobbed").equals("No Lobes")) {
                    num += 1.0;
//                    Log.i("not lobbed: ", "true");
                }
                denom += 1.0;
            }
            if (ui.get("leafBladeStructure") != null && leaf.getBladeStructure() != null && !ui.get("leafBladeStructure").equals("") && !leaf.getBladeStructure().equals("")) {
                if (leaf.getBladeStructure().equalsIgnoreCase(ui.get("leafBladeStructure"))) {
                    num += 1.0;
//                    Log.i("bladestruct: ", "true");
                }
                denom += 1.0;
            }
            if (ui.get("leafBase") != null && leaf.getBase() != null && !ui.get("leafBase").equals("") && !leaf.getBase().equals("")) {
                if (leaf.getBase().equalsIgnoreCase(ui.get("leafBase"))) {
                    num += 1.0;
//                    Log.i("base: ", "true");
                }
                denom += 1.0;
            }
            if (ui.get("leafShape") != null && leaf.getShape() != null && !ui.get("leafShape").equals("") && !leaf.getShape().equals("")) {
                if (leaf.getShape().equalsIgnoreCase(ui.get("leafShape"))) {
                    num += 1.0;
//                    Log.i("shape: ", "true");
                }
                denom += 1.0;
            }
            if (ui.get("needlesOrScales") != null && leaf.getNeedlesOrScales() != null && !ui.get("needlesOrScales").equals("") && !leaf.getNeedlesOrScales().equals("")) {
                if (leaf.getNeedlesOrScales().equalsIgnoreCase(ui.get("needlesOrScales"))) {
                    num += 1.0;
//                    Log.i("nedorscal: ", "true");

                }
                denom += 1.0;
            }
            if (ui.get("needlesBundled") != null && leaf.getNeedlesBundled() != null && !ui.get("needlesBundled").equals("") && !leaf.getNeedlesBundled().equals("")) {
                if (leaf.getNeedlesBundled().equalsIgnoreCase(ui.get("needlesBundled"))) {
                    num += 1.0;
//                    Log.i("nedbun: ", "true");
                }
                denom += 1.0;
            }
            //Log.i("num: ", num+"");
            //Log.i("denom: ", denom+"");
            retList.put(leaf.getTreeName(), num/denom);
        }
        //Log.v("tree resultsLeaf: " , retList.toString());
        //return retList; <- figure out how to do this
        this.results = retList;
        HashMap<String, Double> dummy = updateBB();
    }

    @Override
    public HashMap<String, Double> updateBB() {
        blackBoard.update(this.results, 2);
        return null;
    }

    @Override
    public void loadRules() {
        InputStream is = blackBoard.getResources().openRawResource(R.raw.treedata);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokens = line.split(",");

                // Read the data and store it in the WellData POJO.
                //tokens[0].toString(),tokens[1].toString(),tokens[2].toString(),tokens[3].toString(),tokens[4].toString(),tokens[5].toString(),tokens[6].toString(),tokens[7].toString(
                LeafDetails leafTreeData = new LeafDetails(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8],tokens[9],tokens[10]);
                listOfTrees.add(leafTreeData);

                //Log.d("MainActivity" ,"Just Created " + leafTreeData);
            }
        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line, e1);
            e1.printStackTrace();
        }
    }


}
