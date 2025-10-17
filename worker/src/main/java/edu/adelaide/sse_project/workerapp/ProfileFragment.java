package edu.adelaide.sse_project.workerapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private TextView profileFirstName,profileLastName,profileemail,profileGender,profileWorkingHours,profileMobileNo,profileAddress,profilePincode,profiletypes,profileid;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String id,userkey;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileFirstName=(TextView)view.findViewById(R.id.tvfistname);
        profileLastName=(TextView)view.findViewById(R.id.tvlastname);
        profileemail=(TextView)view.findViewById(R.id.tvemail);
        profileGender=(TextView)view.findViewById(R.id.tvgender);
        profileWorkingHours=(TextView)view.findViewById(R.id.tvworkinghours);
        profileMobileNo=(TextView)view.findViewById(R.id.tvmobileno);
        profileAddress=(TextView)view.findViewById(R.id.tvaddress);
        profilePincode=(TextView)view.findViewById(R.id.tvpincode);
        profiletypes=(TextView)view.findViewById(R.id.tvprofiletype);

        firebaseAuth = FirebaseAuth.getInstance();



        String user_id=firebaseAuth.getCurrentUser().getUid();
        DatabaseReference current_user=FirebaseDatabase.getInstance().getReference().child("Employee Service Profile(SERVER SIDE)").child(firebaseAuth.getUid());
        userkey = current_user.getKey();

        /*  firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference =firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.child("Employee Service Profile(SERVER SIDE)").child(firebaseAuth.getUid());*/

        current_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                Worker worker = dataSnapshot.getValue(Worker.class);
                profileFirstName.setText("" + worker.firstname+"\t");
                profileLastName.setText("" + worker.lastname);
                profileemail.setText("Email address :" + worker.email);
                profileGender.setText("Geneder :" + worker.gender);
                profileWorkingHours.setText("Workinh Hours :" + worker.workinghours);
                profileAddress.setText("Address :" + worker.address);
                profilePincode.setText("Pincode :" + worker.pincode);
                profileMobileNo.setText("Mobile no :" + worker.mobileno);
                profiletypes.setText("Profile Type :" + worker.profiletype);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getActivity(),databaseError.getCode(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void logout(){
    }


}
