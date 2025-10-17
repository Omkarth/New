package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class PasswordActivity extends Activity {

    private EditText passwordEmail;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_password);

        passwordEmail= findViewById(R.id.etEmail);
        Button paswordReset = findViewById(R.id.btnPasswordReset);
        firebaseAuth=FirebaseAuth.getInstance();

        paswordReset.setOnClickListener(v -> {

            String userEmail=passwordEmail.getText().toString().trim();//trim is used to cleat all wide spaces at the begining and also at the input

            if(userEmail.isEmpty()){
                Toasty.warning(PasswordActivity.this, "Please enter your registered Email ID", Toast.LENGTH_SHORT).show();
            }else{
                firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(PasswordActivity.this,"Password Reset mail has been sent!",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(PasswordActivity.this, LoginActivity.class));

                    }else{
                        Toast.makeText(PasswordActivity.this,"This Email ID is not Registered",Toast.LENGTH_SHORT).show();

                    }
                });
            }


        });

    }
}
