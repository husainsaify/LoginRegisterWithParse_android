package com.hackerkernel.loginregisterwithparse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    private EditText mFullname;
    private EditText mEmail;
    private EditText mPhone;
    private EditText mPassword;
    private Button mRegister;
    private TextView mGoToLogin;
    public ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Register");

        //Create a progressdialog
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait");
        pd.setCancelable(true);

        //Check user is logged in
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            //Send user to HomeActivity
            goToHomeActivity();
        }

        //instanciate views
        mFullname = (EditText) findViewById(R.id.regFullname);
        mEmail = (EditText) findViewById(R.id.regEmail);
        mPhone = (EditText) findViewById(R.id.regPhonenumber);
        mPassword = (EditText) findViewById(R.id.regPassword);
        mRegister = (Button) findViewById(R.id.register);
        mGoToLogin = (TextView) findViewById(R.id.goToLogin);

        //When Register button is clicked
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call Register Method
                register();
            }
        });

        //When GoToLogin button is clicked start loginActivity
        mGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),LoginActivity.class));
            }
        });
    }

    private void register() {
        String fullname = mFullname.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        //check fields are not empty
        if(!fullname.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !password.isEmpty()){
            pd.show(); //show progressDialog
            //Store user info in Parse.com
            ParseUser user = new ParseUser();
            user.setUsername(fullname);
            user.setEmail(email);
            user.put("phone", phone);
            user.setPassword(password);

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    pd.dismiss(); //hide progressDialog
                    if(e == null){
                        //Hurray signup completed
                        goToHomeActivity();
                    }else{
                        //Signup didnt work some error
                        Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(getApplication(), R.string.fill_in_all_the_fields, Toast.LENGTH_SHORT).show();
        }
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(getApplication(),HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
