package com.example.to426hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

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
        textViewBird = findViewById(R.id.textViewBird);
        textViewLastSeenBy = findViewById(R.id.textViewLastSeenBy);

        buttonSearch.setOnClickListener(this);
        buttonGoToMain.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==buttonGoToMain){
            Intent goToMain = new Intent(this, MainActivity.class);
            startActivity(goToMain);
        }
        else if(v== buttonSearch){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("Birds");

            int searchByZip = Integer.parseInt(editTextZipCodeEnter.getText().toString());
            myRef.orderByChild("zipcode").equalTo(searchByZip).addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String findKey = dataSnapshot.getKey();
                    Bird foundZipCode =dataSnapshot.getValue(Bird.class);
                    String findPersonName = foundZipCode.personname;
                    String findBirdName = foundZipCode.birdname;

                    textViewBird.setText(findBirdName);
                    textViewLastSeenBy.setText(findPersonName);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}
