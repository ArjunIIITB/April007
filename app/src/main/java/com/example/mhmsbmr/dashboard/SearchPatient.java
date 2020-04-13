package com.example.mhmsbmr.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mhmsbmr.Login.MHPFlow;
import com.example.mhmsbmr.R;
import com.example.mhmsbmr.patient.PatientUtility;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.media.MediaCodec.MetricsConstants.MODE;

public class SearchPatient extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button;
    JSONObject patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_patient);
        button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        LinearLayout linearLayout1 = findViewById(R.id.editTextContainer1);
        EditText editText = new EditText(this);
        final Context context = SearchPatient.this;

        editText.setText("hello it is working");
        linearLayout1.addView(editText);
        Thread thread = new Thread(){
            public void run(){
                String sessionToken=null;
                String patientId;
                Log.e("search module", "inside onClcik listener");
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                String loginToken = sharedPreferences.getString("loginToken", "");
                try {
                    String loginDecodedToken = MHPFlow.decoded(loginToken);
                    sessionToken = new JSONObject(loginDecodedToken).getString("sessionToken");
                }catch(Exception e){}
                patientId = getIntent().getStringExtra("patientId");
                patient = new PatientUtility().getPatient(loginToken, patientId, sessionToken);
            }
        };
        thread.start();

                LinearLayout linearLayout = findViewById(R.id.editTextContainer1);
                    try {
                        final EditText editText1 = new EditText(context);
                        editText1.setText(patient.getString("dateOfBirth"));
                        Log.e("dob", patient.getString("dateOfBirth"));
                        editText1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        editText1.setPadding(20, 20, 20, 20);

                        final EditText editText2 = new EditText(context);
                        editText2.setText(patient.getString("phoneNumber"));
                        Log.e("phone number", patient.getString("phoneNumber"));
                        editText2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        editText2.setPadding(20, 20, 20, 20);

                        final EditText editText3 = new EditText(context);
                        editText3.setText(patient.getString("prefix") + " " + patient.getString("givenName") + " " + patient.getString("middleName"));
                        Log.e("name", patient.getString("prefix") + " " + patient.getString("givenName") + " " + patient.getString("middleName"));
                        editText3.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        editText3.setPadding(20, 20, 20, 20);


                        linearLayout.addView(editText1);
                        linearLayout.addView(editText2);
                        linearLayout.addView(editText3);

                    } catch (Exception e) { }



    }



} //class
