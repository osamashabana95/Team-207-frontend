package com.alice.afroapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private ArrayList<Solution> solutions;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListerner;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuth;

    public CommentsAdapter(){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Solutions");
        mFirebaseAuth = FirebaseAuth.getInstance();
        solutions = new ArrayList<Solution>();
        mChildEventListerner = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Solution solution = snapshot.getValue(Solution.class);
                solution.setId(snapshot.getKey());
                solutions.add(solution);

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
        };
        mDatabaseReference.addChildEventListener(mChildEventListerner);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.solutions_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Solution solution = solutions.get(position);
        holder.bind(solution);


    }

    @Override
    public int getItemCount() {
        return solutions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView solution;
        TextView title;
        //TextView username;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            solution = (TextView) itemView.findViewById(R.id.title_solu);
            title = (TextView) itemView.findViewById(R.id.question_title);
           // username = (TextView) itemView.findViewById(R.id.user_solu);

        }

        public void bind(Solution solution){
//            solution.setText(question.getSolution());
//            title.setText(question.getTitle());
//            username.setText(mFirebaseAuth.getCurrentUser().toString());

        }

    }

}
