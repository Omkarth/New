package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class RegistrationActivity extends Activity {

    private TextInputLayout userName,userLastName,userEmail,userPassword,userMobileno,userAddress,userPincode;
    String name,lastname,email,password,mobileno,address,pincode;
    Button regbutton;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    ".{4,}" +               //at least 4 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registration);

        TextView txtlogin= findViewById(R.id.login);
        txtlogin.setOnClickListener(v -> {
            Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
            startActivity(intent);
        });
        setupUiViews();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance(Home_Page.DATABASE_URL);

        regbutton.setOnClickListener(v -> {
            if(validate()){
                String user_email = null;
                if (userEmail.getEditText() != null) {
                    user_email = userEmail.getEditText().getText().toString().trim();
                }
                String user_password = null;
                if (userPassword.getEditText() != null) {
                    user_password = userPassword.getEditText().getText().toString().trim();
                }
                firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(task -> {

                    if(task.isSuccessful()){
                        sendEmailVerification();
                       // sendUserData();
                    }else{
                        Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }


                });

            }
        });



    }
    private void setupUiViews(){
        userName = findViewById(R.id.etUserName);
        userLastName = findViewById(R.id.etUserLastName);
        userEmail= findViewById(R.id.etUserEmail);
        userPassword= findViewById(R.id.etUserPassword);
        userMobileno= findViewById(R.id.etMoibleno);
        userAddress= findViewById(R.id.etAddress);
        userPincode= findViewById(R.id.etPincode);
        regbutton= findViewById(R.id.btnRegister);

    }
    private Boolean validate(){
        boolean result=false;

        if (userName.getEditText() != null) {
            name=userName.getEditText().getText().toString();
        }
        if (userLastName.getEditText() != null) {
            lastname=userLastName.getEditText().getText().toString();
        }
        if (userEmail.getEditText() != null) {
            email=userEmail.getEditText().getText().toString();
        }
        if (userPassword.getEditText() != null) {
            password=userPassword.getEditText().getText().toString();
        }
        if (userMobileno.getEditText() != null) {
            mobileno=userMobileno.getEditText().getText().toString();
        }
        if (userAddress.getEditText() != null) {
            address=userAddress.getEditText().getText().toString();
        }
        if (userPincode.getEditText() != null) {
            pincode=userPincode.getEditText().getText().toString();
        }

        if(!validatefistname() | !validatelastname() | !validateemail() | !validategpassword()  | !validateaddress() | !validatePincode() | !validatemobileno()){
            Toasty.info(this,"Please enter all details",Toast.LENGTH_SHORT).show();
        }else {
            result=true;
        }
        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    sendUserData();
                    Toasty.success(RegistrationActivity.this,"Registered Successfully, verification mail has been sent", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));

                }else{
                    progressDialog.dismiss();
                    Toasty.error(RegistrationActivity.this,"Verification mail has'nt been sent", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(Home_Page.DATABASE_URL);
        DatabaseReference myRef = firebaseDatabase.getReference("Registered Users").child(firebaseAuth.getUid());
       UserProfile userProfile=new UserProfile(name ,lastname ,email ,password ,mobileno ,address ,pincode);
        myRef.setValue(userProfile);

    }
    private Boolean validatefistname() {
        String firstnameinput = null;
        if (userName.getEditText() != null) {
            firstnameinput = userName.getEditText().getText().toString().trim();
        }
        if (firstnameinput.isEmpty()) {
            userName.setError("First name field can't be empty");
            return false;
        } else if (firstnameinput.length() > 15) {
            userName.setError("First name too long");
            return false;
        }
        else {
            userName.setError(null);
            return true;
        }
    }
    private Boolean validatelastname() {
        String firstnameinput = null;
        if (userLastName.getEditText() != null) {
            firstnameinput = userLastName.getEditText().getText().toString().trim();
        }
        if (firstnameinput.isEmpty()) {
            userLastName.setError("Last name name field can't be empty");
            return false;
        } else if (firstnameinput.length() > 15) {
            userLastName.setError("Last name name too long");
            return false;
        }
        else {
            userLastName.setError(null);
            return true;
        }
    }
    private Boolean validateemail() {
        String emailinput = null;
        if (userEmail.getEditText() != null) {
            emailinput = userEmail.getEditText().getText().toString().trim();
        }
        if (emailinput.isEmpty()) {
            userEmail.setError("Email address field can't be empty");
            return false;

        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()) {
            userEmail.setError("Please enter a valid email address");
            return false;
        }else {
            userEmail.setError(null);
            return true;
        }
    }
    private Boolean validategpassword() {
        String passwordinput = null;
        if (userPassword.getEditText() != null) {
            passwordinput = userPassword.getEditText().getText().toString().trim();
        }
        if (passwordinput.isEmpty()) {
            userPassword.setError("Password field can't be empty");
            return false;
        }else if(passwordinput.length() < 10)
        {
            userPassword.setError("Password field is too weak");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordinput).matches()) {
            userPassword.setError("Password should contains special character");
            return false;
        }

        else {
            userPassword.setError(null);
            return true;
        }
    }
    private Boolean validateaddress() {
        String addressinput = null;
        if (userAddress.getEditText() != null) {
            addressinput = userAddress.getEditText().getText().toString();
        }
        if (addressinput.isEmpty()) {
            userAddress.setError("Address field can't be empty");
            return false;
        } else {
            userAddress.setError(null);
            return true;
        }
    }

    private Boolean validatePincode() {
        String picodeinput = null;
        if (userPincode.getEditText() != null) {
            picodeinput = userPincode.getEditText().getText().toString().trim();
        }
        if (picodeinput.isEmpty()) {
            userPincode.setError("Pincode field can't be empty");
            return false;
        } else if (picodeinput.length() != 6) {
            userPincode.setError("Pincode should contain only 6 digits");
            return false;
        } else {
            userPincode.setError(null);
            return true;
        }
    }

    private Boolean validatemobileno() {
        String mobileinput = null;
        if (userMobileno.getEditText() != null) {
            mobileinput = userMobileno.getEditText().getText().toString().trim();
        }
        if (mobileinput.isEmpty()) {
            userMobileno.setError("Mobile no field can't be empty");
            return false;
        } else if (mobileinput.length() > 10 ) {
            userMobileno.setError("mobile number should contain only 10 digits");
            return false;

        } else if (mobileinput.length() < 10) {
            userMobileno.setError("mobile number should contain less than 10 digits");
            return false;
        } else {
            userMobileno.setError(null);
            return true;
        }
    }
}

