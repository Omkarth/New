package edu.adelaide.sse_project.serverside;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Requests extends AppCompatActivity {


    private ArrayList<List_requested_Feedback> mExampleList;
    private ExampleAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<List_requested_Feedback> list_requested_feedbacks;
    private DatabaseReference feedbacks;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    private Button buttonRemove;
    private EditText editTextRemove;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        feedbacks = FirebaseDatabase.getInstance(MainActivity.DATABASE_URL).getReference().child("/Feedback");


       /* String user_id=firebaseAuth.getCurrentUser().getUid();
        DatabaseReference current_user=FirebaseDatabase.getInstance(MainActivity.DATABASE_URL).getReference().child("/Feedback").child(user_id);
        TextView feeds=(TextView)findViewById(R.id.feeds);
        current_user.setValue(feeds);*/

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewfeed);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(Requests.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching your data....");
        progressDialog.show();



        buildRecyclerView();


        final FirebaseRecyclerAdapter<List_requested_Feedback, Requests.FViewHolder> adapter = new FirebaseRecyclerAdapter<List_requested_Feedback, Requests.FViewHolder>(

                List_requested_Feedback.class,
                R.layout.list_requested_feedback,
                Requests.FViewHolder.class,
                feedbacks

        ) {


            @Override
            protected void populateViewHolder(final Requests.FViewHolder viewHolder, List_requested_Feedback model,final int position) {
                progressDialog.dismiss();
                viewHolder.setFeed(model.getFeeds());

            }



        };
        recyclerView.setAdapter(adapter);


       // createExampleList();

       // setButtons();
    }

   public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }



    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerviewfeed);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(Requests.this, "Clicked" + position, Toast.LENGTH_SHORT).show();
                removeItem(position);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
                Toast.makeText(Requests.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }





    public static class FViewHolder extends RecyclerView.ViewHolder {

        TextView feeds;



        public FViewHolder(View itemView) {
            super(itemView);
            feeds = (TextView) itemView.findViewById(R.id.feed);

        }

        public void setFeed(String feed) {
            feeds.setText(feed);
        }


    }
}
