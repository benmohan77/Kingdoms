package com.mohan.ben.kingdoms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Signup class that will appear on log out and on first time opening app
public class Signup extends AppCompatActivity {


    private EditText mEmailView;
    private EditText mNameView;
    private Button bSignUp;
    SharedPreferences prefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
    }
//Method that checks if email is valid
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    //Bulk of button handling in onResume
    //Currently saves emails to sharedPreferences, Retrofit not yet integrated
    @Override
    protected void onResume(){
        super.onResume();
        if(prefs.getBoolean("firstRun", true)){
            ActionBar myActionBar = getSupportActionBar();
            myActionBar.hide();
            setContentView(R.layout.activity_signup);

            mEmailView = (EditText) findViewById(R.id.email);
            bSignUp = (Button) findViewById(R.id.email_sign_in_button);
            mNameView = (EditText) findViewById(R.id.name);
            bSignUp.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    if (isValidEmail(mEmailView.getText().toString())) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("UserEmail", mEmailView.getText().toString());
                        editor.putBoolean("firstRun", false).apply();
                        Intent i = new Intent(getApplicationContext(), Main.class);
                        startActivity(i);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                        builder.setTitle("Invalid Email");
                        builder.setMessage("Email is invalid, please enter a valid email");
                        builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.setIcon(android.R.drawable.ic_dialog_alert);
                        builder.show();
                    }


                }
            });
        } else{
            Intent i = new Intent(getApplicationContext(), Main.class);
            startActivity(i);

        }
    }

}

