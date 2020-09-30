package com.alice.afroapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class Prof extends AppCompatActivity {
    private TextView fullname,proficiency,location,email;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ArrayList<Mentor> mentors;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fullname = (TextView) findViewById(R.id.fullname_text);
        proficiency = (TextView) findViewById(R.id.profciency_text);
        location = (TextView) findViewById(R.id.loc_text);
        email = (TextView) findViewById(R.id.email_addtext);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("mentors");


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        showProfile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mentorprofie,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.action_tohome:
                Toast.makeText(this,"Home.",Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_edit:
                return  true;

            default: return super.onOptionsItemSelected(item);
        }
    }

     public void showProfile(){
         Mentor mentor = new Mentor();
         mentors = new ArrayList<Mentor>();
         fullname = (TextView) findViewById(R.id.fullname_text);
         mDatabaseReference = mFirebaseDatabase.getReference().child("mentors");


         Intent intent = getIntent();
         String Currentid = intent.getStringExtra("id");
         final Query itemFilter = mDatabaseReference.orderByChild("id")
                 .equalTo(Currentid);

         itemFilter.addChildEventListener(new ChildEventListener() {
             @Override
             public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                 for(DataSnapshot childSnapshot: snapshot.getChildren()){
                     Mentor mentor = childSnapshot.getValue(Mentor.class);
                 }
             }

             @Override
             public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

             }

             @Override
             public void onChildRemoved(@NonNull DataSnapshot snapshot) {

             }

             @Override
             public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });


}}
