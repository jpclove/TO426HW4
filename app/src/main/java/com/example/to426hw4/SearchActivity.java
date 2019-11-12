package com.example.to426hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    EditText editTextZipCodeEnter;
    Button buttonSearch, buttonGoToMain;
    TextView textViewPerson, textViewBird, textViewLastSeenBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextZipCodeEnter = findViewById(R.id.editTextZipCodeEnter);
        buttonGoToMain = findViewById(R.id.buttonGoToMain);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewPerson = findViewById(R.id.textViewPerson);
        textViewBird = findViewById(R.id.textViewBird);
        textViewLastSeenBy = findViewById(R.id.textViewLastSeenBy);

        buttonSearch.setOnClickListener(this);
        buttonGoToMain.setOnClickListener(this);
    }

    @Override OnClick(View v){
        if(v==buttonGoToMain){
            Intent goToMain = new Intent(this, MainActivity.class);
            startActivity(goToMain);
        }

    }
}
