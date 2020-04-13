package com.example.mhmsbmr.patient;


import android.util.Log;

import com.example.mhmsbmr.Login.GlobalVariables;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PatientUtility {
    private OkHttpClient client = new OkHttpClient();

    public void getLastVisitData(String patientId, String orgUUID, String loginToken){


        final String RELATIVE_PATH = "getLastVisitData/";
        patientId = "c55dd6c4-c571-41b1-a9af-ee634d9ebefe/";
        orgUUID = "4cc74280-efe5-4016-b41e-f29472a4ec12";
        loginToken = "Bearer eyJEZXZlbG9wZWQgQnkiOiJlLUhlYWx0aCBSZXNlYXJjaCBDZW50ZXIsIElJSVQgQmFuZ2Fsb3JlIiwiSG9zdCI6Ikthcm5hdGFrYSBNZW50YWwgSGVhbHRoIE1hbmFnZW1lbnQgU3lzdGVtIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJwcm9mZXNzaW9uIjoiTUhNU1BzeWNob2xvZ2lvaW9pc3RzdGlzaXRhY2NlcHRpbmciLCJzdWIiOiJNSE1TIFNlY3VyaXR5IFRva2VuIiwibGFzdExvZ2luT3JnSWQiOiIiLCJMYXN0TG9naW5TdGFydCI6IiIsInNlc3Npb25FbmRUaW1lIjoxNTg2MzkzMzUxLCJpc3MiOiJLTUhNUyIsInNlc3Npb25TdGFydFRpbWUiOjE1ODYzNTAxNTEsInNlc3Npb25JZCI6IjJiMWExZTQ4LWU5ODgtNDhiZS05YzA2LThhYzM2NmQ5MDIzMCIsInVzZXJOYW1lIjoicHJhc2hhbnQiLCJvcmdVVUlEIjoiYTIxYjg4NWUtMmYzYS00NDI1LThiNWItMGQyNzRiNDJhZjI2IiwibmJmIjoxNTg2MzUwMTUxLCJvcmdSb2xlIjoiTUhFQWRtaW4iLCJzZXNzaW9uVG9rZW4iOiJTZXNzaW9uSWQ6MTcyLjMxLjUuMTMjcHJhc2hhbnQ6YTIxYjg4NWUtMmYzYS00NDI1LThiNWItMGQyNzRiNDJhZjI2Ok1ITVM6TUhFQWRtaW4jMTU4NjM1MDE1MTEyMyMtMTU4NDE1NzYwNyMzMTEiLCJwZXJzb25JZCI6IjkyNWQ2N2NkLTdkM2MtNDA3OC04OWZiLTY5NjNjNDdiNDk2YSIsInVzZXJVVUlEIjoiNzc1YjhjM2UtNjc0Mi00YjMwLWI0NDMtYzdkNmFhNmVjNGFjIiwiZXhwIjoxNTg2Mzg2MTUxLCJpYXQiOjE1ODYzNTAxNTF9.Fn53K2z0OqN5GQfovoVgb7WtFncsqdL7AfC7ICuGfxg";

        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        RequestBody formBody = RequestBody.create(JSON, new JSONObject().toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH+patientId+orgUUID)
                .get()
                .addHeader("Authorization", loginToken)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            if(response.code() != 200){
                System.out.println("Did not get 200 response");
            }

            System.out.println(rb.string());
            //System.out.println(jsonObjectResult.getString("profession"));

        }catch(Exception e){
            e.printStackTrace();
        }

    }// getLastVisitData


    public JSONObject getPatient(String loginToken, String patientId, String sessionToken){

        final String RELATIVE_PATH = "getpatient/";
        String returnString = null;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONObject jsonObjectResult = null;

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("patientId", patientId);
            jsonObject.put("token", sessionToken);
        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+loginToken)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            Log.e("why is it null", "");
            jsonObjectResult = new JSONObject(rb.string());
            //System.out.println("why is it null-----------------------------------"+rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonObjectResult;
        //return null;
    }


}
