package com.johnmelodyme.BSC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @DEVELOPER: JOHN MELODY MELISSA
 * @PROJECT_NAME : BLOOD SUGAR CONTROL REGISTRATION
 * @DATE_COMPLETED : 23/12/2019
 */

public class MainActivity extends AppCompatActivity {
    TextView CLEAR;
    EditText EMAIL, NAME, PASSWORD;
    Button REGISTER, RESET;
    FirebaseAuth firebaseAuth;
    Handler handler;

    private void INIT() {

        CLEAR = findViewById(R.id.Clear_field);
        EMAIL = findViewById(R.id.email_p);
        NAME = findViewById(R.id.name);
        PASSWORD = findViewById(R.id.password);
        REGISTER = findViewById(R.id.REGISTER);
        RESET = findViewById(R.id.RESET);

        firebaseAuth = FirebaseAuth.getInstance();
        handler = new Handler();
    }

    public void onStart(){
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INIT();

        CLEAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLEAR_TEXT(); // REFER METHOD
            }
        });

        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name, email, password;
                name = NAME.getText().toString();
                email = EMAIL.getText().toString();
                password = PASSWORD.getText().toString();

                if (TextUtils.isEmpty(name)){
                    NAME.setError("Please Enter Name");
                }
                if (TextUtils.isEmpty(email)){
                    EMAIL.setError("Please Enter Email");
                }
                if (TextUtils.isEmpty(password)){
                    PASSWORD.setError("Please Enter Password");
                }
                if (password.length() < 6){
                    PASSWORD.setError("Password Must be Longer than 6 letters");
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,
                                            "ACCOUNT: " + name + "\n" + email + "created",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                    toANOTHERpage();
                                } else {
                                    Toast.makeText(MainActivity.this,
                                            "FAILED :: TRY AGAIN",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                    System.out.println("FAILED");
                                }
                            }
                        });
            }
        });

        RESET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRESET;
                toRESET = new Intent(MainActivity.this, reset.class);
                startActivity(toRESET);
            }
        });
    }

    private void toANOTHERpage() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent toDATA;
                toDATA = new Intent(MainActivity.this, Client_data.class);
                startActivity(toDATA);
            }
        }, 3000);
    }

    private void CLEAR_TEXT() {
        // CLEAR NAME::
        NAME.setText("");
        NAME.getText().clear();
        // CLEAR EMAIL::
        EMAIL.setText("");
        EMAIL.getText().clear();
        // CLEAR PASSWORD::
        PASSWORD.setText("");
        PASSWORD.getText().clear();
    }
}
