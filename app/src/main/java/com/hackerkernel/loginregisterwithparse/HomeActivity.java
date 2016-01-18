package com.hackerkernel.loginregisterwithparse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
        Button logout = (Button) findViewById(R.id.logout);

        //get logged in user
        ParseUser currentUser = ParseUser.getCurrentUser();

        fullname.setText(currentUser.getUsername());
        email.setText(currentUser.getEmail());
        phone.setText(currentUser.getString("phone"));

        //When logout button is clicked logout the user and send him to mainActivity
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                //send user to MainActivity
                Intent intent = new Intent(getApplication(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
