package edu.adelaide.sse_project.workerapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  implements View.OnClickListener  {

    private BottomNavigationView mainbottomnav;
    Button  btnrequests;
    Switch online;
    FirebaseDatabase db;
    Button btnlogout;
    FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;
    String servicersid;
    private BottomSheetDialog bottomSheetDialog;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        btnrequests = view.findViewById(R.id.btn_requests);

        firebaseAuth = FirebaseAuth.getInstance();
        btnlogout = view.findViewById(R.id.btnlogout);
        db = FirebaseDatabase.getInstance();
        online = view.findViewById(R.id.online);
        online.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (online.isChecked()) {
                    servicersid = firebaseAuth.getCurrentUser().getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference("Servicers Available").child(firebaseAuth.getUid());
                    databaseReference.setValue("online");
                    //databaseReference2 = FirebaseDatabase.getInstance().getReference("Servicers Available").child(servicersid);
                    Snackbar.make(buttonView, "Your are online now", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    status("online");
                    // Toast.makeText(Home_Page.this, ""+databaseReference2, Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.removeValue();
                    Snackbar.make(buttonView, "Your are offline now", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    status("offline");


                }
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getActivity(), Login.class));
                firebaseAuth.signOut();
                getActivity().finish();
            }
        });

      /*  btn=findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Page.this,ProfileActivity.class));
            }
        });*/

        bottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);

        Button maid = bottomSheetDialogView.findViewById(R.id.maid);
        Button plumber = bottomSheetDialogView.findViewById(R.id.plumber);
        Button elect = bottomSheetDialogView.findViewById(R.id.elect);
        Button chef = bottomSheetDialogView.findViewById(R.id.chef);

        maid.setOnClickListener(this);
        plumber.setOnClickListener(this);
        elect.setOnClickListener(this);
        chef.setOnClickListener(this);

        btnrequests = view.findViewById(R.id.btn_requests);

        firebaseAuth = FirebaseAuth.getInstance();
        btnlogout = view.findViewById(R.id.btnlogout);
        db = FirebaseDatabase.getInstance();
        online = view.findViewById(R.id.online);
        online.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (online.isChecked()) {
                    servicersid = firebaseAuth.getCurrentUser().getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference("Servicers Available").child(firebaseAuth.getUid());
                    databaseReference.setValue("online");
                    //databaseReference2 = FirebaseDatabase.getInstance().getReference("Servicers Available").child(servicersid);
                    Snackbar.make(buttonView, "Your are online now", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    status("online");
                    // Toast.makeText(Home_Page.this, ""+databaseReference2, Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.removeValue();
                    Snackbar.make(buttonView, "Your are offline now", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    status("offline");


                }
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getActivity(), Login.class));
                firebaseAuth.signOut();
                getActivity().finish();
            }
        });


      /*  btn=findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Page.this,ProfileActivity.class));
            }
        });*/

        maid.setOnClickListener(this);
        plumber.setOnClickListener(this);
        elect.setOnClickListener(this);
        chef.setOnClickListener(this);

        Button button = view.findViewById(R.id.btn_requests);
        button.setOnClickListener(this);
        return view;

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.btn_requests:
                bottomSheetDialog.show();
                break;

            case R.id.maid:
                startActivity(new Intent(getActivity(), Requests_Apply.class));
                break;

            case R.id.plumber:
                startActivity(new Intent(getActivity(), Request_ApplyPlumber.class));
                break;

            case R.id.elect:
                startActivity(new Intent(getActivity(), Request_Apply_Electrician.class));
                break;

            case R.id.chef:
                startActivity(new Intent(getActivity(), Request_Apply_Chef.class));
                break;

        }




    }

    @Override
    public void onResume() {
        super.onResume();
        status("online");
        servicersid = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Servicers Available").child(servicersid).child("Status");
        databaseReference.setValue("online");
        Toast.makeText(getActivity(), "Welcome to OTS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        status("offline");
        databaseReference.removeValue();
    }


    private void logout() {
    }




    /*  private void manageconnections()
      {

          final DatabaseReference connectionReference = db.getReference().child("Employee Service Profile(SERVER SIDE)").child(firebaseAuth.getUid());
          final DatabaseReference lastconnected = db.getReference().child("Employee Service Profile(SERVER SIDE)").child(firebaseAuth.getUid()).child("lastconnected").child("Status");
          final DatabaseReference infoconnected = db.getReference(".info/connected");

          infoconnected.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                  boolean connected = dataSnapshot.getValue(Boolean.class);

                  if(connected)
                  {
                      DatabaseReference con = connectionReference.child("Status");
                      con.setValue(true);
                      con.onDisconnect().setValue(false);

                  }

              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
                  Toast.makeText(Home_Page.this,"Failed",Toast.LENGTH_SHORT).show();

              }
          });
      }*/
    private void status(String status)
    {
        DatabaseReference databaseReference  = FirebaseDatabase.getInstance().getReference("Employee Service Profile(SERVER SIDE)").child(firebaseAuth.getUid());
        HashMap<String , Object> hashMap =  new HashMap<>();
        hashMap.put("Status", status);

        databaseReference.updateChildren(hashMap);

    }




}
