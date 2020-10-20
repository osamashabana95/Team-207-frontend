package com.alice.afroapp;

import android.content.Intent;
import android.os.Bundle;

import com.alice.afroapp.utility.Database;
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
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyProfile extends AppCompatActivity {
    private TextView fullname,proficiency,location,email;
    private static FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListerner;
    private ValueEventListener mValueEventListener;
    private FirebaseAuth mFirebaseAuth;
    private ImageView imageView;
    private static final String TAG = "MyProfile";


    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);


        mDatabaseReference = mFirebaseDatabase.getReference().child("Mentors");
        mFirebaseAuth = FirebaseAuth.getInstance();


        String Currentname = mFirebaseAuth.getCurrentUser().getDisplayName();

        final Query itemFilter = mDatabaseReference.orderByChild("fullname")
                .equalTo(Currentname);

        itemFilter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("fullname").getValue(String.class).toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        itemFilter.addValueEventListener(mValueEventListener);



    }

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
        imageView = (ImageView) findViewById(R.id.myprof_pic);



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
