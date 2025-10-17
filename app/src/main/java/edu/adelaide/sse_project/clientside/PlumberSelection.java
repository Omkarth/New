package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class PlumberSelection extends Activity {
    Button templuber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_plumber_selection);
        templuber=findViewById(R.id.hireplumber);
        templuber.setOnClickListener(v -> {
            Intent intent=new Intent(PlumberSelection.this,PlumberTempSelection.class);
            startActivity(intent);
            finish();
        });
    }
}
