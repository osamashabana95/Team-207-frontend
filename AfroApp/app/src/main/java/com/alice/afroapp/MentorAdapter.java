package com.alice.afroapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MentorAdapter extends RecyclerView.Adapter<MentorAdapter.ViewHolder> {
    private ArrayList<Mentor> mentors;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListerner;


    public MentorAdapter(){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Mentors");
        mentors = new ArrayList<Mentor>();
        mChildEventListerner = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Mentor mentor = snapshot.getValue(Mentor.class);
                mentors.add(mentor);
                notifyItemInserted(mentors.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Mentor mentor = snapshot.getValue(Mentor.class);
                mentors.add(mentor);

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
        View itemView = LayoutInflater.from(context).inflate(R.layout.mentor_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mentor mentor = mentors.get(position);
        holder.bind(mentor);
    }

    @Override
    public int getItemCount() {
        return mentors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fullname;
        TextView profieciency;
        TextView location;


        public ViewHolder(@NonNull View itemView)  {
            super(itemView);
            fullname = (TextView) itemView.findViewById(R.id.fullname_text);
            profieciency = (TextView) itemView.findViewById(R.id.prof_text);
            location = (TextView) itemView.findViewById(R.id.loc_text);
            fullname.setOnClickListener(this);
           // itemView.setOnClickListener(this);

        }
        public void bind(Mentor mentor){
            fullname.setText(mentor.getFullname());
            profieciency.setText(mentor.getProficiency());
            location.setText(mentor.getLocation());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Mentor selectedMentor = mentors.get(position);
            String name = selectedMentor.getFullname();
            Log.d("message", String.valueOf(position));
            Intent intent = new Intent(itemView.getContext(), Prof.class);
            intent.putExtra("name",name);
            v.getContext().startActivity(intent);

        }
    }


}
