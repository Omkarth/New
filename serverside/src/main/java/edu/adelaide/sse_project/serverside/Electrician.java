package edu.adelaide.sse_project.serverside;

import android.app.Activity;
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

public class Electrician extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<List_Requested_ELect> list_requested_eLects;
    private DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician);
        myref= FirebaseDatabase.getInstance(MainActivity.DATABASE_URL).getReference().child("Request Pickup").child("/Electrician Requested For Permanent");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ProgressDialog progressDialog=new ProgressDialog(Electrician.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching your data....");
        progressDialog.show();

        FirebaseRecyclerAdapter<List_Requested_ELect,Electrician.EViewHolder> adapter =new FirebaseRecyclerAdapter<List_Requested_ELect, Electrician.EViewHolder>(

                List_Requested_ELect.class,
                R.layout.list_request_items_electrician,
                Electrician.EViewHolder.class,
                myref
        ) {
            @Override
            protected void populateViewHolder(Electrician.EViewHolder viewHolder, List_Requested_ELect model, int position) {
                progressDialog.dismiss();
                viewHolder.setmaid(model.getMaid());
                viewHolder.setgender(model.getGender());
                viewHolder.setHours(model.getHours());
                viewHolder.setTime(model.getTime());
                viewHolder.setDate(model.getDate());
                viewHolder.setcity(model.getCity());
                viewHolder.setaddress(model.getAddress());

            }
        };
        recyclerView.setAdapter(adapter);
    }
    public static class EViewHolder extends RecyclerView.ViewHolder {

        TextView t,tv, tv1, tv2, tv3, tv4, tv5;

        public EViewHolder(View itemView) {
            super(itemView);
            t=(TextView)itemView.findViewById(R.id.tvELectrician);
            tv = (TextView) itemView.findViewById(R.id.tvgender);
            tv1 = (TextView) itemView.findViewById(R.id.tvhours);
            tv2 = (TextView) itemView.findViewById(R.id.tvTime);
            tv3 = (TextView) itemView.findViewById(R.id.tvdate);
            tv4 = (TextView) itemView.findViewById(R.id.tvcity);
            tv5 = (TextView) itemView.findViewById(R.id.tvaddress);
        }


        public void setmaid (String maid) {
            t.setText(maid);
        }

        public void setgender(String gender) {
            tv.setText(gender);
        }

        public void setHours(String hours) {
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
