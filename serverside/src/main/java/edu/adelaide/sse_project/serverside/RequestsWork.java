package edu.adelaide.sse_project.serverside;

import android.app.Activity;
import android.app.Notification;
import android.content.IntentSender;
import android.media.session.MediaSession;
import android.support.design.widget.Snackbar;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.security.auth.callback.Callback;

import static android.support.v4.media.session.MediaSessionCompat.*;

public class RequestsWork extends AppCompatActivity {

    Button accept,reject;
    String cutomer_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_work);


        //Accept or reject worker request
        accept = (Button) findViewById(R.id.accept);
        reject = (Button) findViewById(R.id.reject);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Accepted",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show();

            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Cancelled",Snackbar.LENGTH_SHORT)
                        .setAction("Action",null).show();

            }
        });
    }

}
