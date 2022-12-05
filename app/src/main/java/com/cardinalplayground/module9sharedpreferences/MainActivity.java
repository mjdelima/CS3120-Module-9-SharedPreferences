package com.cardinalplayground.module9sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUser, etPass;
    private Button btnLogin;
    private Switch swtRemember;

    //constants
    public final static String USERNAME = "username";
    public final static String PASSWORD = "password";
    public final static String REMEMBER_ME = "remember_me";
    public final static String SHARED_PREFS = "com.cardinalplayground.module9sharedpreferences.SHARED_PREF";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPasword);
        btnLogin = findViewById(R.id.btnLogin);
        swtRemember = findViewById(R.id.switchRemember);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isEmpty(etPass) || isEmpty(etUser)){
                    Toast.makeText(MainActivity.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveData();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                finish();



            }
        });

        if(isRemembered()){
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }


    }

    private boolean isEmpty(EditText editText){
        return editText.getText().toString().trim().length() == 0;
    }


    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, etUser.getText().toString());
        editor.putString(PASSWORD, etPass.getText().toString());
        editor.putBoolean(REMEMBER_ME, swtRemember.isChecked());
        editor.apply();
    }

    private Boolean isRemembered(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getBoolean(REMEMBER_ME, false);
    }


}