package edu.adelaide.sse_project.clientside;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

public class PlumberAddress extends Activity {
    NotificationCompat.Builder notification;
    private static final int unique_id = 45612;
    EditText txtaddress;
    Button hire;
    Dialog mydialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_plumber_address);




        mydialog=new Dialog(this);
        Intent intent=getIntent();
        String name=intent.getStringExtra("messafes");
        String name1=intent.getStringExtra("dfdff");
        TextView times= findViewById(R.id.t21);
        times.setText(name1);
        TextView timeselected= findViewById(R.id.t4);
        timeselected.setText(name);
        ImageView im=findViewById(R.id.im);
        String ur = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzLpvVlT61RqQ7YgTnQV900k-DdSrdUe1veTLWKsux0TQ0kjHc";
        Picasso.with(this).load(ur).into(im);

        hire=findViewById(R.id.addresshire1);

        //Notification
        notification = NotificationHelper.buildNotification(this::addHireClickListener, this);
        notification.setAutoCancel(true);
    }

    private void addHireClickListener(boolean notificationsEnabled) {
        hire.setOnClickListener(v -> {


            TextView ts;
            Button dialog;
            mydialog.setContentView(R.layout.custom_dialog);
            mydialog.findViewById(R.id.text_dialog);
            dialog= mydialog.findViewById(R.id.btn_dialog);

            if (notificationsEnabled) {
                notification.setSmallIcon(R.drawable.notificationicon);
                notification.setTicker("Ticker");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("Maid Hired");
                notification.setContentText("You will be informed soon...");
                Intent intent = new Intent(PlumberAddress.this, Home_Page.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(PlumberAddress.this, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);
                //Build Notification issues it
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(unique_id, notification.build());
            }

            dialog.setOnClickListener(v1 -> {
                mydialog.dismiss();
                Intent thirdIntent = new Intent(PlumberAddress.this,Home_Page.class);
                startActivity(thirdIntent);
                Toasty.success(PlumberAddress.this,"Thank you", Toast.LENGTH_SHORT).show();
            });
            mydialog.show();

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == unique_id) {
            // If request is cancelled, the result arrays are empty.
            addHireClickListener(grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED);
        }
    }
}
