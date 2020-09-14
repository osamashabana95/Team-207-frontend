package com.alice.afroapp;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseUtil {

//    public static FirebaseDatabase mFirebaseDatabase;
//    public static DatabaseReference mDatabaseReference;
//    public  static FirebaseAuth.AuthStateListener mAuthListener;
//    private static Activity caller;
//    private static FirebaseAuth mFirebaseAuth;
//    private static final int RC_SIGN_IN=123;
//    public  static FirebaseUtil firebaseUtil;
//
//    private FirebaseUtil(){}
//
//    public static void openFbReference(String ref, final Activity callerActivity) {
//        if(firebaseUtil == null){
//            firebaseUtil = new FirebaseUtil();
//            mFirebaseDatabase = FirebaseDatabase.getInstance();
//            mFirebaseAuth = FirebaseAuth.getInstance();
//            caller = callerActivity;
//            mAuthListener = new FirebaseAuth.AuthStateListener() {
//                @Override
//                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                    if(firebaseAuth.getCurrentUser()== null){
//                        FirebaseUtil.Signin();
//                    }
//
//                    Toast.makeText(callerActivity.getBaseContext(), "Welcome back",
//                            Toast.LENGTH_LONG).show();
//                }
//            };
//            mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
//
//        }
//
//    }
//
//        private static void Signin(){
//            // Choose authentication providers
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.EmailBuilder().build(),
//                new AuthUI.IdpConfig.GoogleBuilder().build());
//
//
// //Create and launch sign-in intent
//        caller.startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .build(),
//                RC_SIGN_IN);
//    }
//
//    public static  void attachListener(){
//        mFirebaseAuth.addAuthStateListener(mAuthListener);
//    }
//
//    public static void detachListener(){
//        mFirebaseAuth.removeAuthStateListener(mAuthListener);
//    }
//

}
