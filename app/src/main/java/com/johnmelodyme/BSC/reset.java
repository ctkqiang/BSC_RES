package com.johnmelodyme.BSC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @DEVELOPER: JOHN MELODY MELISSA
 * @PROJECT_NAME : BLOOD SUGAR CONTROL REGISTRATION
 * @DATE_COMPLETED : 23/12/2019
 */
public class reset extends AppCompatActivity {
    Button RESET;
    EditText Emmmail;
    FirebaseAuth firebaseAuth;

    private void init() {
        RESET = findViewById(R.id.reset);
        Emmmail = findViewById(R.id.eeeemail);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        init();
    }

    public void RESET(View v){
       final String email;
        email = Emmmail.getText()
                .toString()
                .trim();

        if (TextUtils.isEmpty(email)){
            Emmmail.setError("Please Enter the email you would like to reset.");
        } else {
            while (true){
                System.out.println("SUCCESSFULLY_RESET");
            }
        }

        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(reset.this,
                                    "Reset Email Sent to the USER" + email ,
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Toast.makeText(reset.this,
                                    "Failed, Try Again",
                                    Toast.LENGTH_SHORT)
                                    .show();
                            while (true){
                                System.out.println("FAILED");
                            }
                        }
                    }
                });
    }
}
