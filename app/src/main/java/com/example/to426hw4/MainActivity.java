package com.example.to426hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextBirdName, editTextPersonName, editTextZipCode;
    Button buttonSubmit, buttonGoToSearch;
//test comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoToSearch = findViewById(R.id.buttonGoToSearch);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextZipCode = findViewById(R.id.editTextZipCode);


        buttonSubmit.setOnClickListener(this);
        buttonGoToSearch.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Birds");


        if(v == buttonGoToSearch){
            Intent goToSearch = new Intent(this, SearchActivity.class);
            startActivity(goToSearch);
        }
        else if(v == buttonSubmit){

            int createZipCode = Integer.parseInt(editTextZipCode.getText().toString());
            String createBirdName = editTextBirdName.getText().toString();
            String createPersonName = editTextPersonName.getText().toString();

            Bird myBird = new Bird(createBirdName, createZipCode, createPersonName);

            myRef.push().setValue(myBird);


            Toast.makeText(this, "Submission Successful", Toast.LENGTH_SHORT).show();

        }
    }

}
