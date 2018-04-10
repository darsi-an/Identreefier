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
import java.util.List;
import java.util.Map;

public class BarkExpert implements Expert {

    private Map<String, Double> results;
    private BarkDetails[] rules;

    public List<BarkDetails> listOfTrees;

    public BlackBoard blackBoard;
    //private Context blkbrd;

    public BarkExpert(BlackBoard blkbrd){
        this.blackBoard = blkbrd;
        this.listOfTrees = new ArrayList<>();
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
        System.out.println("Bark expert not implemented yet");
    }

    @Override
    public Map<String, Double> updateBB() {
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
                BarkDetails barkDetails = new BarkDetails(tokens[0],tokens[9],tokens[10]);
                listOfTrees.add(barkDetails);

                Log.d("BarkExpert" ,"Just Created " + barkDetails);
            }
        } catch (IOException e1) {
            Log.e("BarkExpert", "Error" + line, e1);
            e1.printStackTrace();
        }
    }
}
