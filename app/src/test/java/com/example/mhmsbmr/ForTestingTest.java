package com.example.mhmsbmr;

import android.util.Base64;
import android.util.Log;

import com.example.mhmsbmr.Login.Encryption;
import com.example.mhmsbmr.Login.ForTesting;
import com.example.mhmsbmr.Login.LoginBmr;
import com.example.mhmsbmr.Login.Utility;


import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import okhttp3.internal.Util;

import static com.example.mhmsbmr.Login.GlobalVariables.PUBLIC_KEY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { Base64.class })
public class ForTestingTest {

    /*Utility utility = new Utility();
    ForTesting test = new ForTesting();
    String emailToken = null;
    String mobileToken = null;
    String email = "aakrisht.arjun@iiitb.ac.in";
    String mobile = "8404973134";
    String purpose = "SIGNUP";
    String tokenIdMobile="f50a60ca-e5f1-4b5f-9b14-d9aafec5cf10";
    String tokenIdEmail="de6c7e75-bc42-4628-b89a-7c8b098f6280";
    //--------------------------------------------



    //--------------------------------------------



    @Test
    public void sendMobileOTP(){ // working
        mobileToken = test.sendOTP("mobile", mobile, purpose);
        System.out.println(mobileToken);
        //8265740a-9290-4517-82f8-7e4e69ec56b0
    }

    @Test
    public void verifyMobileOTP(){ // working
        String responseCode = test.verifyOTP("mobile", mobile, purpose, tokenIdMobile, "254109");
        if(responseCode.equals("200"))
            System.out.println("OK");
        else
            System.out.println("some error occurred");
    }

    @Test
    public void sendEmailOTP(){ // working
        emailToken = test.sendOTP("email", email, purpose);
        System.out.println(emailToken);
        //44a4299e-587b-40de-a893-60169906a4ee
    }


    @Test
    public void verifyEmailOTP(){ // working
        String responseCode = test.verifyOTP("email", email, purpose, tokenIdEmail, "766975");
        System.out.println("tokenID in verifyEmailOTP = "+emailToken);
        //
        if(responseCode.equals("200"))
            System.out.println("OK");
        else
            System.out.println("some error occurred");
    }


    @Test
    public void sendBoth(){
        mobileToken = test.sendOTP("mobile", mobile, purpose);
        System.out.println(mobileToken);

        mobileToken = test.sendOTP("email", email, purpose);
        System.out.println(mobileToken);
    }// sendBoth ends here


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Test
    public void createUser() throws Exception{ //working
        String plainText = "password";
        String cipherText = RSAEncrypt(plainText);
        System.out.println(cipherText);


        //test.createUser("Feb03", cipherText, mobile, email, "DemoName",tokenIdMobile, tokenIdEmail);
    } // createUser ends here

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    public String RSAEncrypt(final String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException{
        String encodedString = "";
        byte[] encrypted = null;
        try {

            PowerMockito.mockStatic(Base64.class);
            when(Base64.encode(encrypted, anyInt())).thenAnswer(new Answer<Object>() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    return java.util.Base64.getEncoder().encode((byte[]) invocation.getArguments()[0]);
                }
            });
            when(Base64.decode(anyString(), anyInt())).thenAnswer(new Answer<Object>() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    return java.util.Base64.getMimeDecoder().decode((String) invocation.getArguments()[0]);
                }
            });
            byte[] publicBytes = Base64.decode(PUBLIC_KEY, Base64.DEFAULT);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING"); //or try with "RSA"
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            encrypted = cipher.doFinal(plainText.getBytes());
            encodedString = Base64.encodeToString(encrypted, Base64.DEFAULT);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return encodedString;
       //return "v03v5bVbom9xQj1YdJbim7NXmdr5hqZiC//XtWxUY3J2IyC5UzpOinqat++IsDLCQ66oV3dx3C++iF4YKzFVQIamDAek51RKAx1aFK805kDhHNsHJcnRc4kuBRCEq4kdAi3E0XSt+bzV+CXYLBJMFGwOER5nfo9aeRdk66t8cC+eME8ijvvUu64xNU1Zz7367e+AS9pe9bOzRVCtuSvOFilbZ7SM1sJ3KretI+umsBMQ1CBl5OpIwLbRmeGJ2XADW5aEsxi3/a83igfo388yLC4M/WgHDjuFhkf5dOFyCAqCM1EYIx+Tsu3FZixZkc4kD34nCLgaO4uEjNvD+ddsnvGs0jQhDINPExjxuUvqfEoPSRJ98uQnFbnNkqX2SRtf61hz0xnTHo9lwg4yuhhhkEbxgk1oJ2269sfLpVoDet0OdUblOzLdbfyBTshJ6h+EYNJzICdHFuyJQC+0OBP5f5m0TcKB6m+LHrhMjPaqJxftN8p5AT/nBpNBdnqPtulPsl12rhkhZx5TR2pnjH9KURyy+mhUMn+JBjlAhEakR/GbWmN/dzX88HMPhz/jRzzh6sUdkP3hv4jVKRxa81wiWp4SxaPFTt66uw5ntKUa7CzyiJD8ZNxFK3GFYnWJxVB/CfasVI/ufrJMUekj1qwbGYLJN7C70nz/2cbJhYFB9lM=";

    }// RSAEncrypt ends here

    @Test
    public void login()throws Exception{ // working
        String userName = "Feb03";
        String password = "Test@123";

        LoginBmr lb = new LoginBmr();
        String salts[] = lb.getSalt(userName);

        String hash = BCrypt.hashpw(password, salts[0]);
        String hash1 = BCrypt.hashpw(hash, salts[1]);
        System.out.println(hash);
        System.out.println("hash 1" + hash1);

        JSONObject response = utility.login(userName,password );
        String jwtToken = response.getString("token");
// unable to convert to jwt token.
        utility.decoded(jwtToken);
    }

    public void createUserSession(){

        *//*
        * {
  "profession": "MHMSPsychiatrist",
  "sub": "MHMS Security Token",
  "sessionEndTime": 1582061301,
  "iss": "KMHMS",
  "sessionStartTime": 1582018101,
  "sessionId": "2227978b-5462-4af2-91cb-9fc59a5f1180",
  "userName": "feb03",
  "orgUUID": "a21b885e-2f3a-4425-8b5b-0d274b42af26",
  "nbf": 1582018101,
  "orgRole": "MHEAdmin",
  "sessionToken": "SessionId:172.31.5.13#Feb03:a21b885e-2f3a-4425-8b5b-0d274b42af26:MHMS:MHEAdmin#1582018099952#-445146214#496",
  "personId": "bd3574ae-633b-4dd2-864f-7d253dbfbd20",
  "userUUID": "8767083c-0c45-424a-81f4-059bc0e32868",
  "exp": 1582054101,
  "iat": 1582018101
}
        * *//*
    }// createUserSession ends
*/

    @Test
    public void rsaEncodingTest(){
        try{
        Encryption test = new Encryption();
        test.RSAEncryptOne("password");
    }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void RSAEncrypt(){
        PowerMockito.mockStatic(Base64.class);
        when(Base64.encode(new byte[anyInt()], anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return java.util.Base64.getEncoder().encode((byte[]) invocation.getArguments()[0]);
            }
        });
        when(Base64.decode(anyString(), anyInt())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return java.util.Base64.getMimeDecoder().decode((String) invocation.getArguments()[0]);
            }
        });
        String encoded = "";
        byte[] encrypted = null;
try
    {
        byte[] publicBytes = Base64.decode(PUBLIC_KEY, Base64.DEFAULT);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance("RSA"); //or try with "RSA"
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        encrypted = cipher.doFinal("password".getBytes());
        encoded = Base64.encodeToString(encrypted, Base64.DEFAULT);
    }catch(Exception e){
        e.printStackTrace();
    }
        //return "v03v5bVbom9xQj1YdJbim7NXmdr5hqZiC//XtWxUY3J2IyC5UzpOinqat++IsDLCQ66oV3dx3C++iF4YKzFVQIamDAek51RKAx1aFK805kDhHNsHJcnRc4kuBRCEq4kdAi3E0XSt+bzV+CXYLBJMFGwOER5nfo9aeRdk66t8cC+eME8ijvvUu64xNU1Zz7367e+AS9pe9bOzRVCtuSvOFilbZ7SM1sJ3KretI+umsBMQ1CBl5OpIwLbRmeGJ2XADW5aEsxi3/a83igfo388yLC4M/WgHDjuFhkf5dOFyCAqCM1EYIx+Tsu3FZixZkc4kD34nCLgaO4uEjNvD+ddsnvGs0jQhDINPExjxuUvqfEoPSRJ98uQnFbnNkqX2SRtf61hz0xnTHo9lwg4yuhhhkEbxgk1oJ2269sfLpVoDet0OdUblOzLdbfyBTshJ6h+EYNJzICdHFuyJQC+0OBP5f5m0TcKB6m+LHrhMjPaqJxftN8p5AT/nBpNBdnqPtulPsl12rhkhZx5TR2pnjH9KURyy+mhUMn+JBjlAhEakR/GbWmN/dzX88HMPhz/jRzzh6sUdkP3hv4jVKRxa81wiWp4SxaPFTt66uw5ntKUa7CzyiJD8ZNxFK3GFYnWJxVB/CfasVI/ufrJMUekj1qwbGYLJN7C70nz/2cbJhYFB9lM=";

    }// RSAEncrypt ends here







    public String RSAEncryptOne(final String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException{
        String encodedString = "";
        byte[] encrypted = null;
        try {

            PowerMockito.mockStatic(Base64.class);
            when(Base64.encode(new byte[anyInt()], anyInt())).thenAnswer(new Answer<Object>() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    return java.util.Base64.getEncoder().encode((byte[]) invocation.getArguments()[0]);
                }
            });
            when(Base64.decode(new byte[anyInt()], anyInt())).thenAnswer(new Answer<Object>() {
                @Override
                public Object answer(InvocationOnMock invocation) throws Throwable {
                    return java.util.Base64.getMimeDecoder().decode((String) invocation.getArguments()[0]);
                }
            });
            byte[] publicBytes = Base64.decode(PUBLIC_KEY, Base64.DEFAULT);
            System.out.println("dafdafasdfas"+publicBytes.toString());
/*            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING"); //or try with "RSA"
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            encrypted = cipher.doFinal(plainText.getBytes());
            encodedString = Base64.encodeToString(encrypted, Base64.DEFAULT);*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return encodedString;
        //return "v03v5bVbom9xQj1YdJbim7NXmdr5hqZiC//XtWxUY3J2IyC5UzpOinqat++IsDLCQ66oV3dx3C++iF4YKzFVQIamDAek51RKAx1aFK805kDhHNsHJcnRc4kuBRCEq4kdAi3E0XSt+bzV+CXYLBJMFGwOER5nfo9aeRdk66t8cC+eME8ijvvUu64xNU1Zz7367e+AS9pe9bOzRVCtuSvOFilbZ7SM1sJ3KretI+umsBMQ1CBl5OpIwLbRmeGJ2XADW5aEsxi3/a83igfo388yLC4M/WgHDjuFhkf5dOFyCAqCM1EYIx+Tsu3FZixZkc4kD34nCLgaO4uEjNvD+ddsnvGs0jQhDINPExjxuUvqfEoPSRJ98uQnFbnNkqX2SRtf61hz0xnTHo9lwg4yuhhhkEbxgk1oJ2269sfLpVoDet0OdUblOzLdbfyBTshJ6h+EYNJzICdHFuyJQC+0OBP5f5m0TcKB6m+LHrhMjPaqJxftN8p5AT/nBpNBdnqPtulPsl12rhkhZx5TR2pnjH9KURyy+mhUMn+JBjlAhEakR/GbWmN/dzX88HMPhz/jRzzh6sUdkP3hv4jVKRxa81wiWp4SxaPFTt66uw5ntKUa7CzyiJD8ZNxFK3GFYnWJxVB/CfasVI/ufrJMUekj1qwbGYLJN7C70nz/2cbJhYFB9lM=";

    }// RSAEncrypt ends here




















}// class ends here