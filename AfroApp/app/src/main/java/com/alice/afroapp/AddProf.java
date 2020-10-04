package com.alice.afroapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class AddProf extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 22;
    private FirebaseDatabase mFirebaseDatabase;
    private  DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private EditText editName,editProf,editLoc,editEmail;
    private Uri filePath;
    private ImageView imageView;
    private ImageButton selectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_code_black_18dp);


        //initializing views
        editName = (EditText) findViewById(R.id.editName);
        editProf = (EditText) findViewById(R.id.editProf);
        editLoc = (EditText) findViewById(R.id.editLoc);
        editEmail= (EditText) findViewById(R.id.editEmail);
        imageView = (ImageView) findViewById(R.id.prof_pic);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("mentors");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        selectButton = (ImageButton)findViewById(R.id.select_button);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveMentor();

            }
        });

        String imageUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();
        Picasso.with(this).load(imageUrl).placeholder(R.drawable.ic_person_black_24dp)
                .resize(70,70)
                .into(imageView);



    }
    public void Viewprofile(){
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.action_home:
                GoHome();
                return true;
            case R.id.action_prof:
                MyProfile();
                return true;
            case R.id.action_list:
                GoList();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void GoHome(){
        Intent intent= new Intent(AddProf.this,MainActivity.class);
        startActivity(intent);
    }

    public void GoProf(){
        Intent intent= new Intent(AddProf.this,Prof.class);
        startActivity(intent);
    }


    public void GoList(){
        Intent intent= new Intent(AddProf.this,MentorList.class);
        startActivity(intent);
    }


    public void SelectImage(){
// Defining Implicit Intent to mobile gallery
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(
//                Intent.createChooser(
//                        intent,
//                        "Select Image from here..."),
//                PICK_IMAGE_REQUEST);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode,
                resultCode,
                data);
        final Mentor mentor = new Mentor();


        // checking request code and result code
//       if (requestCode == PICK_IMAGE_REQUEST
//                && resultCode == RESULT_OK
//                && data != null
//                && data.getData() != null) {
//
//            filePath = data.getData();
//            try {
//
//                String imageUri =  mFirebaseAuth.getCurrentUser().getPhotoUrl().getLastPathSegment();
//                URL url = new URL(mFirebaseAuth.getCurrentUser().getPhotoUrl().getLastPathSegment());
//                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//
////                // Setting image on image view using Bitmap
////                Bitmap bitmap = MediaStore
////                        .Images
////                       .Media
////                        .getBitmap(
//                               getContentResolver(),
//                                filePath);
//               imageView.setImageBitmap(bitmap);
//           }
//            catch (IOException e) {
//               // Log the exception
//                e.printStackTrace();
//            }
//        }
    }

//    private void uploadImage()
//
//    {
//        final Mentor mentor = new Mentor();
//
//        if (filePath != null) {
//
//            // Code for showing progressDialog while uploading
//            final ProgressDialog progressDialog
//                    = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading...");
//            progressDialog.show();
//
//
//            // Defining the child of storageReference
//            StorageReference ref
//                    = storageReference
//                    .child(
//                            "images/"
//                                    + UUID.randomUUID().toString());
//
//            // adding listeners on upload
//            ref.putFile(filePath)
//                    .addOnSuccessListener(
//                            new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//                                @Override
//                                public void onSuccess(
//                                        UploadTask.TaskSnapshot taskSnapshot)
//                                {
//
//                                    // Image uploaded successfully
//                                    // Dismiss dialog
//                                    progressDialog.dismiss();
//
//
//                                    Toast
//                                            .makeText(AddProf.this,
//                                                    "Image Uploaded!!",
//                                                    Toast.LENGTH_SHORT)
//                                            .show();
//                                }
//                            })
//
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e)
//                        {
//                            // Error, Image not uploaded
//                            progressDialog.dismiss();
//                            Toast
//                                    .makeText(AddProf.this,
//                                            "Failed " + e.getMessage(),
//                                            Toast.LENGTH_SHORT)
//                                    .show();
//                        }
//                    })
//                    .addOnProgressListener(
//                            new OnProgressListener<UploadTask.TaskSnapshot>() {
//
//                                // Progress Listener for loading
//                                @Override
//                                public void onProgress(
//                                        UploadTask.TaskSnapshot taskSnapshot)
//                                {
//                                    double progress
//                                            = (100.0
//                                            * taskSnapshot.getBytesTransferred()
//                                            / taskSnapshot.getTotalByteCount());
//                                    progressDialog.setMessage(
//                                            "Uploaded "
//                                                    + (int)progress + "%");
//                                }
//                            });
//        }
//    }




    public void SaveMentor(){
        String fullname = editName.getText().toString();
        String proficiency = editProf.getText().toString();
        String location = editLoc.getText().toString();
        String email = editEmail.getText().toString();
        String imageUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();
        String username = mFirebaseAuth.getCurrentUser().getDisplayName();
        Mentor mentor = new Mentor("id",fullname,proficiency,location,email, imageUrl,
                "imageName", "key",username);
       DatabaseReference mentoRef= mDatabaseReference.push();
               mentoRef.setValue(mentor);
               String key = mentoRef.getKey();
               mentor.setKey(key);

    }



    public void MyProfile(){
        String username = mFirebaseAuth.getCurrentUser().getDisplayName();
        Intent intent = new Intent(AddProf.this, MyProfile.class);
        intent.putExtra("name",username);
        startActivity(intent);

    }
}
