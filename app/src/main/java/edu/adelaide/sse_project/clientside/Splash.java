package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.Wave;

public class Splash extends Activity {
   private static final int SPLASH_TIME_OUT = 4500;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        if (!isConnected(Splash.this)) buildDialog(Splash.this).show();
        else {

            setContentView(R.layout.activity_splash);
            new Handler().postDelayed(() -> {

                ProgressBar progressBar = findViewById(R.id.spin_kit);

                Wave cubeGrid = new Wave();
                progressBar.setIndeterminateDrawable(cubeGrid);

                Intent Login = new Intent(Splash.this, LoginActivity.class);
                startActivity(Login);
                finish();
            },SPLASH_TIME_OUT);
        }
    }




    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
        } else
            return false;
    }


    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setIcon(R.drawable.ic_signal_cellular_connected_no_internet_0_bar_black_24dp);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please turn on your mobile data or wifi.");

        builder.setPositiveButton("Ok", (dialog, which) -> finish());

        return builder;
    }
    }
