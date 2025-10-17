package edu.adelaide.sse_project.workerapp;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment {

    private static final int REQUEST_CALL = 1;
    String user_id,number;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter3;
    private List<List_requested_apply_Chef> list_requested_chefs;
    private DatabaseReference chefref,acceptref,acceptreddelete,pickup;
    private TextView text1;
    private DatabaseReference userref;
    private FirebaseAuth mAuth;
    String servicer_id,visit_user_id;
    ProgressDialog progressDialog;
    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        mAuth = FirebaseAuth.getInstance();
        servicer_id = mAuth.getCurrentUser().getUid();
        pickup = FirebaseDatabase.getInstance().getReference().child("Pickup").child("Chef Requested For Temporary");
        acceptref = FirebaseDatabase.getInstance().getReference().child("Accepted Requests").child("Chef").child(servicer_id);


      /*  acceptreddelete = FirebaseDatabase.getInstance().getReference().child("Request Pickup").child("Chef Requested For Temporary");
        userref = FirebaseDatabase.getInstance().getReference().child("Registered Users");
        chefref = FirebaseDatabase.getInstance().getReference().child("Request Pickup").child("/Chef Requested For Temporary");*/
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerorders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching your data....");
        progressDialog.show();

        return view;

    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<OrderHistory> options =
                new FirebaseRecyclerOptions.Builder<OrderHistory>()
                        .setQuery(acceptref, OrderHistory.class)
                        .build();
        FirebaseRecyclerAdapter<OrderHistory,FindFirndViewHolder> adapter = new FirebaseRecyclerAdapter< OrderHistory, FindFirndViewHolder>(options) {


            @Override
            public void onBindViewHolder(final FindFirndViewHolder viewHolder, int position, OrderHistory model) {


                user_id = getRef(position).getKey();

                pickup.child(user_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChild("chef"))
                        {
                            progressDialog.dismiss();
                            String chef = dataSnapshot.child("chef").getValue().toString();
                            String gender = dataSnapshot.child("gender").getValue().toString();
                            String date = dataSnapshot.child("date").getValue().toString();
                            String time = dataSnapshot.child("time").getValue().toString();
                            String hours = dataSnapshot.child("hours").getValue().toString();
                            String city = dataSnapshot.child("city").getValue().toString();
                            String address = dataSnapshot.child("address").getValue().toString();

                            viewHolder.t.setText(chef);
                            viewHolder.tv.setText(gender);
                            viewHolder.tv1.setText(date);
                            viewHolder.tv2.setText(time);
                            viewHolder.tv3.setText(hours);
                            viewHolder.tv4.setText(city);
                            viewHolder.tv5.setText(address);
                        }


                }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                viewHolder.call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user_id = getRef(viewHolder.getAdapterPosition()).getKey();
                        call();
                    }
                });
                viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user_id = getRef(viewHolder.getAdapterPosition()).getKey();
                        Toast.makeText(getActivity(),""+user_id,Toast.LENGTH_SHORT).show();
                    }
                });


            }
            @Override
            public FindFirndViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_request_apply_items_orders,viewGroup,false);
                FindFirndViewHolder viewHolder = new FindFirndViewHolder(view);
                return viewHolder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void call() {

        DatabaseReference userref = FirebaseDatabase.getInstance().getReference().child("Registered Users");
        userref.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("userMobileno"))
                { number = dataSnapshot.child("userMobileno").getValue().toString();
                    if(number.trim().length()>0)
                    {
                       if(ContextCompat.checkSelfPermission(getContext(),
                               Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                       {

                           ActivityCompat.requestPermissions(getActivity(),
                                   new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                       }
                       else
                           {
                               String dial = "tel:" + number;
                               startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                             //  Toast.makeText(getContext(), ""+number, Toast.LENGTH_SHORT).show();
                           }

                    }
                    else
                    {
                        Snackbar snackbar = Snackbar.make(recyclerView,"Invalid Number : " + number,Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                call();
            }

        }
    }

    public static class FindFirndViewHolder extends RecyclerView.ViewHolder
    {
        TextView t, tv, tv1, tv2, tv3, tv4, tv5;
        CardView parentLayout;
        ImageView call;

        public FindFirndViewHolder(View itemView) {
            super(itemView);
            t = (TextView) itemView.findViewById(R.id.tvchef);
            tv = (TextView) itemView.findViewById(R.id.tvgender);
            tv1 = (TextView) itemView.findViewById(R.id.tvhours);
            tv2 = (TextView) itemView.findViewById(R.id.tvTime);
            tv3 = (TextView) itemView.findViewById(R.id.tvdate);
            tv4 = (TextView) itemView.findViewById(R.id.tvcity);
            tv5 = (TextView) itemView.findViewById(R.id.tvaddress);
            parentLayout = (CardView) itemView.findViewById(R.id.select);
            call=(ImageView) itemView.findViewById(R.id.call);
        }
    }
}
