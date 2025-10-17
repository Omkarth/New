package edu.adelaide.sse_project.workerapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
    public static final String DATABASE_URL = "https://sse-project-67343-default-rtdb.firebaseio.com";

    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin=findViewById(R.id.button);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
