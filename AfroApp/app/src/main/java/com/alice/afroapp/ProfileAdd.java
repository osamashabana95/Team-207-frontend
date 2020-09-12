package com.alice.afroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ProfileAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_add);
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
            case R.id.action_new:
                return true;
            case R.id.action_list:
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void GoHome(){
        Intent intent= new Intent(ProfileAdd.this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Questions list.",Toast.LENGTH_LONG).show();
    }
}
