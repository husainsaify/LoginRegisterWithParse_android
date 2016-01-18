package com.hackerkernel.loginregisterwithparse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {
    private EditText mFullname;
    private EditText mPassword;
    private Button mLogin;
    public ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(getString(R.string.login));

        //Create a progressdialog
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait");
        pd.setCancelable(true);


        mFullname = (EditText) findViewById(R.id.logUsername);
        mPassword = (EditText) findViewById(R.id.logPassword);
        mLogin = (Button) findViewById(R.id.login);

        //When login button is clicked
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call Register Method
                login();
            }
        });
    }

    private void login() {
        String password = mPassword.getText().toString().trim();
        String username = mFullname.getText().toString().trim();

        //check fields are not empty
        if(!password.isEmpty() && !username.isEmpty()){
            pd.show(); //show progressDialog
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    pd.dismiss();//hide Pd
                    if (user != null) {
                        // Hooray! The user is logged in.
                        goToHomeActivity();
                    } else {
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
