package com.amier.modernloginregister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Log extends AppCompatActivity {
    TextInputEditText textInputEditTextemail2, textInputEditTextpassword2;
    Button btnRegLogin;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextemail2=findViewById(R.id.email2);
        textInputEditTextpassword2=findViewById(R.id.password2);
        btnRegLogin=findViewById(R.id.btnRegLogin);
        button=findViewById(R.id.button);

        btnRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String  email, password;


                email = String.valueOf(textInputEditTextemail2.getText());
                password = String.valueOf(textInputEditTextpassword2.getText());

                if ( !email.equals("") && !password.equals("")) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "email";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = email;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.31.112/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent((getApplicationContext()), Main.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All Fields Required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }