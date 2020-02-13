package com.example.mhmsbmr;
import android.widget.Button;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.mhmsbmr.Login.LoginBmr;
import com.example.mhmsbmr.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginUnitTest {

    @Rule
    public ActivityTestRule<LoginBmr> activityRule
            = new ActivityTestRule<>(LoginBmr.class);
    private LoginBmr activity = activityRule.getActivity();

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