package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends Activity {

    private ImageView profilePic;
    private TextView profileFirstName,profileLastName,profileEmail,profileMobileNo,profileAddress,profilePincode,profileid;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String userkey;


   private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(ProfileActivity.this, Home_Page.class));
            } else if (itemId == R.id.nav_prof) {
                progressDialog.show();
            } else if (itemId == R.id.nav_work) {
                Intent intent1 = new Intent(ProfileActivity.this, WorkForOTS.class);
                startActivity(intent1);
            } else if (itemId == R.id.nav_logout) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        CircleImageView circleImageView =findViewById(R.id.circleimage);
        String curl = "https://www.blendedlearning.org/wp-content/uploads/2015/01/phil-profile-circle.png";
        Picasso.with(this).load(curl).into(circleImageView);


        profileFirstName= findViewById(R.id.tvfistname);
        profileLastName= findViewById(R.id.tvlastname);
        profileEmail= findViewById(R.id.tvemail);
        profileMobileNo= findViewById(R.id.tvmobileno);
        profileAddress= findViewById(R.id.tvaddress);
        profilePincode= findViewById(R.id.tvpincode);
        LinearLayout about=findViewById(R.id.about);
        LinearLayout help=findViewById(R.id.help);
        LinearLayout work=findViewById(R.id.orders);
        BottomNavigationView navigation = findViewById(R.id.main_nav);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        Menu menu=navigation.getMenu();
        MenuItem menuItem=menu.getItem(1);
        menuItem.setChecked(true);
        work.setOnClickListener(v -> {
            Intent intent=new Intent(ProfileActivity.this, OrderID.class);
            startActivity(intent);
        });
        about.setOnClickListener(v -> {
            Intent intent=new Intent(ProfileActivity.this, About.class);
            startActivity(intent);
        });
        help.setOnClickListener(v -> {
            Intent intent=new Intent(ProfileActivity.this, Help.class);
            startActivity(intent);
        });


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(Home_Page.DATABASE_URL);


        final DatabaseReference databaseReference = firebaseDatabase.getReference("Registered Users").child(firebaseAuth.getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                if (userProfile != null) {
                    profileFirstName.setText(userProfile.userName+"\t");
                }
                profileLastName.setText(userProfile.usreLastname);
                profileEmail.setText("Email :" + userProfile.userEmail);
                profileMobileNo.setText("Mobile No :" + userProfile.userMobileno);
                profileAddress.setText("Address :" + userProfile.userAddress);
                profilePincode.setText("Pincode :" + userProfile.userPincode);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(ProfileActivity.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
