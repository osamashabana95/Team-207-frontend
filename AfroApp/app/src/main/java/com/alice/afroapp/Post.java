package com.alice.afroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Post extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private EditText editQuestiion;
    private ImageButton postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        editQuestiion  = (EditText) findViewById(R.id.editQuestion);
        postButton = (ImageButton)findViewById(R.id.postButton);
       // mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        //mDatabaseReference = FirebaseUtil.mDatabaseReference;
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postQuestion();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_question_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.action_edit:
                GoList();
                return true;
            case R.id.action_home:
                GoHome();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
//function to go view list questions
    public void GoList(){
        Intent intent= new Intent(Post.this,Listquestions.class);
        startActivity(intent);
    }

    public void GoHome(){
        Intent intent= new Intent(Post.this,MainActivity.class);
        startActivity(intent);
    }

    public void postQuestion(){
        String title = editQuestiion.getText().toString();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Convesations");
        mDatabaseReference.child("Conversations").child("Questions").
                child("questionId").child("title").push().setValue(title);
    }

}
