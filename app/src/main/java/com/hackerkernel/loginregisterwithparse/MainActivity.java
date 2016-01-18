package com.hackerkernel.loginregisterwithparse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mFullname;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mPassword;
    private Button mRegister;
    private TextView mGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LOGIN");

        //instanciate views
        mFullname = (EditText) findViewById(R.id.regFullname);
        mEmail = (EditText) findViewById(R.id.regEmail);
        mPhone = (EditText) findViewById(R.id.regPhonenumber);
        mPassword = (EditText) findViewById(R.id.regPassword);
        mRegister = (Button) findViewById(R.id.register);
        mGoToLogin = (TextView) findViewById(R.id.goToLogin);
    }
}
