package edu.adelaide.sse_project.serverside;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PLumber extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter2;
    private List<List_requested_Plumber> list_requested_plumbers;
    private DatabaseReference plumberref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber);
        plumberref = FirebaseDatabase.getInstance(MainActivity.DATABASE_URL).getReference().child("Request Pickup").child("/Plumber Requested For Temporary");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ProgressDialog progressDialog=new ProgressDialog(PLumber.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Loading your data....");
        progressDialog.show();
        FirebaseRecyclerAdapter<List_requested_Plumber, PLumber.PViewHolder> adapter2 = new FirebaseRecyclerAdapter<List_requested_Plumber, PLumber.PViewHolder>(

                List_requested_Plumber.class,
                R.layout.list_request_items_plumber,
                PLumber.PViewHolder.class,
                plumberref

        ) {
            @Override
            protected void populateViewHolder(PLumber.PViewHolder viewHolder, List_requested_Plumber model, int position) {
                progressDialog.dismiss();
                viewHolder.setplumber(model.getPlumber());
                viewHolder.setgender(model.getGender());
                viewHolder.sethours(model.getHours());
                viewHolder.setTime(model.getTime());
                viewHolder.setDate(model.getDate());
                viewHolder.setcity(model.getCity());
                viewHolder.setaddress(model.getAddress());


            }
        };
        recyclerView.setAdapter(adapter2);
        progressDialog.dismiss();
    }

    public static class PViewHolder extends RecyclerView.ViewHolder {

        TextView t, tv, tv1, tv2, tv3, tv4, tv5;

        public PViewHolder(View itemView) {
            super(itemView);
            t = (TextView) itemView.findViewById(R.id.tvplumber);
            tv = (TextView) itemView.findViewById(R.id.tvgender);
            tv1 = (TextView) itemView.findViewById(R.id.tvhours);
            tv2 = (TextView) itemView.findViewById(R.id.tvTime);
            tv3 = (TextView) itemView.findViewById(R.id.tvdate);
            tv4 = (TextView) itemView.findViewById(R.id.tvcity);
            tv5 = (TextView) itemView.findViewById(R.id.tvaddress);
        }


        public void setplumber(String plumber) {
            t.setText(plumber);
        }

        public void setgender(String gender) {
            tv.setText(gender);
        }

        public void sethours(String hours) {
            tv1.setText(hours);
        }

        public void setTime(String time) {
            tv2.setText(time);
        }

        public void setDate(String date) {
            tv3.setText(date);
        }

        public void setcity(String city) {
            tv4.setText(city);
        }


        public void setaddress(String address) {
            tv5.setText(address);
        }
    }
}