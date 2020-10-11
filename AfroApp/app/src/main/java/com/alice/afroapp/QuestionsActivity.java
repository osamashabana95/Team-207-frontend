package com.alice.afroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
    private DatabaseReference mQuestionsReference;
    private ValueEventListener mQuestionListener;
    private static final String TAG = "QuestionsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Toolbar questionsToolbar = (Toolbar) findViewById(R.id.questions_toolbar);
        setSupportActionBar(questionsToolbar);
        getSupportActionBar().setIcon(R.drawable.ic_code_black_18dp);

        recyclerView = (RecyclerView) findViewById(R.id.questions_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mQuestionsReference = FirebaseDatabase.getInstance().getReference().child("Questions");

    }
    @Override
    public void onStart() {
        super.onStart();

        ValueEventListener questionListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String,Map<String,String>> map= (Map<String, Map<String,String>>) snapshot.getValue();
                List<Map<String,String>>list = new ArrayList<>(map.values());
                Log.w("TAG", list.get(0).get("userName"));
                RecyclerView.Adapter questionsAdapter = new QuestionAdapter(list, new QuestionAdapter.QuestionRecyclerViewItemClickListener() {

                    @Override
                    public void onItemClicked(String id) {

                        Intent solutionsIntent = new Intent(QuestionsActivity.this, SolutionsActivity.class);
                        Log.w("Question_Id", id);
                        solutionsIntent.putExtra("Question_Id", id);
                        startActivity(solutionsIntent);
                    }
                });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.go_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()){

            case R.id.action_home:
                goHome();
                return true;

            default: return super.onOptionsItemSelected(item);

        }
    }

    public void goHome(){

        Intent intent= new Intent(QuestionsActivity.this,MainActivity.class);
        startActivity(intent);
    }
}