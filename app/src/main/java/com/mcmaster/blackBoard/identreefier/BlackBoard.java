package com.mcmaster.blackBoard.identreefier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mcmaster.blackBoard.identreefier.Experts.Expert;
import com.mcmaster.blackBoard.identreefier.Experts.LeafExpert;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackBoard extends AppCompatActivity {

    private List<Expert> experts;
    private UserInput userInput ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_board);

        this.experts = new ArrayList<>();

        Intent intent = getIntent();
        this.userInput = (UserInput) intent.getSerializableExtra("userInput");

//        TextView textView = findViewById(R.id.textView5);
//        textView.setText(userInput.treeType);
//        Log.v("blackboard-treetype",userInput.treeType);

        startIdentificationProcess();
    }

    public void startIdentificationProcess(){
        Expert leaf = new LeafExpert();
        registerExpert(leaf);

        expertEventTrigger();
    }

    public void registerExpert(Expert expert){
        if (expert == null){
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

        }

    }

    public void update(){

    }

    public void inspect(){


    }

    public void reset(){

    }




}
