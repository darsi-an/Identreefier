package com.mcmaster.blackBoard.identreefier.Experts;

import android.content.Context;
import android.util.Log;

import com.mcmaster.blackBoard.identreefier.BlackBoard;
import com.mcmaster.blackBoard.identreefier.Models.LeafDetails;
import com.mcmaster.blackBoard.identreefier.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class LeafExpert implements Expert {

    private static final String TAG = "BlackBoard";
    private LeafDetails[] rules;
    public List<LeafDetails> listOfTrees;

    public BlackBoard blackBoard;
    private Context blkbrd;

    public LeafExpert(BlackBoard blk){
        this.blackBoard = blk;
        this.blkbrd = blk;
    }

    @Override
    public String getName() {
        return "LeafExpert";
    }

    @Override
    public boolean checkEventCondition() {

        return true;
    }

    @Override
    public void handleEvent() {

        Log.i(TAG,"Leaf expert not implemented yet");
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
                LeafDetails leafTreeData = new LeafDetails(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8],tokens[9],tokens[10]);
                listOfTrees.add(leafTreeData);

                Log.d("MainActivity" ,"Just Created " + leafTreeData);
            }
        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line, e1);
            e1.printStackTrace();
        }
    }


}
