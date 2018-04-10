package com.mcmaster.blackBoard.identreefier.Experts;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.mcmaster.blackBoard.identreefier.BlackBoard;
import com.mcmaster.blackBoard.identreefier.Models.LocationDetails;
import com.mcmaster.blackBoard.identreefier.R;
import com.mcmaster.blackBoard.identreefier.UserInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Darsi An on 2018-04-09.
 */

public class LocationExpert implements Expert {

    private LocationDetails[] rules;

    public List<LocationDetails> listOfTrees;

    public BlackBoard blackBoard;
    //private Context blkbrd;

    public LocationExpert(BlackBoard blkbrd){
        this.blackBoard = blkbrd;
        this.listOfTrees = new ArrayList<>();
    }

    @Override
    public String getName() {
        return "LocationExpert";
    }

    @Override
    public boolean checkEventCondition() {
        return true;
    }

    @Override
    public void handleEvent() {

        HashMap<String, String> inputs = blackBoard.userInput.getDetails();

        Double lat = Double.parseDouble(inputs.get("lattitude"));
        Log.v("lat: ", Double.toString(lat));
        Double lng = Double.parseDouble(inputs.get("longitude"));
        Log.v("lng: ", Double.toString(lng));
        Geocoder geocoder = new Geocoder(blackBoard, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.v("Address from geocoder: ", addresses.toString());
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

                Log.d("LocationExpert" ,"Just Created " + locationTreeData);
            }
        } catch (IOException e1) {
            Log.e("LocationExpert", "Error" + line, e1);
            e1.printStackTrace();
        }
    }
}
