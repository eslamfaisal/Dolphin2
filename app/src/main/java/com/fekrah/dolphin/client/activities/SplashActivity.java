package com.fekrah.dolphin.client.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.ClientHomeActivity;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.technicale.activities.TechnicalHomeActivity;
import com.fekrah.dolphin.technicale.activities.TechnicalLoginActivity;

import static com.fekrah.dolphin.client.activities.ClientLoginActivity.IS_LOGIN_TYPE;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d("SplashActivity", "onCreate: "+SharedHelper.getKey(this,RegisterActivity.USER_ID));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedHelper.getKey(getApplicationContext(),IS_LOGIN_TYPE).equals(""))
                startActivity(new Intent(getApplicationContext(), ClientHomeActivity.class));
                else {
                    if (SharedHelper.getKey(getApplicationContext(),IS_LOGIN_TYPE).equals("فنى")){
                        startActivity(new Intent(getApplicationContext(), TechnicalHomeActivity.class));
                    }else {
                        startActivity(new Intent(getApplicationContext(), ClientHomeActivity.class));
                    }

                }
                finish();

            }
        },1500);
    }
}
