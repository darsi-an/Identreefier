package com.mcmaster.blackBoard.identreefier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemSelectedListener ;

public class EntryPage extends AppCompatActivity implements OnItemSelectedListener {

    public List<String> inputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);

        Spinner spinner = (Spinner) findViewById(R.id.spinner3);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tree_type, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        inputs = new ArrayList<>();
        spinner.setOnItemSelectedListener(this);


    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String tree_type = (String) parent.getItemAtPosition(pos);
        inputs.add(tree_type);
        Log.v("tree-type: ", tree_type);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    //public void add
    public void sendBlackBoardData(View view){
        Intent intent = new Intent(this,BlackBoard.class);
        String message = inputs.get(0);
        Log.v("spinnder-value:",message);
        intent.putExtra("userInput", message);
        startActivity(intent);
    }


}
