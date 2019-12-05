package com.example.bird;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {



    EditText email, password;

    Button bL, bR;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.editTextemail);
        password = findViewById(R.id.editTextpass);

        bL = findViewById(R.id.buttonL);
        bR = findViewById(R.id.buttonR);

        bL.setOnClickListener(this);
        bR.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {

        String e = email.getText().toString();

        String p = password.getText().toString();

        if (view == bL) {

            mAuth.signInWithEmailAndPassword(e, p)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent new1 = new Intent(Login.this, Landing.class);

                                startActivity(new1);
                            } else {
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });



        }

        else if (view == bR) {

            mAuth.createUserWithEmailAndPassword(e, p)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Login.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                                Intent new1 = new Intent(Login.this, Landing.class);

                                startActivity(new1);



                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });





        }


    }
}
