package edu.adelaide.sse_project.workerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Home_Page extends AppCompatActivity{


    BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragmentl;
    private ProfileFragment profileFragment;
    private OrdersFragment ordersFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        bottomNavigationView=findViewById(R.id.bottom_nav);
        homeFragmentl=new HomeFragment();
        profileFragment=new ProfileFragment();
        ordersFragment=new OrdersFragment();

        fragmentmanager(homeFragmentl);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.bottom_home:
                        fragmentmanager(homeFragmentl);
                        return true;
                    case R.id.bottom_prof:
                        fragmentmanager(profileFragment);
                        return true;
                    case R.id.bottom_orders:
                        fragmentmanager(ordersFragment);
                        return true;


                        default:
                            return false;
                }
            }
        });

    }

    public void fragmentmanager(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer,fragment);
        fragmentTransaction.commit();
    }

}