package com.hackerkernel.loginregisterwithparse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Welcome");


        //instanciate views
        TextView fullname = (TextView) findViewById(R.id.homeFullname);
        TextView email = (TextView) findViewById(R.id.homeEmail);
        TextView phone = (TextView) findViewById(R.id.homePhone);


        //get logged in user
        ParseUser currentUser = ParseUser.getCurrentUser();

        fullname.setText(currentUser.getUsername());
        email.setText(currentUser.getEmail());
        phone.setText(currentUser.getString("phone"));

    }
}
