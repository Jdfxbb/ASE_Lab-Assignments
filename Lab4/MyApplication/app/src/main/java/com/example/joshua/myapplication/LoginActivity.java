package com.example.joshua.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Login", "Login");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button signInBtn;


        signInBtn = (Button)findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText usernameControl = (EditText) findViewById(R.id.usernameBox);
                EditText passwordControl = (EditText) findViewById(R.id.passwordBox);
                TextView errorText = (TextView) findViewById(R.id.InvalidTextView);
                String userName = usernameControl.getText().toString();
                String password = passwordControl.getText().toString();

                boolean isValid = false;

                if(!userName.isEmpty() && !password.isEmpty()){
                    if(userName.equals("Admin") && password.equals("Admin")){
                        isValid = true;
                    }
                }
                if(!isValid){
                    errorText.setVisibility(View.VISIBLE);
                }
                else{
                    Intent signIn = new Intent(LoginActivity.this, APIActivity.class);
                    startActivity(signIn);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void validate(View view){




    }
}

