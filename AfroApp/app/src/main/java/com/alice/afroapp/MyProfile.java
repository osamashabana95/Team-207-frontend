package com.alice.afroapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {
    private TextView fullname,proficiency,location,email;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListerner;
    private ValueEventListener mValueEventListener;
    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_code_black_18dp);

        fullname = (TextView) findViewById(R.id.fullname_text);
        proficiency = (TextView) findViewById(R.id.profciency_text);
        location = (TextView) findViewById(R.id.loc_text);
        email = (TextView) findViewById(R.id.email_addtext);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("mentors");
        mFirebaseAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String Currentid = mFirebaseAuth.getCurrentUser().getDisplayName();
        final String email = mFirebaseAuth.getCurrentUser().getEmail();

        final Query itemFilter = mDatabaseReference.orderByChild("username")
                .equalTo(Currentid);

       mDatabaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot != null){
                   if(email == snapshot.child("email").getValue()){
                       for (DataSnapshot postSnapshot:snapshot.getChildren())  {
                           fullname.setText(postSnapshot.child("fullname").
                                   getValue(String.class).toString()); }
                   }

               }



           }
           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

        mDatabaseReference.addValueEventListener(mValueEventListener);


//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                fullname.setText(snapshot.child("fullname").getValue(String.class).toString());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

}
