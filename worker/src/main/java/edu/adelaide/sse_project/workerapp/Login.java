package edu.adelaide.sse_project.workerapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends Activity {

    Button login;
    TextInputLayout email,password;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        login=findViewById(R.id.btnlgin);
        email=findViewById(R.id.txtemail);
        password=findViewById(R.id.txtpassord);
        firebaseAuth = FirebaseAuth.getInstance();

     /*   mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null)
                {
                    startActivity(new Intent(Login.this,Home_Page.class));

                }
            }
        };*/

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("Employee Service Profile(SERVER SIDE)");

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               progressDialog.show();
               signin();
           }
       });
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(Login.this,Home_Page.class));

        }

    }

   /* @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }*/

    private void signin()
    {
        String emailinput = email.getEditText().getText().toString().trim();
        String passwordinput = password.getEditText().getText().toString().trim();

        if(TextUtils.isEmpty(emailinput))
        {
            progressDialog.dismiss();
            email.setError("Email field should be filled");
        }else if(TextUtils.isEmpty(passwordinput))
        {
            progressDialog.dismiss();
            password.setError("Password field should be filled");
        }else if(passwordinput.length() < 8)
        {
            progressDialog.dismiss();
            password.setError("Password should contain special characters");
        }

        else {
            firebaseAuth.signInWithEmailAndPassword(emailinput,passwordinput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, "Sign in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,Home_Page.class));
                    }
                    else if(!task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }
}
