package edu.adelaide.sse_project.workerapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Request_Apply_Chef extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter3;
    private List<List_requested_apply_Chef> list_requested_chefs;
    private DatabaseReference chefref,acceptref,acceptreddelete;
    private TextView text1;
    private DatabaseReference userref;
    private FirebaseAuth mAuth;
    String servicer_id;
    private String visit_user_id;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__apply__chef);


        mAuth = FirebaseAuth.getInstance();
        servicer_id = mAuth.getCurrentUser().getUid();
        acceptref = FirebaseDatabase.getInstance().getReference().child("Accepted Requests").child("Chef");
        acceptreddelete = FirebaseDatabase.getInstance().getReference().child("Request Pickup").child("Chef Requested For Temporary");
        userref = FirebaseDatabase.getInstance().getReference().child("Registered Users");
        chefref = FirebaseDatabase.getInstance().getReference().child("Request Pickup").child("/Chef Requested For Temporary");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(Request_Apply_Chef.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching your data....");
        progressDialog.show();


    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<List_requested_apply_Chef1> options =
                new FirebaseRecyclerOptions.Builder<List_requested_apply_Chef1>()
                .setQuery(chefref, List_requested_apply_Chef1.class)
                .build();
        FirebaseRecyclerAdapter<List_requested_apply_Chef1,FindFirndViewHolder> adapter = new FirebaseRecyclerAdapter<List_requested_apply_Chef1, FindFirndViewHolder>(options) {


            @Override
            public void onBindViewHolder(final FindFirndViewHolder viewHolder, final int position , List_requested_apply_Chef1 model) {
                viewHolder.t.setText(model.getChef());
                viewHolder.tv.setText(model.getGender());
                viewHolder.tv1.setText(model.getHours());
                viewHolder.tv2.setText(model.getTime());
                viewHolder.tv3.setText(model.getDate());
                viewHolder.tv4.setText(model.getCity());
                viewHolder.tv5.setText(model.getAddress());
                progressDialog.dismiss();


                viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        visit_user_id = getRef(viewHolder.getAdapterPosition()).getKey();
                        Toast.makeText(Request_Apply_Chef.this, ""+visit_user_id, Toast.LENGTH_SHORT).show();
                    }
                });

                viewHolder.accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        visit_user_id = getRef(viewHolder.getAdapterPosition()).getKey();



                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Request_Apply_Chef.this);
                        alertDialog.setTitle("Are you sure")
                                .setMessage("Click on ok button to accept the request")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        acceptref.child(servicer_id).child(visit_user_id).child("Status").setValue("Done")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful())
                                                        {
                                                            //viewHolder.reject.setVisibility(viewHolder.reject.INVISIBLE);
                                                            acceptreddelete.child(visit_user_id).removeValue()
                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                        }
                                                                    });
                                                            Snackbar snackbar =  Snackbar.make(v,"Accpeted",Snackbar.LENGTH_LONG);
                                                            snackbar.show();
                                                        }
                                                    }
                                                });
                                    }
                                })
                                .setNegativeButton("cancel",null);
                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();





                                                }


                        });
                viewHolder.reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                                            viewHolder.accept.setVisibility(View.GONE);
                                            viewHolder.reject.setText("Rejected");
                                            Toast.makeText(Request_Apply_Chef.this, "Rejected", Toast.LENGTH_SHORT).show();


                    }
                });
                    /*    CharSequence option[] = new CharSequence[]
                                {
                                        "Accept",
                                        "Cancel"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        builder.setTitle("Please select following");
                        builder.setItems(option, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                if(i==0)
                                {
                                    acceptref.child(servicer_id).setValue("Done")
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(Request_Apply_Chef.this, "Accepted", Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            });

                                }
                                if (i==1)
                                {
                                    acceptref.child(servicer_id).removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if(task.isSuccessful())
                                                    {
                                                        Toast.makeText(Request_Apply_Chef.this, "Cancelled", Toast.LENGTH_SHORT).show();

                                                    }                                                }
                                            });

                                }
                            }

                        });
                        builder.show();*/





            }

            @Override
            public FindFirndViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
               View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_request_apply_items_chef,viewGroup,false);
               FindFirndViewHolder viewHolder = new FindFirndViewHolder(view);
               return viewHolder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class FindFirndViewHolder extends RecyclerView.ViewHolder
    {
        TextView t, tv, tv1, tv2, tv3, tv4, tv5;
        CardView parentLayout;
        Button accept,reject;

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
            accept = (Button) itemView.findViewById(R.id.accept);
            reject = (Button) itemView.findViewById(R.id.decline);
        }
    }
}
