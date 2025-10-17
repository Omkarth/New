package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Temporarymaidprofile extends Activity {
    TextView time;
    RadioGroup rg;
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;
    Calendar currentTime;
    int hour, minute;
    String format;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    RadioGroup m_gender;
    RadioGroup m_time;
    RadioButton mgenderoption;
    EditText address;
    TextView permanent;
    RadioButton mgenderoption2;
    String gender,hours;
    String city;
    FirebaseAuth firebaseAuth;
    final FirebaseDatabase db= FirebaseDatabase.getInstance(Home_Page.DATABASE_URL);
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_temporarymaidprofile);
         Button btntemp= findViewById(R.id.temphirenow);
         r1=findViewById(R.id.t301hour1);
         r2=findViewById(R.id.t12hour1);
         r3=findViewById(R.id.t22hour1);

         address= findViewById(R.id.txtaddress);
         permanent= findViewById(R.id.tvtemp);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=db.getReference("Maid Requested");
        m_gender=findViewById(R.id.rg_gender);
        m_gender.setOnCheckedChangeListener((group, checkedId) -> {

            mgenderoption=m_gender.findViewById(checkedId);

            if (checkedId == R.id.rb_male) {
                gender = mgenderoption.getText().toString();
            } else if (checkedId == R.id.rb_female) {
                gender = mgenderoption.getText().toString();
            }

        });
        m_time=findViewById(R.id.waitingtime1);
        m_time.setOnCheckedChangeListener((group, checkedId) -> {
            mgenderoption2=m_time.findViewById(checkedId);
            if (checkedId == R.id.t301hour1) {
                hours = mgenderoption2.getText().toString();
            } else if (checkedId == R.id.t12hour1) {
                hours = mgenderoption2.getText().toString();
            } else if (checkedId == R.id.t22hour1) {
                hours = mgenderoption2.getText().toString();
            }

        });



        //TimePIcker
        time =findViewById(R.id.time1);
        currentTime = Calendar.getInstance();
        hour = currentTime.get(Calendar.HOUR_OF_DAY);
        minute = currentTime.get(Calendar.MINUTE);
        selectedTimeFormat(hour);
        time.setText(hour + " : " + minute + " " + format);
        time.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(Temporarymaidprofile.this, (view, hourOfDay, minute) -> {
                selectedTimeFormat(hourOfDay);
                time.setText(hourOfDay + " : " + minute + " " + format);
            }, hour, minute, true);
            timePickerDialog.show();
        });
        btntemp.setOnClickListener(v -> {
            sendata();
            String stime;
            if (r1.isChecked())
            {
                stime = "1/2-1 Hours " + "\n\nRs.500";
            } else if (r2.isChecked())
            {
                stime = "1-2 Hours   " + "\n\nRs.1000";
            } else if (r3.isChecked())
            {
                stime = "2-1 Hours   " + "\n\nRs.1200";
            } else

                {
                stime = "";
            }
            Intent timeintent = new Intent(Temporarymaidprofile.this,AddressActivity.class);
            timeintent.putExtra("Etra_message",stime);
            startActivity(timeintent);
        });
        //EndofTimePicker
        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Select, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city=parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),city,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void selectedTimeFormat(int hour) {

        if (hour == 0) {
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            format = "PM";
        } else {
            format = "AM";
        }
    }
    public void sendata(){
        String datafieldtext= gender;
        String datafieldtext2= hours;
        String datafieldtext5= city;
        String datafieldtext4=time.getText().toString();
        String datafieldtext6=address.getText().toString();
        String datafieldtext7=permanent.getText().toString();
        tempmaiduser tempmaiduser=new tempmaiduser(datafieldtext,datafieldtext2,datafieldtext5,datafieldtext4,datafieldtext6,datafieldtext7);
        String user_id= null;
        if (firebaseAuth.getCurrentUser() != null) {
            user_id = firebaseAuth.getCurrentUser().getUid();
        }

        DatabaseReference current_user=FirebaseDatabase.getInstance(Home_Page.DATABASE_URL).getReference().child("Request Pickup").child("Maid Requested").child(user_id);
        current_user.setValue(tempmaiduser);

        DatabaseReference current_users=FirebaseDatabase.getInstance(Home_Page.DATABASE_URL).getReference().child("Pickup").child("Maid Requested").child(user_id);
        current_users.setValue(tempmaiduser);

        }


}
