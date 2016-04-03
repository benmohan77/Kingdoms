package com.mohan.ben.kingdoms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A login screen that offers login via email/password.
 */
public class Signup extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mNameView;
    private Button bSignUp;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar myActionBar = getSupportActionBar();
        myActionBar.hide();
        setContentView(R.layout.activity_signup);
//         Set up the login form.
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        bSignUp = (Button) findViewById(R.id.email_sign_in_button);
        mNameView = (EditText) findViewById(R.id.name);
        bSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(isValidEmail(mEmailView.getText().toString())){
                    Intent i = new Intent(getApplicationContext(), Menu.class);
                    i.putExtra("email", mEmailView.getText().toString());
                    startActivity(i);
                } else{

                }


            }
        });

    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


}

