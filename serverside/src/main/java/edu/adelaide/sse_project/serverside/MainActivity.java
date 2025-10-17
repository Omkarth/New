package edu.adelaide.sse_project.serverside;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public static final String DATABASE_URL = "https://sse-project-67343-default-rtdb.firebaseio.com";

    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MarqueeView marqueeView=(MarqueeView)findViewById(R.id.mview);
        List<String> list=new ArrayList<>();
        list.add("1. Order is displayed on the screen");
        list.add("2. You can add employees");
        list.add("3. Your employees can be displayed");
        list.add("4. Collect your respective charges");
        marqueeView.startWithList(list);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(MainActivity.this,""+textView.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Home_page.class));
            }
        });
     //   MarqueeView marquee1View=(MarqueeView)findViewById(R.id.m1view);
        List<String> list1=new ArrayList<>();
        list1.add("1. Please collect the money");
        list1.add("2. Show your 100%");
        list1.add("3. Work for 24/7");
        list1.add("4. Make sure that customer is happy");
      /*  marquee1View.startWithList(list1);
        marquee1View.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(MainActivity.this,""+textView.getText(),Toast.LENGTH_SHORT).show();
            }
        });*/

        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Home_page.class));
            }
        });
    }
}
