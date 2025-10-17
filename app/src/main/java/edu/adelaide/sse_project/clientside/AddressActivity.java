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

public class AddressActivity extends Activity{

    NotificationCompat.Builder notification;
    private static final int unique_id = 45612;
    private EditText address;
    private Button addhire;
    Dialog mydialog;
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_address);
        mydialog=new Dialog(this);
        ImageView im=findViewById(R.id.im);
        String ur ="https://media2.s-nbcnews.com/i/newscms/2017_41/2187641/171012-better-stock-house-cleaner-ew-531p_524663bd13e994184485cf04bf4a26e0.jpg";
        Picasso.with(this).load(ur).into(im);
        Intent intent=getIntent();
        String salute=intent.getStringExtra("Etra_message");
        TextView timeselected= findViewById(R.id.t2);
        timeselected.setText(salute);

        pay= findViewById(R.id.pay);
        notification = NotificationHelper.buildNotification(this::addPayClickListener, this);
        notification.setAutoCancel(true);
    }

    private void addPayClickListener(boolean notificationsEnabled) {
        pay.setOnClickListener(v -> {
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
                Intent intent = new Intent(AddressActivity.this, Home_Page.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(AddressActivity.this, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(unique_id , notification.build());
            }


            dialog.setOnClickListener(v1 -> {
                mydialog.dismiss();
                Intent thirdIntent = new Intent(AddressActivity.this,Home_Page.class);
                startActivity(thirdIntent);
                Toasty.success(AddressActivity.this,"Thank you",Toast.LENGTH_SHORT).show();
            });
            mydialog.show();


        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == unique_id) {
            // If request is cancelled, the result arrays are empty.
            addPayClickListener(grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED);
        }
    }

}
