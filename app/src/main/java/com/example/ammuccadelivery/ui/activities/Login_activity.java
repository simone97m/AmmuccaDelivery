package com.example.ammuccadelivery.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ammuccadelivery.R;

import java.util.regex.Pattern;

public class Login_activity extends AppCompatActivity implements View.OnClickListener{

    Button loginBT;
    Button registrazioneBT;

    EditText emailET;
    EditText passwordET;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBT = findViewById(R.id.login_BT);
        registrazioneBT = findViewById(R.id.registrazione_BT);
        emailET = findViewById(R.id.email_ET);
        passwordET = findViewById(R.id.password_ET);

        loginBT.setOnClickListener(this);
        registrazioneBT.setOnClickListener(this);


    }

    private void showToast(String messaggio){
        Toast.makeText(this,messaggio,Toast.LENGTH_LONG).show();
    }

    private boolean emailValida(){
        String email = emailET.getText().toString();

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    private boolean passwordValida(){
        String password = passwordET.getText().toString();

        if(password.length() > 6)
            return true;
        else
            return false;
    }

    private void doLogin(){
        if(emailValida() && passwordValida()){
            showToast("login effettuato");
        }
        else{
            showToast("login non effettuato");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login_BT){
            //TODO do login
            doLogin();
        }else if (v.getId()==R.id.registrazione_BT){
            //TODO go to register activity
            Intent intent = new Intent(this, Registrazione_activity.class);
            startActivity(intent);
        }
    }
}
