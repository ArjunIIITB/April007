package com.example.mhmsbmr.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import org.mindrot.jbcrypt.BCrypt;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mhmsbmr.R;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginBmr extends AppCompatActivity {


    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private ImageView image;
    private int counter = 5;
    OkHttpClient client = new OkHttpClient();

    private final String GlobalVariables = "http://13.126.27.50/MHMS_DEV/user/";
    private final String CORS = "http://13.126.27.50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bmr);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside on click listener");

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        //test4(236);
                        final String userName = ((EditText) findViewById(R.id.etName)).getText().toString();
                        final String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();
                        System.out.println(userName+"     lskajdf;lkajsdf;lkjas;dfkjas;dkfj;sakdfj;sakdfj;sdalfkj;asdkfj    " +password);
                        String salts[] = getSalt(userName);
                        loginTest(salts, userName, password);
                    }

                };
                new Thread(runnable).start();
            }
        });
    }

public void newThread(View view) {
    // do something long
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            test4(236);
        }
    };
    new Thread(runnable).start();
}







    public void validate(String s, String ss){}


    // getting unique id and displaying the select information of user
    public void test4(int search){
        System.out.println("method test4() invoked");

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("http://13.126.27.50/MHMS_DEV/rest/searchpatient/givenName/akshay?indent=4").build();
        okhttp3.Response response = null;

        try {
            response = client.newCall(request).execute();
            okhttp3.ResponseBody rb = response.body();
            //System.out.println(rb.string());
            JSONArray arr = new JSONArray(rb.string());
            //JSONObject obj = (JSONObject) arr.get(0);
            //System.out.println(obj.get("personIdentifiers"));
            //JSONObject pi = (JSONObject) obj.get("personIdentifiers");
            //System.out.println(pi.getString("dateCreated"));

            HashMap<Integer, String[]> userMap = new HashMap<Integer, String[]>();
            for(int i=0; i<arr.length(); i++){
                int value = arr.getJSONObject(i).getInt("personId");
                String []stringArray = new String[3];
                stringArray[0] = arr.getJSONObject(i).getString("givenName");
                stringArray[1] = arr.getJSONObject(i).getString("email");
                stringArray[2] = arr.getJSONObject(i).getString("phoneNumber");
                userMap.put(value, stringArray);
            }
            System.out.println("__________________________________________________________________________");
            if(userMap.containsKey(search)) {
                System.out.println();
                String[] userData = (userMap.get(search));
                for (String data : userData) {
                    System.out.println(data);
                }
            }else{
                System.out.println("No user with given id exists");
            }
            System.out.println("__________________________________________________________________________");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("method test4() last line");
    } //test4() ends



    public String[] getSalt(String userName){

        String returnString[] = new String[2];

        String url = "http://13.126.27.50/MHMS_DEV/user/genSalt/"+userName;
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url).build();
        okhttp3.Response response = null;
        try {
            response = client.newCall(request).execute();
            JSONObject obj = new JSONObject(response.body().string());
            String fixed = obj.getString("fixedSalt");
            String dynamic = obj.getString("dynamicSalt");
            System.out.println(fixed +"         "+ dynamic);
            returnString[0] = fixed;
            returnString[1] = dynamic;
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnString;

    } //getSalt() ends


    public void loginTest(String[] salts, String userName, String password){

        String relativePath = "applogin/";
        final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        String hash = BCrypt.hashpw(password, salts[0]);
        String hash1 = BCrypt.hashpw(hash, salts[1]);
        System.out.println(hash);
        System.out.println("hash 1" + hash1);

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("username", userName);
            jsonObject.put("password", hash1);
        }catch (Exception e){
            e.printStackTrace();
        }
        /*if(true) {
            System.out.println("This is the json object check whether it is starting with '{' or not ? " + jsonObject.toString());
            return;
        }*/

        RequestBody formBody = RequestBody.create(JSON, jsonObject.toString());

                Request request = new Request.Builder()
                .url(GlobalVariables+relativePath)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Allow-Origin", CORS )
                .build();



        Response response = null;

        try {
            response = client.newCall(request).execute();
            String whatIGot = response.body().string();
            System.out.println("_______________________________________________________________________________");
            System.out.println(whatIGot);
            JSONObject obj = new JSONObject(whatIGot);
        }catch(Exception e){
            e.printStackTrace();
        }


/*        String urlString = GlobalVariables+relativePath; // URL to call
        String data = jsonObject.toString(); //data to post
        OutputStream out = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/


    }// loginTest ends here




    public void getCaptcha(){
        String relativePath = "createCaptcha/LOGIN";

        RequestBody formBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder()
                .url(GlobalVariables+relativePath)
                .post(formBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Allow-Origin", CORS )
                .build();



        Response response = null;

                try {
                    response = client.newCall(request).execute();
                    String whatIGot = response.body().string();
                    System.out.println(whatIGot);
                    JSONObject obj = new JSONObject(whatIGot);
                    obj.getString("captchaID");
                    String imageString = obj.getString("encodedImage");
                    System.out.println(imageString);

                    byte []imageBytes = Base64.decode(imageString, Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

                    //image is of type ImageView and it needs to be fetched using getElementById(id) equivalent of android
                    image.setImageBitmap(decodedImage);



                }catch(Exception e){
                    e.printStackTrace();
                }
    } //getCaptcha ends





} //Login ends

