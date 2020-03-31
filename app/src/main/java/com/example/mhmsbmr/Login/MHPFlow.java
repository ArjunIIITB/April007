package com.example.mhmsbmr.Login;

import android.util.Base64;
//import org.apache.commons.codec.binary.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MHPFlow {

    private OkHttpClient client = new OkHttpClient();

    public String login(String userName, String password){

            final String RELATIVE_PATH = "applogin/";
            String returnString = null;
            final MediaType JSON
                    = MediaType.parse("application/json; charset=utf-8");

            JSONObject jsonObjectResult = null;

            JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("username", userName);
                jsonObject.put("password", password);
            }catch (Exception e){
                e.printStackTrace();
            }

            RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

            Request request = new Request.Builder()
                    .url(GlobalVariables.GLOBAL_PATH_USER+RELATIVE_PATH)
                    .post(formBody)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Response response = null;

            try {
                response = client.newCall(request).execute();
                ResponseBody rb = response.body();
                if(response.code() != 200){
                    return "wrong email id and/or password";
                }

                return rb.string();
                //System.out.println(jsonObjectResult.getString("profession"));

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }// login ends here


    public String[] getSalt(String userName){

        String returnString[] = new String[2];
        final String RELATIVE_PATH = "genSalt/";

        String url = GlobalVariables.GLOBAL_PATH_USER+RELATIVE_PATH+userName;
        Request request = new okhttp3.Request.Builder()
                .url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            JSONObject obj = new JSONObject(response.body().string());
            String fixed = obj.getString("fixedSalt");
            String dynamic = obj.getString("dynamicSalt");
            //System.out.println(fixed +"         "+ dynamic);
            returnString[0] = fixed;
            returnString[1] = dynamic;
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnString;

    } //getSalt() ends


    public JSONObject getuserbyuuid(String jwtToken, String uuid){
        System.out.println("GET USER BY UUID STATRTS HERE");
        String userUUID = uuid;
        String jwtTokenString = jwtToken;

        final String RELATIVE_PATH = "userinfo/" + userUUID;
        String returnString = null;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
// This is GET request
        JSONObject jsonObjectResult = null;
        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .get()
                .addHeader("Authorization", "Bearer "+ jwtTokenString)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            jsonObjectResult = new JSONObject(rb.string());
            System.out.println("jsonObjectResult.toString() " + jsonObjectResult.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(jsonObjectResult.toString());
        //getMHEInviteList(String );
        System.out.println("GET USER BY UUID ENDS HERE");
        return jsonObjectResult;

    }// getuserbyuuid ends here



    public JSONArray getAssociatedOrg(String jwtToken, String sessionId){

        final String RELATIVE_PATH = "getAssociatedOrg/";
        String returnString = null;
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        JSONArray jsonObjectResult = null;

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("user_token", sessionId);
        }catch (Exception e){
            e.printStackTrace();
        }

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(GlobalVariables.GLOBAL_PATH_REST+RELATIVE_PATH)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+jwtToken)
                .build();

        Response response = null;

        try {

            response = client.newCall(request).execute();
            ResponseBody rb = response.body();
            jsonObjectResult = new JSONArray(rb.string());

        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonObjectResult;

    }// getMHEInviteList ends here



    public static String decoded(String JWTEncoded) throws Exception {
        try {

            //System.out.println("before spliting " + JWTEncoded);
            String[] split = JWTEncoded.split("\\.");
            /*System.out.println("--------------------------------");
            for(String string : split){
                System.out.println("012"+string);
            }*/
            return(getJson(split[1]));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }//decode ends here



    public static String getJson(String strEncoded) throws UnsupportedEncodingException{
        //byte[] decodedBytes = Base64.decodeBase64(strEncoded);
        byte[] decodedBytes = Base64.decode(strEncoded,Base64.DEFAULT);
        if(decodedBytes == null) {
            System.out.println("Base64 unable to decode");
            return "Base64 unable to decode";
        }
        return new String(decodedBytes, "UTF-8");
        //return "return String";

    }//getJson ends here




}// MHPFlow class ends here
