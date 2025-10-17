package edu.adelaide.sse_project.clientside;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

public class PlumberTempSelection extends Activity {

    TextView time;
    /*DatePicker*/TextView date;
    /*DatePicker*/ Calendar mCurrentDate;
    /*DatePicker*/ int day,month,year;
    Calendar currentTime;
    int hour,minute;
    String format;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Button perhire;
    RadioButton r11;
    RadioButton r22;
    RadioButton r33;
    EditText address;
    TextView plumber;

    RadioGroup m_gender;
    RadioGroup m_time;
    RadioButton mgenderoption;
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
        setContentView(R.layout.activity_plumber_temp_selection);

        plumber= findViewById(R.id.plumberservice);
        address =findViewById(R.id.txtaddresss);
        r11=findViewById(R.id.t301hour1);
        r22=findViewById(R.id.t12hour1);
        r33=findViewById(R.id.t22hour1);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=db.getReference("Plumber Requested For Temporary");

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
        final TextView sedate=findViewById(R.id.date);
        //DatePicker
        date=findViewById(R.id.date);
        mCurrentDate=Calendar.getInstance();
        day=mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month=mCurrentDate.get(Calendar.MONTH);
        year=mCurrentDate.get(Calendar.YEAR);
        month=month+1;
        date.setText(day+"/"+month+"/"+year);
        date.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog=new DatePickerDialog(PlumberTempSelection.this, (view, year, monthofyeat, dayOfMonth) -> {
                monthofyeat=monthofyeat+1;
                date.setText(dayOfMonth+"/"+monthofyeat+"/"+year);
            }, year, month, day);
            datePickerDialog.show();
        });
        //EndofDatePicker
        perhire=findViewById(R.id.perHireNow);

        perhire.setOnClickListener(v -> {

            sendata();
            final String name=sedate.getText().toString();
            String salute1;
            if(r11.isChecked())
            {
                salute1="1/2-1 Hours "+"\n\nRs.500";
            }else if(r22.isChecked())
            {
                salute1="1-2 Hours   "+"\n\nRs.1000";
            }else if(r33.isChecked()){
                salute1="2-1 Hours   "+"\n\nRs.1200";
            }else
            {
                salute1="";
            }

            Intent dateintent=new Intent(PlumberTempSelection.this,PlumberAddress.class);
            dateintent.putExtra("messafes",name);
            dateintent.putExtra("dfdff",salute1);
            startActivity(dateintent);

        });
        //TimePIcker
        time = findViewById(R.id.time1);
        currentTime =Calendar.getInstance();
        hour=currentTime.get(Calendar.HOUR_OF_DAY);
        minute=currentTime.get(Calendar.MINUTE);
        selectedTimeFormat(hour);
        time.setText(hour + " : " + minute + " " + format);
        time.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog=new TimePickerDialog(PlumberTempSelection.this, (view, hourOfDay, minute) -> {
                selectedTimeFormat(hourOfDay);
                time.setText(hourOfDay + " : " + minute + " " + format);
            }, hour, minute, true);
            timePickerDialog.show();
        });
        //EndofTimePicker
        spinner = findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.Select, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
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
    public void selectedTimeFormat(int hour){

        if(hour==0){
            format="AM";
        }else if(hour==12){
            format="PM";
        }else if(hour>12){
            format="PM";
        }else {
            format="AM";
        }
    }
    public void sendata(){
        String datafieldtext= gender;
        String datafieldtext2= hours;
        String datafieldtext3=time.getText().toString();
        String datafieldtext4=date.getText().toString();
        String datafieldtext5= city;
        String datafiledtext6=address.getText().toString();
        String datafieldtext7=plumber.getText().toString();

        PlumberUsers plumberUsers = new PlumberUsers(datafieldtext,datafieldtext2,datafieldtext3,datafieldtext4,datafieldtext5,datafiledtext6,datafieldtext7);

        PlumberUser plumberUser=new PlumberUser(datafieldtext,datafieldtext2,datafieldtext3,datafieldtext4,datafieldtext5,datafiledtext6,datafieldtext7);

        String user_id= null;
        if (firebaseAuth.getCurrentUser() != null) {
            user_id = firebaseAuth.getCurrentUser().getUid();
        }

        DatabaseReference current_user=FirebaseDatabase.getInstance(Home_Page.DATABASE_URL).getReference().child("Request Pickup").child("Plumber Requested For Temporary").child(user_id);
        current_user.setValue(plumberUser);

        DatabaseReference current_users =FirebaseDatabase.getInstance(Home_Page.DATABASE_URL).getReference().child("Pickup").child("Plumber Requested For Temporary").child(user_id);
        current_users.setValue(plumberUsers);
    }
}
