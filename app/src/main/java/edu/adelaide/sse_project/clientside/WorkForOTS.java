package edu.adelaide.sse_project.clientside;

import android.Manifest;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class WorkForOTS extends AppCompatActivity {

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
    private ProgressDialog mDialog;
    private static final int unique_id = 3193;

    Button hire;
    private FirebaseAuth firebaseAuth;
    RadioGroup m_gender;
    RadioButton mgenderoption;
    DatabaseReference firebaseDatabase;
    FirebaseDatabase db = FirebaseDatabase.getInstance(Home_Page.DATABASE_URL);
    String profiletype;
    String id;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    ".{4,}" +               //at least 4 characters
                    "$");

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(WorkForOTS.this, Home_Page.class));
                } else if (itemId == R.id.nav_prof) {
                    startActivity(new Intent(WorkForOTS.this, ProfileActivity.class));
                } else if (itemId == R.id.nav_logout) {//                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(WorkForOTS.this, LoginActivity.class));
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_for_ots);
        hire = findViewById(R.id.btn_hireperson);
        m_gender = findViewById(R.id.rg_skill);
        firebaseDatabase = FirebaseDatabase.getInstance(Home_Page.DATABASE_URL).getReference("Employee Service Profile(SERVER SIDE)");

        BottomNavigationView navigation = findViewById(R.id.main_nav);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        firebaseAuth = FirebaseAuth.getInstance();

        txtfirstname = findViewById(R.id.txt_firstname);
        txtlastname = findViewById(R.id.txt_lastname);
        txtemail = findViewById(R.id.txt_email);
        txtpassword = findViewById(R.id.txt_password);
        txtgender = findViewById(R.id.txt_gender);
        txtaddress = findViewById(R.id.txt_address);
        txtpincode = findViewById(R.id.txt_pincode);
        txtmobileno = findViewById(R.id.txt_mobileno);
        txtworkinhhours = findViewById(R.id.txt_workinghours);
        hire = findViewById(R.id.btn_hireperson);
        final FirebaseDatabase database = FirebaseDatabase.getInstance(Home_Page.DATABASE_URL);
        database.getReference("Employee Service Profile(SERVER SIDE)");
        m_gender.setOnCheckedChangeListener((group, checkedId) -> {

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

        });
        mDialog = new ProgressDialog(WorkForOTS.this);
        mDialog.setMessage("Please wait...");

        boolean notificationPermissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        if (notificationPermissionGranted)
            addHireClickListener(true);
        else
            requestPermissions(new String[] { Manifest.permission.POST_NOTIFICATIONS }, unique_id);

    }

    void addHireClickListener(boolean notificationsEnabled) {
        hire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    String user_email = null;
                    if (txtemail.getEditText() != null) {
                        user_email = txtemail.getEditText().getText().toString().trim();
                    }
                    String user_password = null;
                    if (txtpassword.getEditText() != null) {
                        user_password = txtpassword.getEditText().getText().toString().trim();
                    }
                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            sendData();
                            notificationcall();
                        } else {
                            Toast.makeText(WorkForOTS.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }


                    });

                }
            }

            private Boolean validate() {
                boolean result = false;

                if (txtfirstname.getEditText() != null) {
                    firstname = txtfirstname.getEditText().getText().toString();
                }
                if (txtlastname.getEditText() != null) {
                    lastname = txtlastname.getEditText().getText().toString();
                }
                if (txtemail.getEditText() != null) {
                    email = txtemail.getEditText().getText().toString();
                }
                if (txtpassword.getEditText() != null) {
                    password = txtpassword.getEditText().getText().toString();
                }
                if (txtgender.getEditText() != null) {
                    gender = txtgender.getEditText().getText().toString();
                }
                if (txtaddress.getEditText() != null) {
                    address = txtaddress.getEditText().getText().toString();
                }
                if (txtpincode.getEditText() != null) {
                    pincode = txtpincode.getEditText().getText().toString();
                }
                if (txtmobileno.getEditText() != null) {
                    mobilno = txtmobileno.getEditText().getText().toString();
                }
                if (txtworkinhhours.getEditText() != null) {
                    workinhours = txtworkinhhours.getEditText().getText().toString();
                }
                raiobtn = mgenderoption.getText().toString();

                if (!validatefistname() | !validatelastname() | !validateemail() | !validategpassword() | !validategender() | !validateaddress() | !validatePincode() | !validatemobileno() | !validateworkinghours()) {
                    Toasty.info(WorkForOTS.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                } else {
                    result = true;
                }
                return result;
            }

            private void sendData() {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(Home_Page.DATABASE_URL);
                DatabaseReference myRef = firebaseDatabase.getReference("Employee Service Profile(SERVER SIDE)");
                mDialog.dismiss();
                User user = null;
                if (txtworkinhhours.getEditText() != null) {
                    user = new User(txtfirstname.getEditText().getText().toString(), txtlastname.getEditText().getText().toString(), txtemail.getEditText().getText().toString(), txtpassword.getEditText().getText().toString(), txtgender.getEditText().getText().toString(), txtmobileno.getEditText().getText().toString(), txtaddress.getEditText().getText().toString(), txtpincode.getEditText().getText().toString(), txtworkinhhours.getEditText().getText().toString(), profiletype);
                }
                myRef.push().setValue(user);
                Toasty.success(WorkForOTS.this, "HIred Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(WorkForOTS.this, Home_Page.class));
                finish();
            }

            private Boolean validatefistname() {
                String firstnameinput = null;
                if (txtfirstname.getEditText() != null) {
                    firstnameinput = txtfirstname.getEditText().getText().toString().trim();
                }
                if (firstnameinput.isEmpty()) {
                    txtfirstname.setError("First name field can't be empty");
                    return false;
                } else if (firstnameinput.length() > 15) {
                    txtfirstname.setError("First name too long");
                    return false;
                } else {
                    txtfirstname.setError(null);
                    return true;
                }
            }

            private Boolean validatelastname() {
                String lastnameinput = null;
                if (txtlastname.getEditText() != null) {
                    lastnameinput = txtlastname.getEditText().getText().toString().trim();
                }
                if (lastnameinput.isEmpty()) {
                    txtlastname.setError("Last name field can't be empty");
                    return false;
                } else if (lastnameinput.length() > 15) {
                    txtlastname.setError("Last name too long");
                    return false;
                } else {
                    txtlastname.setError(null);
                    return true;
                }
            }

            private Boolean validateemail() {
                String emailinput = null;
                if (txtemail.getEditText() != null) {
                    emailinput = txtemail.getEditText().getText().toString().trim();
                }
                if (emailinput.isEmpty()) {
                    txtemail.setError("Email address field can't be empty");
                    return false;

                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailinput).matches()) {
                    txtemail.setError("Please enter a valid email address");
                    return false;
                } else {
                    txtemail.setError(null);
                    return true;
                }
            }

            private Boolean validategpassword() {
                String passwordinput = null;
                if (txtpassword.getEditText() != null) {
                    passwordinput = txtpassword.getEditText().getText().toString().trim();
                }
                if (passwordinput.isEmpty()) {
                    txtpassword.setError("Password field can't be empty");
                    return false;
                } else if (passwordinput.length() < 10) {
                    txtpassword.setError("Password field is too weak");
                    return false;
                } else if (!PASSWORD_PATTERN.matcher(passwordinput).matches()) {
                    txtpassword.setError("Password should contains special character");
                    return false;
                } else {
                    txtpassword.setError(null);
                    return true;
                }
            }

            private Boolean validategender() {
                String genderinput = null;
                if (txtgender.getEditText() != null) {
                    genderinput = txtgender.getEditText().getText().toString().trim();
                }
                if (genderinput.isEmpty()) {
                    txtgender.setError("Gender field can't be empty");
                    return false;

                } else {
                    txtgender.setError(null);
                    return true;
                }
            }

            private Boolean validateaddress() {
                String addressinput = null;
                if (txtaddress.getEditText() != null) {
                    addressinput = txtaddress.getEditText().getText().toString();
                }
                if (addressinput.isEmpty()) {
                    txtaddress.setError("Address field can't be empty");
                    return false;
                } else {
                    txtaddress.setError(null);
                    return true;
                }
            }

            private Boolean validatePincode() {
                String picodeinput = null;
                if (txtpincode.getEditText() != null) {
                    picodeinput = txtpincode.getEditText().getText().toString().trim();
                }
                if (picodeinput.isEmpty()) {
                    txtpincode.setError("Pincode field can't be empty");
                    return false;
                } else if (picodeinput.length() != 4) {
                    txtpincode.setError("Pincode should contain only 4 digits");
                    return false;
                } else {
                    txtpincode.setError(null);
                    return true;
                }
            }

            private Boolean validatemobileno() {
                String mobileinput = null;
                if (txtmobileno.getEditText() != null) {
                    mobileinput = txtmobileno.getEditText().getText().toString().trim();
                }
                if (mobileinput.isEmpty()) {
                    txtmobileno.setError("Mobile no field can't be empty");
                    return false;
                } else if (mobileinput.length() > 10) {
                    txtmobileno.setError("mobile number should contain only 10 digits");
                    return false;

                } else if (mobileinput.length() < 10) {
                    txtmobileno.setError("mobile number should contain less than 10 digits");
                    return false;
                } else {
                    txtmobileno.setError(null);
                    return true;
                }
            }

            private Boolean validateworkinghours() {
                String workinghourinput = null;
                if (txtworkinhhours.getEditText() != null) {
                    workinghourinput = txtworkinhhours.getEditText().getText().toString();
                }
                if (workinghourinput.isEmpty()) {
                    txtworkinhhours.setError("Workinh hours field can't be empty");
                    return false;
                } else if (workinghourinput.length() > 10) {
                    txtworkinhhours.setError("Not valid for more hours");
                    return false;

                } else {
                    txtworkinhhours.setError(null);
                    return true;
                }

            }

            public void notificationcall() {
                if (notificationsEnabled) {
                    NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(WorkForOTS.this)
                            .setDefaults(NotificationCompat.DEFAULT_ALL)
                            .setSmallIcon(R.drawable.ic_action_prof)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_prof))
                            .setContentTitle("OTS")
                            .setContentText("You will be informed soon....");
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(1, notificationbuilder.build());
                }
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == unique_id) {
            // If request is cancelled, the result arrays are empty.
            addHireClickListener(grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED);
        }
    }
}

