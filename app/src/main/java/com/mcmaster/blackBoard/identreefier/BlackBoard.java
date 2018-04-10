package com.mcmaster.blackBoard.identreefier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mcmaster.blackBoard.identreefier.Experts.BarkExpert;
import com.mcmaster.blackBoard.identreefier.Experts.Expert;
import com.mcmaster.blackBoard.identreefier.Experts.LeafExpert;
import com.mcmaster.blackBoard.identreefier.Experts.LocationExpert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackBoard extends AppCompatActivity {


    private List<Tree> tree_result;

    private List<Expert> experts;
    public UserInput userInput ;


    public Map<String,String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_board);

        this.tree_result = new ArrayList<>() ;
        readData();
//        for(int i = 1 ; i < 22; i++){
//            Log.v("tree_result: ", tree_result.get(i).getTreeName());
//        }

        this.experts = new ArrayList<>();

        Intent intent = getIntent();
        this.userInput = (UserInput) intent.getSerializableExtra("userInput");
        TextView textView = findViewById(R.id.textView5);

        this.list = userInput.getDetails();
        Log.v("list values: ",list.toString() );
        textView.setText(list.toString());

        Expert leaf = new LeafExpert(this);
        registerExpert(leaf);

        Expert bark = new BarkExpert(this);
        registerExpert(bark);

        Expert loc = new LocationExpert(this);
        registerExpert(loc);

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
                Tree treeData = new Tree(tokens[0]);

                tree_result.add(treeData);

                Log.d("EntryPage" ,"Just Created " + treeData);
            }
        } catch (IOException e1) {
            Log.e("EntryPage", "Error" + line, e1);
            e1.printStackTrace();
        }
    }




}
