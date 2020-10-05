package com.alice.afroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.alice.afroapp.adapters.SolutionAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolutionsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mSolutionsReference;
    private ValueEventListener mSolutionListener;
    private static final String TAG = "SolutionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);
        final String questionId = getIntent().getStringExtra("Question_Id");
        recyclerView = (RecyclerView) findViewById(R.id.solutions_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mSolutionsReference = FirebaseDatabase.getInstance().getReference().child("Questions").child(questionId);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener solutionListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String,Map<String, Map<String,String>>> map= (Map<String, Map<String,Map<String,String>>>) snapshot.getValue();

                if(map.containsKey("Solutions")) {
                    List<Map<String, String>> list = new ArrayList<>(map.get("Solutions").values());

                    Log.w(TAG, list.get(0).get("title"));

                    RecyclerView.Adapter solutionsAdapter = new SolutionAdapter(list);

                    recyclerView.setAdapter(solutionsAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w(TAG, "loadSolutions:onCancelled", error.toException());

            }
        };

        mSolutionsReference.addValueEventListener(solutionListener);
        // Keep copy of question listener so we can remove it when app stops
        mSolutionListener = solutionListener;



    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSolutionListener != null) {
            mSolutionsReference.removeEventListener(mSolutionListener);

        }
    }
}