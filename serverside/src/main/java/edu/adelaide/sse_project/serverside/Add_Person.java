package edu.adelaide.sse_project.serverside;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class Add_Person extends Activity {


    String firstname,lastname,email,password,gender,address,pincode,mobilno,workinhours,raiobtn;

    private TextInputLayout txtfirstname;
    private TextInputLayout txtlastname;
    private TextInputLayout txtemail;
    private TextInputLayout txtpassword;
    private TextInputLayout txtgender;
    private TextInputLayout txtaddress;
    private TextInputLayout txtpincode;
    private TextInputLayout txtmobileno;
    private TextInputLayout txtworkinhhours;
    private TextInputLayout txtprofiltype;
    Button hire;
    RadioGroup m_gender;
    RadioButton mgenderoption;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String profiletype;
    ProgressDialog mDialog;
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
        setContentView(R.layout.activity_add__person);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance(MainActivity.DATABASE_URL);
        mDialog=new ProgressDialog(Add_Person.this);
        mDialog.setMessage("Please wait...");


        m_gender = findViewById(R.id.rg_skill);
        txtfirstname=findViewById(R.id.txt_firstname);
        txtlastname=findViewById(R.id.txt_lastname);
        txtemail=findViewById(R.id.txt_email);
        txtpassword=findViewById(R.id.txt_password);
        txtgender=findViewById(R.id.txt_gender);
        txtaddress=findViewById(R.id.txt_address);
        txtpincode=findViewById(R.id.txt_pincode);
        txtmobileno=findViewById(R.id.txt_mobileno);
        txtworkinhhours=findViewById(R.id.txt_workinghours);
      //  txtprofiltype=findViewById(R.id.txt_profile);
        hire=findViewById(R.id.btn_hireperson);
        final FirebaseDatabase database=FirebaseDatabase.getInstance(MainActivity.DATABASE_URL);
        final DatabaseReference table_user=database.getReference("Employee Service Profile(SERVER SIDE)");
        m_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                mgenderoption = m_gender.findViewById(checkedId);

                if (checkedId == R.id.rb_maid) {
                    profiletype = mgenderoption.getText().toString();
                } else if (checkedId == R.id.rb_elect) {
                    profiletype = mgenderoption.getText().toString();
                } else if (checkedId == R.id.rb_plumber) {
                    profiletype = mgenderoption.getText().toString();
                } else if (checkedId == R.id.rb_chef) {
                    profiletype = mgenderoption.getText().toString();
                }

            }
        });

        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String user_email = txtemail.getEditText().getText().toString().trim();
                    String user_password = txtpassword.getEditText().getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                 sendData();
                            }else{
                                Toast.makeText(Add_Person.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                }











               // Toasty.success(Add_Person.this,"Form succefully filled",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private Boolean validate(){
        Boolean result=false;

        firstname=txtfirstname.getEditText().getText().toString();
        lastname=txtlastname.getEditText().getText().toString();
        email=txtemail.getEditText().getText().toString();
        password=txtpassword.getEditText().getText().toString();
        gender=txtgender.getEditText().getText().toString();
        address=txtaddress.getEditText().getText().toString();
        pincode=txtpincode.getEditText().getText().toString();
        mobilno=txtmobileno.getEditText().getText().toString();
        workinhours=txtworkinhhours.getEditText().getText().toString();
        raiobtn=mgenderoption.getText().toString();

        if(!validatefistname() | !validatelastname() | !validateemail() | !validategpassword()  | !validategender() | !validateaddress() | !validatePincode() | !validatemobileno() | !validateworkinghours()){
            Toasty.info(this,"Please enter all details",Toast.LENGTH_SHORT).show();
        }else {
            result=true;
        }
        return result;
    }
    private void sendData()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(MainActivity.DATABASE_URL);
        DatabaseReference myRef = firebaseDatabase.getReference("Employee Service Profile(SERVER SIDE)").child(firebaseAuth.getUid());
        mDialog.dismiss();
        User user = new User(txtfirstname.getEditText().getText().toString(), txtlastname.getEditText().getText().toString(),txtemail.getEditText().getText().toString(),txtpassword.getEditText().getText().toString(), txtgender.getEditText().getText().toString(), txtmobileno.getEditText().getText().toString(), txtaddress.getEditText().getText().toString(), txtpincode.getEditText().getText().toString(), txtworkinhhours.getEditText().getText().toString(), profiletype.toString());
        myRef.setValue(user);
        Toasty.success(Add_Person.this,"HIred Successfully",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Add_Person.this,Home_page.class));
        finish();
    }
    private Boolean validatefistname(){
        String firstnameinput=txtfirstname.getEditText().getText().toString().trim();
        if(firstnameinput.isEmpty()){
            txtfirstname.setError("First name field can't be empty");
            return false;
        }else if(firstnameinput.length()>15){
            txtfirstname.setError("First name too long");
            return false;
        }else{
            txtfirstname.setError(null);
            return true;
        }
    }
    private Boolean validatelastname(){
        String lastnameinput=txtlastname.getEditText().getText().toString().trim();
        if(lastnameinput.isEmpty()){
            txtlastname.setError("Last name field can't be empty");
            return false;
        }else if(lastnameinput.length()>15){
            txtlastname.setError("Last name too long");
            return false;
        }else {
            txtlastname.setError(null);
            return true;
        }
    }
    private Boolean validateemail() {
        String emailinput = txtemail.getEditText().getText().toString().trim();
        if (emailinput.isEmpty()) {
            txtemail.setError("Email address field can't be empty");
            return false;

        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()) {
            txtemail.setError("Please enter a valid email address");
            return false;
        }else {
            txtemail.setError(null);
            return true;
        }
    }
    private Boolean validategpassword() {
        String passwordinput = txtpassword.getEditText().getText().toString().trim();
        if (passwordinput.isEmpty()) {
            txtpassword.setError("Password field can't be empty");
            return false;
        }else if(passwordinput.length() < 10)
        {
            txtpassword.setError("Password field is too weak");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordinput).matches()) {
            txtpassword.setError("Password should contains special character");
            return false;
        }

        else {
            txtpassword.setError(null);
            return true;
        }
    }
    private Boolean validategender(){
        String genderinput=txtgender.getEditText().getText().toString().trim();
        if(genderinput.isEmpty()){
            txtgender.setError("Gender field can't be empty");
            return false;
        }else {
            txtgender.setError(null);
            return true;
        }
    }
    private Boolean validateaddress(){
        String addressinput=txtaddress.getEditText().getText().toString().trim();
        if(addressinput.isEmpty()){
            txtaddress.setError("Address field can't be empty");
            return false;
        }else {
            txtaddress.setError(null);
            return true;
        }
    }
    private Boolean validatePincode(){
        String picodeinput=txtpincode.getEditText().getText().toString().trim();
        if(picodeinput.isEmpty()){
            txtpincode.setError("Pincode field can't be empty");
            return false;
        }else if(txtpincode.getCounterMaxLength()>4) {
            txtpincode.setError("Pincode too long");
            return false;
        }else {
            txtpincode.setError(null);
            return true;
        }
    }
    private Boolean validatemobileno(){
        String mobileinput=txtmobileno.getEditText().getText().toString().trim();
        if(mobileinput.isEmpty()){
            txtmobileno.setError("Mobile no field can't be empty");
            return false;
        } else{
            txtmobileno.setError(null);
            return true;
        }
    }
    private Boolean validateworkinghours(){
        String workinghourinput=txtworkinhhours.getEditText().getText().toString().trim();
        if(workinghourinput.isEmpty()){
            txtworkinhhours.setError("Workinh hours field can't be empty");
            return false;
        } else{
            txtworkinhhours.setError(null);
            return true;
        }

    }
}
