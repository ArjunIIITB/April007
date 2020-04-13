package com.example.mhmsbmr.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mhmsbmr.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        LinearLayout linearLayout = findViewById(R.id.editTextContainer);
        JSONArray waitList = null;
        try {
            //JSONArray waitingList = new JSONArray(getIntent().getStringExtra("waitingPatient"));
            JSONArray completeList = new JSONArray(getIntent().getStringExtra("completePatient"));

            for(int i=0; i<completeList.length(); i++) {
                JSONObject patient = completeList.getJSONObject(i);


                final EditText editText1 = new EditText(this);
                editText1.setText(patient.getString("patientId"));
                editText1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText1.setPadding(20, 20, 20, 20);

                final EditText editText2 = new EditText(this);
                editText2.setText(patient.getString("assignedmhpId"));
                editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText2.setPadding(20, 20, 20, 20);

                final EditText editText3 = new EditText(this);
                editText3.setText(patient.getString("assignedmhpName"));
                editText3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText3.setPadding(20, 20, 20, 20);


                linearLayout.addView(editText1);
                linearLayout.addView(editText2);
                linearLayout.addView(editText3);

            }

        }catch(Exception e){}





        final EditText space = new EditText(this);
        space.setText("Comlete list ends here, waiting list starts");
        space.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        space.setPadding(20, 50, 20, 50);

        linearLayout.addView(space);




        try {
            JSONArray waitingList = new JSONArray(getIntent().getStringExtra("waitingPatient"));
            waitList = new JSONArray(getIntent().getStringExtra("waitingPatient"));
            for(int i=0; i<waitingList.length(); i++) {
                JSONObject patient = waitingList.getJSONObject(i);

                final EditText editText1 = new EditText(this);
                editText1.setText(patient.getString("patientId"));
                editText1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText1.setPadding(20, 20, 20, 20);

                final EditText editText2 = new EditText(this);
                editText2.setText(patient.getString("assignedmhpId"));
                editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText2.setPadding(20, 20, 20, 20);

                final EditText editText3 = new EditText(this);
                editText3.setText(patient.getString("assignedmhpName"));
                editText3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText3.setPadding(20, 20, 20, 20);


                linearLayout.addView(editText1);
                linearLayout.addView(editText2);
                linearLayout.addView(editText3);


                Thread.sleep(10000);

            }

        }catch(Exception e){}

        System.out.println("after sleep");


        Intent intent = new Intent(DashboardActivity.this, SearchPatient.class);
        try {
            JSONObject patient = (JSONObject) waitList.get(0);
            intent.putExtra("patientId", patient.getString("patientId"));
        }catch (Exception e){}
        DashboardActivity.this.startActivity(intent);


    }
}
