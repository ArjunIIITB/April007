package com.example.mhmsbmr;

// Working fine do not touch it.


import android.view.View;
import android.widget.Button;

import com.example.mhmsbmr.Login.LoginBmr;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test

        public void useAppContext() {
            // Context of the app under test.
            LoginBmr login = new LoginBmr();
            //assertEquals("hello world", login.test("hello world"));
            login.test4(235);
        }

     @Test

        public void testSaltGeneration(){
        String userName = "psm321";
         String password = "Test@123";
        LoginBmr login = new LoginBmr();
        String []salts = login.getSalt(userName);
        login.loginTest(salts, userName, password);

     }

     @Test

        public void captchaGeneration(){
        LoginBmr login = new LoginBmr();
        login.getCaptcha();
     }



     @Test
    public void clickTes(){

         //Button button = LoginBmr findViewById()

     }

    @Test
    public void getOrgTest(){
        LoginBmr loginBmr = new LoginBmr();
        //loginBmr.getAssociatedOrganisation("dummy");
    }



}