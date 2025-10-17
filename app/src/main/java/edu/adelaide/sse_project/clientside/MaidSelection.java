package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MaidSelection extends Activity {

    Button temphire;
    Button perhiree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_maid_selection);
        temphire = findViewById(R.id.temphire);
        perhiree = findViewById(R.id.permanent);



        temphire.setOnClickListener(v -> {
            Intent tempIntent = new Intent(MaidSelection.this,Temporarymaidprofile.class);
           startActivity(tempIntent);
       });
       perhiree.setOnClickListener(v -> startActivity(new Intent(MaidSelection.this,PermanentMaid.class)));
    }
}
