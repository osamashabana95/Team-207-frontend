package com.alice.afroapp.utility;

import com.alice.afroapp.Mentor;
import com.alice.afroapp.NewQuestion;
import com.alice.afroapp.Question;
import com.alice.afroapp.Solution;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



// class to get and set data to firebase database.
public class Database {

    private DatabaseReference mFirebaseDatabase;

    public Database(){

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void setMentor (String fullName, String proficiency, String location,
                           String email, String imageUrl, String imageName){

        DatabaseReference newMentorRef = mFirebaseDatabase.child("Mentors").push();
        String mentorId = newMentorRef.getKey();
        Mentor mentor = new Mentor(mentorId,fullName,proficiency,location,email,imageUrl,imageName);
        newMentorRef.setValue(mentor);

    }

    public void setQuestion (String userName, String title){

        DatabaseReference newQuestionRef = mFirebaseDatabase.child("Questions").push();
        String questionId = newQuestionRef.getKey();
        NewQuestion question = new NewQuestion(questionId,title,userName);
        newQuestionRef.setValue(question);

    }

    public void setSolution (String questionId,String userName, String title){

        DatabaseReference newSolutionRef = mFirebaseDatabase.child("Questions").child(questionId).child("Solutions").push();
        String solutionId= newSolutionRef.getKey();
        Solution solution = new Solution(solutionId,title,userName);
        newSolutionRef.setValue(solution);

    }


}
