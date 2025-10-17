package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    // UI references.
    private EditText Name;
    private EditText Password;
    private Button Login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        CheckBox checkBox= findViewById(R.id.checkpassword);
        Password= findViewById(R.id.etPassword);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
            {
                Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });







       Name= findViewById(R.id.etName);
       Password= findViewById(R.id.etPassword);
       Login= findViewById(R.id.btnLogin);
        TextView btn = findViewById(R.id.txtbtn);
        TextView forgot = findViewById(R.id.forgotpassword);
       Password.setEnabled(false);
       Login.setEnabled(false);

       Name.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

               Password.setEnabled(!s.toString().isEmpty());
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
       Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Login.setEnabled(!s.toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       firebaseAuth = FirebaseAuth.getInstance();
       progressDialog = new ProgressDialog(this);
       //checks Logged in to the app or not
        FirebaseUser user = firebaseAuth.getCurrentUser();

            if(user != null){
                finish();
                startActivity(new Intent(LoginActivity.this,Home_Page.class));

            }


            Login.setOnClickListener(v -> validate(Name.getText().toString(), Password.getText().toString()));
       btn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,RegistrationActivity.class)));
        forgot.setOnClickListener(v -> {
            Intent forgorpasswordlogin=new Intent(LoginActivity.this,PasswordActivity.class);
            startActivity(forgorpasswordlogin);

        });
    }
        private void validate(String userName , String userPassword) {

            progressDialog.setMessage("Verifying Your Email Account");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    checkEmailVerification();
                } else {
                    Toasty.error(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            });

     }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        boolean emailflag = false;
        if (firebaseUser != null) {
            emailflag = firebaseUser.isEmailVerified();
        }

        if (emailflag) {
            finish();
            Toasty.success(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, Home_Page.class));
        }else{
            Toasty.info(this,"Please verify your email", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

}






