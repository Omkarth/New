package edu.adelaide.sse_project.serverside;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tomer.fadingtextview.FadingTextView;

import java.util.ArrayList;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.NavigationMenu;

public class Home_page extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter10;
    private List<List_Request_item> list_request_items;
    private Boolean isuserpressbackbutton = false;
    private DatabaseReference myref;


    @Override
    public void onBackPressed() {
        if (!isuserpressbackbutton) {
            Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT).show();
            isuserpressbackbutton = true;

        } else {
            super.onBackPressed();
        }
        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                isuserpressbackbutton = false;

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        myref = FirebaseDatabase.getInstance(MainActivity.DATABASE_URL).getReference().child("Request Pickup").child("/Maid Requested");

        recyclerView = (RecyclerView) findViewById(R.id.recyclervie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ProgressDialog progressDialog = new ProgressDialog(Home_page.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Fetching your data....");
        progressDialog.show();

        FirebaseRecyclerAdapter<List_Request_item, MViewHolder> adapter10 = new FirebaseRecyclerAdapter<List_Request_item, MViewHolder>(

                List_Request_item.class,
                R.layout.list_request_items,
                MViewHolder.class,
                myref
        ) {
            @Override
            protected void populateViewHolder(MViewHolder viewHolder, List_Request_item model, int position) {

                final String postid = getRef(position).getKey();
                progressDialog.dismiss();
                viewHolder.setmaid(model.getPermanent());
                viewHolder.setgender(model.getGender());
                viewHolder.sethours(model.getHours());
                viewHolder.setTime(model.getTime());
                viewHolder.setDate(model.getDate());
                viewHolder.setcity(model.getCity());
                viewHolder.setaddress(model.getAddress());

            }
        };
        recyclerView.setAdapter(adapter10);


   /*     list_request_items = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            List_Request_item list_request_item = new List_Request_item(
                    "989765678" + i + 1,
                    "tower of daravi near scholl",
                    "22/10/2019",
                    "22/22/22"
            );
            list_request_items.add(list_request_item);
        }*/
        // adapter=new MyAdapter(list_request_items,this);
        //   recyclerView.setAdapter(adapter);*/

        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabsppeddial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();/*   case R.id.action_call:
                        startActivity(new Intent(Home_page.this,WorkersAvailable.class));
                        break;
                    case R.id.action_requests:
                        Intent intent111 = new Intent(Home_page.this, RequestsWork.class);
                        startActivity(intent111);
                        break;*/
                if (itemId == R.id.action_available) {
                    Intent intent = new Intent(Home_page.this, Available.class);
                    startActivity(intent);
                } else if (itemId == R.id.action_addperson) {
                    Intent intent1 = new Intent(Home_page.this, Add_Person.class);
                    startActivity(intent1);
                } else if (itemId == R.id.action_message) {
                    Intent intent2 = new Intent(Home_page.this, Requests.class);
                    startActivity(intent2);
                } else if (itemId == R.id.action_plumber) {
                    Intent intent4 = new Intent(Home_page.this, PLumber.class);
                    startActivity(intent4);
                } else if (itemId == R.id.action_electrician) {
                    Intent intent5 = new Intent(Home_page.this, Electrician.class);
                    startActivity(intent5);
                } else if (itemId == R.id.action_chef) {
                    Intent intent6 = new Intent(Home_page.this, Chef.class);
                    startActivity(intent6);
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });


    }

    public static class MViewHolder extends RecyclerView.ViewHolder {

        TextView t, tv, tv1, tv2, tv3, tv4, tv5;


        public MViewHolder(View itemView) {
            super(itemView);
            t = (TextView) itemView.findViewById(R.id.maid);
            tv = (TextView) itemView.findViewById(R.id.tvgender);
            tv1 = (TextView) itemView.findViewById(R.id.tvhours);
            tv2 = (TextView) itemView.findViewById(R.id.tvTime);
            tv3 = (TextView) itemView.findViewById(R.id.tvdate);
            tv4 = (TextView) itemView.findViewById(R.id.tvcity);
            tv5 = (TextView) itemView.findViewById(R.id.tvaddress);
        }


        public void setmaid(String maid) {
            t.setText(maid);
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
