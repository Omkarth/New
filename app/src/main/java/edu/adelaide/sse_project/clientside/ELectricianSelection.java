package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ELectricianSelection extends Activity {
    Button hireeelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_electrician_selection);

        hireeelect=findViewById(R.id.hireelect);
        hireeelect.setOnClickListener(v -> {
            Intent intent=new Intent(ELectricianSelection.this,TempELectselectinon.class);
            startActivity(intent);
        });


    }
}
