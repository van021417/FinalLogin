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

public class Reg extends AppCompatActivity {
TextInputEditText textInputEditTextname, textInputEditTextemail, textInputEditTextpassword;
Button button1;
Button btnLogRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputEditTextname=findViewById(R.id.name);
        textInputEditTextemail=findViewById(R.id.email);
        textInputEditTextpassword=findViewById(R.id.password);
        button1=findViewById(R.id.button1);
        btnLogRegister=findViewById(R.id.btnLogRegister);


        btnLogRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Log.class);
                startActivity(intent);
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name, email, password;

                name = String.valueOf(textInputEditTextname.getText());
                email = String.valueOf(textInputEditTextemail.getText());
                password = String.valueOf(textInputEditTextpassword.getText());

                if (!name.equals("") && !email.equals("") && !password.equals("")) {

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "name";
                            field[1] = "email";
                            field[2] = "password";
                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = name;
                            data[1] = email;
                            data[2] = password;
                            PutData putData = new PutData("http://192.168.31.112/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent((getApplicationContext()), Log.class);
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
