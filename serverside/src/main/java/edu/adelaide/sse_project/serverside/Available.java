package edu.adelaide.sse_project.serverside;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Available extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter20;
    private RecyclerView.Adapter adapter21;
    private List<LIst_working> lIst_workings;
    private DatabaseReference workingref;
    private static final int Request_Call = 1;
    String number;
    private boolean ischat;
    FirebaseAuth firebaseAuth;
    Boolean chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);
        firebaseAuth = FirebaseAuth.getInstance();


      /*  CardView card=(CardView) findViewById(R.id.callit);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7039772608"));
                startActivity(intent);
               Toast.makeText(Available.this, "Cikcekd", Toast.LENGTH_SHORT).show();
            }
        });*/

        workingref = FirebaseDatabase.getInstance(MainActivity.DATABASE_URL).getReference().child("/Employee Service Profile(SERVER SIDE)");
        recyclerView = (RecyclerView) findViewById(R.id.recyclervi);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ProgressDialog progressDialog = new ProgressDialog(Available.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching your data....");
        progressDialog.show();

        FirebaseRecyclerAdapter<LIst_working, Available.AViewHolder> adapter20 = new FirebaseRecyclerAdapter<LIst_working, Available.AViewHolder>(

                LIst_working.class,
                R.layout.layout_listitem,
                Available.AViewHolder.class,
                workingref
        ) {
            @Override
            protected void populateViewHolder(Available.AViewHolder viewHolder, LIst_working model, int position) {
                progressDialog.dismiss();
                LIst_working lIst_working = new LIst_working();
                viewHolder.setprofile(model.getProfiletype());
                viewHolder.setfirstname(model.getFirstname());
                viewHolder.setgender(model.getGender());
                viewHolder.setmobileno(model.getMobileno());
                viewHolder.setworkinghours(model.getWorkinghours());
                viewHolder.setstatus(model.getStatus());

            }

        };
        recyclerView.setAdapter(adapter20);


    }
   /* private void makephonecall(){
        String num=.toString();
        if(num.trim().length() > 0){
            if(ContextCompat.checkSelfPermission(Available.this,
                    android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(Available.this,
                        new String[]{android.Manifest.permission.CALL_PHONE},Request_Call);
            }else {
                String dial ="tel:" + num;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }

        }else {
            Toast.makeText(Available.this,"Enter phone number",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==Request_Call){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                makephonecall();
            }else {
                Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    public static class AViewHolder extends RecyclerView.ViewHolder {


        TextView t, tv, tv1, tv2, tv3, tv4;

        public AViewHolder(View itemView) {
            super(itemView);
            t = (TextView) itemView.findViewById(R.id.tvprof);
            tv = (TextView) itemView.findViewById(R.id.tvname);
            tv1 = (TextView) itemView.findViewById(R.id.tvgender);
            tv2 = (TextView) itemView.findViewById(R.id.tvmobile);
            tv3 = (TextView) itemView.findViewById(R.id.tvhours);
            tv4 = (TextView) itemView.findViewById(R.id.img_on);



        }


        public void setprofile(String profiletype) {
            t.setText(profiletype);
        }

        public void setfirstname(String firstname) {
            tv.setText(firstname);
        }

        public void setgender(String gender) {
            tv1.setText(gender);
        }

        public void setmobileno(String mobileno) {
            tv2.setText(mobileno);
        }

        public void setworkinghours(String workinghours) {
            tv3.setText(workinghours);
        }


        public void setstatus(String Status) {
            tv4.setText(Status);
        }


    }
}