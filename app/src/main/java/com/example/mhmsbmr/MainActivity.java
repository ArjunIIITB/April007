package com.example.mhmsbmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.mhmsbmr.Login.LoginBmr;
import com.example.mhmsbmr.Login.MHPFlow;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("inside MainActivity");


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        final String loginToken = sharedPreferences.getString("loginToken", "");
        final String orgUUID ;
        final String userUUID;

        try {

            String loginDecodedToken = MHPFlow.decoded(loginToken);
            Log.e("loginDecodedToken", loginDecodedToken);
            orgUUID = new JSONObject(loginDecodedToken).getString("orgUUID");
            userUUID = new JSONObject(loginDecodedToken).getString("userUUID");
            Log.e("three values    ",  orgUUID +"   " + userUUID+"  "+ loginToken);


            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    new MHPFlow().getWatingPatients(orgUUID, userUUID, loginToken);
                    new MHPFlow().getCompletedPatients(orgUUID, userUUID, loginToken);
                }
            };
            new Thread(runnable).start();

        }catch(Exception e){e.printStackTrace();}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}