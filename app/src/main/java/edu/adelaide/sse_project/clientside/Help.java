package edu.adelaide.sse_project.clientside;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import es.dmoral.toasty.Toasty;

public class Help extends AppCompatActivity {

    private EditText txtfeedback;
    private Button btnfeedback;

    FirebaseAuth firebaseAuth;
    final FirebaseDatabase db= FirebaseDatabase.getInstance(Home_Page.DATABASE_URL);
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        txtfeedback = findViewById(R.id.feedback);
        btnfeedback = findViewById(R.id.btnfeedback);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=db.getReference("Feedback");



        ImageView call = findViewById(R.id.call);

        call.setOnClickListener(v -> {

                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:7039772608"));
                startActivity(intent1);

        });
        btnfeedback.setOnClickListener(v -> {
            if (btnfeedback.isPressed()) {
                String feedback = txtfeedback.getText().toString().trim();
                if (feedback.isEmpty()) {
                    txtfeedback.setError("Feedack field can't be empty");

                }else
                {
                    sendata();
                    Toasty.success(Help.this,"Feedback send successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Help.this,Home_Page.class);
                    startActivity(intent);
                }
            }
        });


    }
    public void sendata() {

        String feeds = txtfeedback.getText().toString();
        Feedback feedback = new Feedback(feeds);
        String user_id = null;
        if (firebaseAuth.getCurrentUser() != null) {
            user_id = firebaseAuth.getCurrentUser().getUid();
        }
        DatabaseReference current_user = FirebaseDatabase.getInstance(Home_Page.DATABASE_URL).getReference().child("Feedback").child(user_id);
        current_user.setValue(feedback);

    }
}
