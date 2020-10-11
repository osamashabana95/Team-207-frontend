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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alice.afroapp.adapters.SolutionAdapter;
import com.alice.afroapp.utility.Database;
import com.google.firebase.auth.FirebaseAuth;
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
    private EditText s_editText;
    private String questionId;
    private DatabaseReference mSolutionsReference;
    private ValueEventListener mSolutionListener;
    private static final String TAG = "SolutionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        Toolbar solutionsToolbar = (Toolbar) findViewById(R.id.solutions_toolbar);
        setSupportActionBar(solutionsToolbar);
        getSupportActionBar().setIcon(R.drawable.ic_code_black_18dp);

        questionId = getIntent().getStringExtra("Question_Id");
        recyclerView = (RecyclerView) findViewById(R.id.solutions_recycler_view);
        s_editText= (EditText) findViewById(R.id.s_edit_text);
        Button button = (Button) findViewById(R.id.s_enter_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String solution = String.valueOf(s_editText.getText());
                String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                if (solution.isEmpty()) {

                    Toast.makeText(SolutionsActivity.this, "Enter Your Solution", Toast.LENGTH_LONG).show();

                }else {

                    Database db = new Database();
                    db.setSolution(questionId, userName, solution);
                    s_editText.setText("");

                }

            }
        });
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

        Intent intent= new Intent(SolutionsActivity.this,MainActivity.class);
        startActivity(intent);
    }

}