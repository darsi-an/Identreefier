package com.mcmaster.blackBoard.identreefier;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener ;

public class EntryPage extends Activity implements OnItemSelectedListener {

    private UserInput userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);

        Spinner tree_type = (Spinner) findViewById(R.id.tree_type_spinner);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.tree_type, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tree_type.setAdapter(aa);
        tree_type.setOnItemSelectedListener(this);

        Spinner leaf_arrangement = (Spinner) findViewById(R.id.leaflet_arrangement_spinner);
        ArrayAdapter<CharSequence> aa2 = ArrayAdapter.createFromResource(this, R.array.l_arrangement, android.R.layout.simple_spinner_item);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_arrangement.setAdapter(aa2);
        leaf_arrangement.setOnItemSelectedListener(this);

        Spinner leaf_type = (Spinner) findViewById(R.id.leaf_type_spinner);
        ArrayAdapter<CharSequence> aa3 = ArrayAdapter.createFromResource(this, R.array.l_type, android.R.layout.simple_spinner_item);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_type.setAdapter(aa3);
        leaf_type.setOnItemSelectedListener(this);

        this.userInput = new UserInput();

    }

    /**
     * String treeType;
     * String leafGrouping;
     * String leafEdge;
     * String lobesOrNot;
     * String leafBladeStructure;
     * String leafBase;
     * String leafShape;
     * String leafBudArrangement
     * String barkColour;
     * String barkTexture;
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        switch (parent.getId()){
            case R.id.tree_type_spinner:
                String treeType = parent.getSelectedItem().toString().trim();
                Log.v("input-tree-type: ", treeType);
                userInput.treeType = treeType;
                Log.v("input-tree-type: ",userInput.treeType);
                break;
            case R.id.leaflet_arrangement_spinner:
                userInput.leafletArrangement = parent.getSelectedItem().toString().trim();
                Log.v("input-leaflet-arng:",userInput.leafletArrangement);
                break;
            case R.id.leaf_type_spinner:
                userInput.leafType = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-type: ",userInput.leafType);
                break;

        }
    }


    //public void add
    public void sendBlackBoardData(View view){
        Intent intent = new Intent(this,BlackBoard.class);
        intent.putExtra("userInput", userInput);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
