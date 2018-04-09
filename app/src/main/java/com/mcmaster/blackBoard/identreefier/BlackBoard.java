package com.mcmaster.blackBoard.identreefier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mcmaster.blackBoard.identreefier.Experts.Expert;
import com.mcmaster.blackBoard.identreefier.Experts.LeafExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackBoard extends AppCompatActivity {


    private List<Tree> tree_result;
    private List<Expert> experts;
    private UserInput userInput ;
    public Map<String,HashMap<String,String>> list;


    public  List<TreeData> listOfTrees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_board);

        readData();

        this.experts = new ArrayList<>();

        Intent intent = getIntent();
        this.userInput = (UserInput) intent.getSerializableExtra("userInput");

        //update(userInput);
        //this.list = new HashMap<>();
        this.list = userInput.getDetails();
        Log.v("list values: ",list.toString() );

        Expert leaf = new LeafExpert();
        registerExpert(leaf);
        expertEventTrigger();
    }




    public void registerExpert(Expert expert){
        if (expert == null){
            Log.v("null-pointer: ","registered expert is null");
            throw new NullPointerException("Null Expert");
        }

        if(!experts.contains(expert)){
            experts.add(expert);
        }
    }

    public void unregister(Expert expert){
        experts.remove(expert);
    }


    public void expertEventTrigger(){
        for(Expert e : experts){
            if(e.checkEventCondition()){
                e.handleEvent();
            }
        }
    }

    public void update(UserInput userInput){
        this.userInput = userInput;
    }


    public void inspect(){

    }

    public void reset(){

    }

    private void readData() {
        InputStream is = getResources().openRawResource(R.raw.treedata);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        try {
            while ((line = reader.readLine()) != null) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokens = line.split(",");

                // Read the data and store it in the WellData POJO.
                TreeData wellData = new TreeData();
                wellData.setTreeName(tokens[0]);
                wellData.setTreeType(tokens[1]);
                wellData.setLeafLetArrangement(tokens[2]);

                listOfTrees.add(wellData);

                Log.d("MainActivity" ,"Just Created " + wellData);
            }
        } catch (IOException e1) {
            Log.e("MainActivity", "Error" + line, e1);
            e1.printStackTrace();
        }
    }




}
