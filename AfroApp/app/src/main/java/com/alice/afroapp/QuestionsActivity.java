package com.alice.afroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.alice.afroapp.adapters.QuestionAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter questionsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference mQuestionsReference;
    private ValueEventListener mQuestionListener;
    private static final String TAG = "QuestionsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questions);
        recyclerView = (RecyclerView) findViewById(R.id.questions_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mQuestionsReference = FirebaseDatabase.getInstance().getReference().child("Questions");

    }
    @Override
    public void onStart() {
        super.onStart();

        ValueEventListener questionListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String,Map<String,String>> map= (Map<String, Map<String,String>>) snapshot.child("Questions").getValue();
                List<Map<String,String>>list = new ArrayList<>(map.values());
                Log.w("TAG", list.get(0).get("username"));
                questionsAdapter = new QuestionAdapter(list);
                recyclerView.setAdapter(questionsAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.w(TAG, "loadQuestions:onCancelled", error.toException());

            }
        };

        mQuestionsReference.addValueEventListener(questionListener);
        // Keep copy of question listener so we can remove it when app stops
        mQuestionListener = questionListener;



    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mQuestionListener != null) {
            mQuestionsReference.removeEventListener(mQuestionListener);

        }

    }
}