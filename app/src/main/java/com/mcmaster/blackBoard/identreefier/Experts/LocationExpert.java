package com.mcmaster.blackBoard.identreefier.Experts;

import android.content.Context;
import android.util.Log;

import com.mcmaster.blackBoard.identreefier.BlackBoard;
import com.mcmaster.blackBoard.identreefier.Models.LocationDetails;
import com.mcmaster.blackBoard.identreefier.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by Darsi An on 2018-04-09.
 */

public class LocationExpert implements Expert {

    private LocationDetails[] rules;

    public List<LocationDetails> listOfTrees;

    public BlackBoard blackBoard;
    private Context blkbrd;

    @Override
    public String getName() {
        return "LocationExpert";
    }

    @Override
    public boolean checkEventCondition() {
        return false;
    }

    @Override
    public void handleEvent() {

        System.out.println("Location not implemented yet");
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
                LocationDetails locationTreeData = new LocationDetails(tokens[0],tokens[11] );
                listOfTrees.add(locationTreeData);

                Log.d("MainActivity" ,"Just Created " + locationTreeData);
            }
        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line, e1);
            e1.printStackTrace();
        }
    }
}
