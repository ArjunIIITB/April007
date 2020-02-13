package com.example.mhmsbmr;


import android.widget.Button;


import androidx.test.rule.ActivityTestRule;

import com.example.mhmsbmr.Login.LoginBmr;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNull;


public class UnitTestLoginGUI {

    @Rule
    public ActivityTestRule<LoginBmr> activityRule
            = new ActivityTestRule<>(LoginBmr.class);
    private LoginBmr activity = activityRule.getActivity();

    @Before
    public void setUp() throws Exception{

    }



    @Test
    public void clickLoginButton() {
        Button button = activity.findViewById(R.id.btnLogin);
        if(button == null){
            System.out.println("null");
        }else{
            System.out.println("not null");
        }
        assertNull(button);
    }
}