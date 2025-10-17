package edu.adelaide.sse_project.clientside;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChefSelection extends AppCompatActivity {
    Button chef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_selection);

        chef=findViewById(R.id.hirechef);
        chef.setOnClickListener(v -> {
            startActivity(new Intent(ChefSelection.this,TempChefSelection.class));
            finish();
        });
    }
}
