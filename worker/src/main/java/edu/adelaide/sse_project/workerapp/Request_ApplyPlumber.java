package edu.adelaide.sse_project.workerapp;





import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Request_ApplyPlumber extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter3;
    private List<List_requested_apply_Chef> list_requested_chefs;
    private DatabaseReference chefref, acceptref, acceptreddelete;
    private TextView text1;
    private DatabaseReference userref;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String servicer_id, visit_user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__apply_plumber);


        mAuth = FirebaseAuth.getInstance();
        servicer_id = mAuth.getCurrentUser().getUid();
        acceptref = FirebaseDatabase.getInstance().getReference().child("Accepted Requests").child("Plumber");
        acceptreddelete = FirebaseDatabase.getInstance().getReference().child("Request Pickup").child("Plumber Requested For Temporary");
        userref = FirebaseDatabase.getInstance().getReference().child("Registered Users");
        chefref = FirebaseDatabase.getInstance().getReference().child("Request Pickup").child("/Plumber Requested For Temporary");
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Request_ApplyPlumber.this));
        progressDialog = new ProgressDialog(Request_ApplyPlumber.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching your data....");
        progressDialog.show();


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<List_requested_apply_Plumber> options =
                new FirebaseRecyclerOptions.Builder<List_requested_apply_Plumber>()
                        .setQuery(chefref, List_requested_apply_Plumber.class)
                        .build();
        FirebaseRecyclerAdapter<List_requested_apply_Plumber, FindFirndViewHolder> adapter = new FirebaseRecyclerAdapter<List_requested_apply_Plumber, Request_ApplyPlumber.FindFirndViewHolder>(options) {


            @Override
            public void onBindViewHolder(final Request_ApplyPlumber.FindFirndViewHolder viewHolder, final int position, List_requested_apply_Plumber model) {
                viewHolder.t.setText(model.getPlumber());
                viewHolder.tv.setText(model.getGender());
                viewHolder.tv1.setText(model.getHours());
                viewHolder.tv2.setText(model.getTime());
                viewHolder.tv3.setText(model.getDate());
                viewHolder.tv4.setText(model.getCity());
                viewHolder.tv5.setText(model.getAddress());
                progressDialog.dismiss();


                viewHolder.accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        visit_user_id = getRef(viewHolder.getAdapterPosition()).getKey();
                        // Toast.makeText(Request_Apply_Chef.this, ""+visit_user_id, Toast.LENGTH_SHORT).show();


                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Request_ApplyPlumber.this);
                        alertDialog.setTitle("Are you sure")
                                .setMessage("Click on ok button to accept the request")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        acceptref.child(servicer_id).child(visit_user_id).setValue("Done")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            //viewHolder.reject.setVisibility(viewHolder.reject.INVISIBLE);
                                                            acceptreddelete.child(visit_user_id).removeValue()
                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                        }
                                                                    });
                                                            Snackbar snackbar = Snackbar.make(v, "Accpeted", Snackbar.LENGTH_LONG);
                                                            snackbar.show();
                                                        }
                                                    }
                                                });
                                    }
                                })
                                .setNegativeButton("cancel", null);
                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();


                    }


                });
                viewHolder.reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        visit_user_id = getRef(viewHolder.getAdapterPosition()).getKey();
                        acceptref.child(servicer_id).child(visit_user_id).removeValue()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            viewHolder.reject.setText("Rejected");
                                            Toast.makeText(Request_ApplyPlumber.this, "Rejected", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

            }

            @Override
            public Request_ApplyPlumber.FindFirndViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_request_apply_items_chef, viewGroup, false);
                Request_ApplyPlumber.FindFirndViewHolder viewHolder = new Request_ApplyPlumber.FindFirndViewHolder(view);
                return viewHolder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class FindFirndViewHolder extends RecyclerView.ViewHolder {
        TextView t, tv, tv1, tv2, tv3, tv4, tv5;
        CardView parentLayout;
        Button accept, reject;

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