package com.mcmaster.blackBoard.identreefier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BlackBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_board);

        Intent intent = getIntent();
        String message = intent.getStringExtra("userInput");
        TextView textView = findViewById(R.id.textView5);
        textView.setText(message);
    }
}
