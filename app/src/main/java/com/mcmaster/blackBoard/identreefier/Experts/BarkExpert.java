package com.mcmaster.blackBoard.identreefier.Experts;

import android.content.Context;
import android.util.Log;

import com.mcmaster.blackBoard.identreefier.BlackBoard;
import com.mcmaster.blackBoard.identreefier.Models.BarkDetails;
import com.mcmaster.blackBoard.identreefier.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarkExpert implements Expert {

    private HashMap<String, Double> results;
    private BarkDetails[] rules;
    private double d;

    public List<BarkDetails> listOfTrees;

    public BlackBoard blackBoard;
    //private Context blkbrd;

    public BarkExpert(BlackBoard blk){
        this.blackBoard = blk;
        this.listOfTrees =  new ArrayList<>();
    }

    @Override
    public String getName() {
        return "BarkExpert";
    }

    @Override
    public boolean checkEventCondition() {
        return true;
    }

    @Override
    public void handleEvent() {
        //Log.v("hey: ", "there");
        loadRules();

        int length = listOfTrees.size();
        HashMap<String, Double> retList = new HashMap<>();

        for (int i=1; i<length; i++) {
            double num = 1.0;
            double denom = 1.0;
            BarkDetails bark = listOfTrees.get(i);
            HashMap<String, String> ui = blackBoard.userInput.getDetails();
//            Log.v("ui: ", ""+ui.get("barkColor"));
//            Log.v("bark: ", bark.getColour());

            if (ui.get("barkColor") != null && bark.getColour() != null && !ui.get("barkColor").equals("") && !bark.getColour().equals("")) {
                String[] colours = bark.getColour().split(" ");
                Log.v("num of colours", colours.length+"");
                for (String colour : colours) {
                    Log.v("colour", colour+ui.get("barkColor"));
                    if (colour.equalsIgnoreCase(ui.get("barkColor"))) {
                        num += 1;
                    }
                }
                denom += 1;
            }
            if ((ui.get("barkColor2") != null) && (bark.getColour() != null) && !ui.get("barkColor2").equals("") && !bark.getColour().equals("") && !ui.get("barkColor2").equals(ui.get("barkColor"))) {
                String[] colours = bark.getColour().split(" ");
                for (String colour : colours) {
                    Log.v("colour", colour);
                    if (colour.equalsIgnoreCase(ui.get("barkColor2"))) {
                        num += 1;
                    }
                }
                denom += 1;
            }
            if (ui.get("barkTexture") != null && bark.getTexture() != null && !ui.get("barkTexture").equals("") && !bark.getTexture().equals("")) {
                if (bark.getTexture().equalsIgnoreCase(ui.get("barkTexture"))) {
                    num += 1.0;
                    Log.v("arrangement ", "true");
                }
                denom += 1.0;
            }
            Log.v("bark num ", num+"");
            Log.v("bark denom ", denom+"");
            retList.put(bark.getTreeName(), num/denom);
            Log.v("S", "Added to list");
        }
    }

    @Override
    public Map<String, Double> updateBB() {
        blackBoard.update(this.results, 1);
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
                BarkDetails barkDetails = new BarkDetails(tokens[0],tokens[11],tokens[12]);
                listOfTrees.add(barkDetails);

                //Log.d("BarkExpert" ,"Just Created " + barkDetails);
            }
        } catch (IOException e1) {
            Log.e("BarkExpert", "Error" + line, e1);
            e1.printStackTrace();
        }
    }
}
