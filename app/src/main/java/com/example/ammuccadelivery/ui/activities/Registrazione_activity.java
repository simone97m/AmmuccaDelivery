package com.example.ammuccadelivery.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ammuccadelivery.R;

import java.util.regex.Pattern;

public class Registrazione_activity extends AppCompatActivity implements View.OnClickListener,TextWatcher{

    EditText emailET,passwordET,passwordETconf;
    Button registrazioneBT, btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailET = findViewById(R.id.email_ET);
        passwordET = findViewById(R.id.password_ET);
        passwordETconf = findViewById(R.id.password_ET_conferma);
        registrazioneBT = findViewById(R.id.registrazione_BT);

        registrazioneBT.setOnClickListener(this);
        btn = findViewById(R.id.registrazione_BT);
        //btn.setEnabled(false);

          emailET.addTextChangedListener(this);
          passwordET.addTextChangedListener(this);
          passwordETconf.addTextChangedListener(this);
    }

    private boolean emailValida(){
        String email = emailET.getText().toString();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private boolean passwordValida(){
        String password = passwordET.getText().toString();
        if (password.length() > 6)
            return true;
        else
            return false;

    }

    private boolean confrontaPassword(){
        String password = passwordET.getText().toString();
        String password2 = passwordETconf.getText().toString();
        if (password.equals(password2)) {
            return true;
        }
        return false;
    }

    private void showToast(String messaggio){
        Toast.makeText(this,messaggio,Toast.LENGTH_LONG).show();
    }

    private void doRegistrazione(){
        if (emailValida() && passwordValida() && confrontaPassword()){
            showToast("Registrazione effettuata");
        }
        else{
            showToast("Registrazione non effettuata");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.registrazione_BT){
            doRegistrazione();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (emailValida() && passwordValida() && confrontaPassword()) {
            btn.setEnabled(true);
        }else{
            btn.setEnabled(false);
        }

    }  @Override
        public void afterTextChanged (Editable s){
            if (emailValida() && passwordValida() && confrontaPassword()) {
                btn.setEnabled(true);
            }
            else{
                btn.setEnabled(false);
            }
        }

    }