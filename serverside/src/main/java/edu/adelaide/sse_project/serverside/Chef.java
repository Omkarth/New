package edu.adelaide.sse_project.serverside;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;
import java.util.concurrent.FutureTask;

public class Chef extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter3;
    private List<List_requested_Chef> list_requested_chefs;
    private DatabaseReference chefref;
    MaterialSpinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);
        chefref = FirebaseDatabase.getInstance(MainActivity.DATABASE_URL).getReference().child("Request Pickup").child("/Chef Requested For Temporary");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ProgressDialog progressDialog=new ProgressDialog(Chef.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching your data....");
        progressDialog.show();

        FirebaseRecyclerAdapter<List_requested_Chef, Chef.CViewHolder> adapter3 = new FirebaseRecyclerAdapter<List_requested_Chef, Chef.CViewHolder>(

                List_requested_Chef.class,
                R.layout.list_request_items_chef,
                Chef.CViewHolder.class,
                chefref
        ) {
            @Override
            protected void populateViewHolder(final Chef.CViewHolder viewHolder, List_requested_Chef model, final int position) {
                progressDialog.dismiss();
                viewHolder.setchef(model.getChef());
                viewHolder.setgender(model.getGender());
                viewHolder.sethours(model.getHours());
                viewHolder.setTime(model.getTime());
                viewHolder.setDate(model.getDate());
                viewHolder.setcity(model.getCity());
                viewHolder.setaddress(model.getAddress());

            }

        };
        recyclerView.setAdapter(adapter3);



    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getIntent().equals(common.Update))
            showupdatedialog((Adapter) adapter3);
        else if(item.getIntent().equals(common.Delete))
            deletedialog((Adapter) adapter3);
        return super.onContextItemSelected(item);
    }

    private void deletedialog(Adapter adapter3) {
    }

    private void showupdatedialog(Adapter adapter3) {
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(Chef.this);
        alertdialog.setTitle("Update Order");
        alertdialog.setMessage("Please change status");

        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.update_status,null);

        spinner = (MaterialSpinner)view.findViewById(R.id.statusspinner);
        spinner.setItems("Placed","on my way","Shipped");

        alertdialog.setView(view);

      //  final String localKey = key;
        alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
               // item.setStatus(String.valueOf(spinner.getSelectedIndex()));

            }
        });
        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertdialog.show();
    }

    public static class CViewHolder extends RecyclerView.ViewHolder {

        TextView t, tv, tv1, tv2, tv3, tv4, tv5;

        public CViewHolder(View itemView) {
            super(itemView);
            t = (TextView) itemView.findViewById(R.id.tvchef);
            tv = (TextView) itemView.findViewById(R.id.tvgender);
            tv1 = (TextView) itemView.findViewById(R.id.tvhours);
            tv2 = (TextView) itemView.findViewById(R.id.tvTime);
            tv3 = (TextView) itemView.findViewById(R.id.tvdate);
            tv4 = (TextView) itemView.findViewById(R.id.tvcity);
            tv5 = (TextView) itemView.findViewById(R.id.tvaddress);

        }

        public void setchef(String chef) {
            t.setText(chef);
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
