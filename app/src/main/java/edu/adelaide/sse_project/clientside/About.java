package edu.adelaide.sse_project.clientside;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ListView lv = findViewById(R.id.listv);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Include manual floor scrubbing\n");
        strings.add("Wash basin can also be cleaned\n");
        strings.add("Bathroom can be cleaned 100%\n");
        strings.add("No extra payment to servicer");

        ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout.list_row, strings);
        lv.setAdapter(listAdapter);
        Button nack = findViewById(R.id.back);
        nack.setOnClickListener(v -> {
            Intent intent = new Intent(About.this, ProfileActivity.class);
            startActivity(intent);
        });
    }
}
