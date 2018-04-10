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
import java.lang.reflect.Array;
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
        String city = addresses.get(0).getLocality();
        String postalCode = addresses.get(0).getPostalCode();
        findRegion(city);

        loadRules();
        int length = listOfTrees.size();
        HashMap<String, Double> retList = new HashMap<>();
//        for (int i = 1; i < length; i++) {
//            double num = 1.0;
//            double denom = 1.0;
//            LocationDetails location = listOfTrees.get(i);
//            HashMap<String, String> ui = blackBoard.userInput.getDetails();
////            Log.i("ui: ", ""+ui.get("leafBase"));
////            Log.i("leaf: ", leaf.getBase());
//            if (ui.get("tree_type") != null && location.getCity() != null && !ui.get("tree_type").equals("") && !location.getCity().equals("")) {
//                if (location.getType().equalsIgnoreCase(ui.get("tree_type"))) {
//                    num += 1.0;
////                    Log.i("treetype: ", "true");
//                }
//                denom += 1.0;
//            }
//            if (ui.get("leaflet_arrangement") != null && location.getArrangement() != null && !ui.get("leaflet_arrangement").equals("") && !location.getArrangement().equals("")) {
//                if (location.getArrangement().equalsIgnoreCase(ui.get("leaflet_arrangement"))) {
//                    num += 1.0;
////                    Log.i("arrangement: ", "true");
//                }
//                denom += 1.0;
//            }
//            Log.i("num: ", num+"");
//            Log.i("denom: ", denom+"");
//            retList.put(location.getTreeName(), num/denom);
//        }
//        Log.v("tree resultsLeaf: " , retList.toString());
        
    }

    public String findRegion(String city){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        ArrayList<String> south = new ArrayList<>();
        south.add("Toronto");
        south.add("Ottawa");
        south.add("Hamilton");
        south.add("Kitchener");
        south.add("Cambridge");
        south.add("London");
        south.add("St.Catherines");
        south.add("Niagara");
        south.add("Oshawa");
        south.add("Windsor");
        south.add("Barrie");
        south.add("Kingston");
        south.add("Guelph");
        south.add("Brantford");
        south.add("Peterborough");
        map.put("south",south);

        ArrayList<String> east = new ArrayList<>();
        east.add("Arnprior");
        east.add("Belleville");
        east.add("Brockville");
        east.add("Carleton Place");
        east.add("Cornwall");
        east.add("Embrun");
        east.add("Gananoque");
        east.add("Greater Napanee");
        east.add("Hawkesbury");
        east.add("Kingston");
        east.add("Mississippi Mills");
        east.add("Pembroke");
        east.add("Perth");
        east.add("Petawawa");
        east.add("Prince Edward County");
        east.add("Quinte West");
        east.add("Renfrew");
        east.add("Rockland");
        east.add("Russell");
        east.add("Smiths Falls");
        map.put("east",east);

        ArrayList<String> north = new ArrayList<>();
        north.add("Dryden");
        north.add("Elliot Lake");
        north.add("Greater Subury");
        north.add("Kenora");
        north.add("North Bay");
        north.add("Sault Ste.Marie");
        north.add("Thunder Bay");
        north.add("Temiskaming Shores");
        north.add("Timmmins");
        map.put("north",north);

        ArrayList<String> northWest = new ArrayList<>();
        northWest.add("Dryden");
        northWest.add("Thunder Bay");
        northWest.add("Kenora");
        northWest.add("Fort Frances");
        northWest.add("Sioux Lookout");
        northWest.add("Greenstone");
        northWest.add("Red Lake");
        northWest.add("Maraton");
        northWest.add("Atikokan");
        map.put("northwest",northWest);

        String region = "";
        for (int i = 0 ; i < map.size();i++){
            ArrayList tmp = map.get(i);
            for(int z = 0 ; z < tmp.size();z++){
                if(tmp.get(z).equals(city)){
                    region = map.get(i).toString();
                    Log.v("findRegion",map.get(i).toString());
                }
            }
        }

        return region;

    }
//    Address[addressLines=[0:"University Avenue",1:"Hamilton, ON L8S",2:"Canada"],feature=University Avenue,
// admin=Ontario,sub-admin=null,locality=Hamilton,thoroughfare=University Avenue,postalCode=L8S,countryCode=CA,countryName=Canada,
// hasLatitude=true,latitude=43.2611359,hasLongitude=true,longitude=-79.918741,phone=null,url=null,extras=null]]

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
