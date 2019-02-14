package com.fekrah.dolphin.technicale.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.ClientHomeActivity;
import com.fekrah.dolphin.client.activities.ClientLoginActivity;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.models.RegisterResponse;
import com.fekrah.dolphin.server.BaseClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fekrah.dolphin.client.activities.ClientLoginActivity.IS_LOGIN_TYPE;

public class TechnicalLoginActivity extends AppCompatActivity {


    public static final String USER_NAME = "user_name";
    public static final String IS_LOGIN = "LOGIN";
    public static final String USER_ID = "user_id";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String SECOND_NAME = "SECOND_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";
    public static final String CITY = "CITY";
    public static final String ADDRESS = "ADDRESS";
    public static final String IMAGE = "IMAGE";
    public static final String TOAKEN = "TOAKEN";
    public static final String ACTIVE = "ACTIVR";
    public static Activity activity;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.phone)
    EditText email;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_login);
        ButterKnife.bind(this);
        activity = this;
    }

    @OnClick(R.id.log_in)
    void logInUserClient() {
        final Dialog dialog = new Dialog(this);
        dialog.show();
        if (
                email.getText().toString().equals("") ||
                        pass.getText().toString().equals("")) {
            dialog.dismiss();

            return;
        }
        BaseClient.getApi().logInClient(
                email.getText().toString(),
                pass.getText().toString()
        ).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                Log.d("llllll", "getSuccess_login:  " + response.body().getSuccess_login());
                if (response.body().getSuccess_login() == 1) {
                    RegisterResponse user = response.body();
                    SharedHelper.putKey(getApplicationContext(), IS_LOGIN, "yes");
                    SharedHelper.putKey(getApplicationContext(), IS_LOGIN_TYPE, "فنى");
                    SharedHelper.putKey(getApplicationContext(), CITY, user.getAr_city_title());
                    SharedHelper.putKey(getApplicationContext(), USER_NAME, user.getUser_full_name());
                    SharedHelper.putKey(getApplicationContext(), ADDRESS, user.getUser_address());
                    SharedHelper.putKey(getApplicationContext(), EMAIL, user.getUser_email());
                    SharedHelper.putKey(getApplicationContext(), IMAGE, user.getUser_photo());
                    SharedHelper.putKey(getApplicationContext(), TOAKEN, user.getUser_token_id());
                    SharedHelper.putKey(getApplicationContext(), PHONE, user.getUser_photo());
                    SharedHelper.putKey(getApplicationContext(), USER_ID, user.getUser_id());

                    Log.d("llllll", "onResponse:  =  http://dolphin-ksa.com/uploads/images/" + user.getUser_photo());
                    if (response.body().getUser_type().equals("1")) {
                        startActivity(new Intent(getApplicationContext(), ClientHomeActivity.class));
                    } else if (response.body().getUser_type().equals("2")) {
                        startActivity(new Intent(getApplicationContext(), TechnicalHomeActivity.class));
                    }

                    ClientLoginActivity.activity.finish();
                    ClientHomeActivity.activity.finish();
                    TechnicalLoginActivity.activity.finish();
                    finish();
                } else {
                    for (String error : response.body().getError_msg()){
                        Toast.makeText(TechnicalLoginActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Toast.makeText(TechnicalLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    public void goRegister(View view) {
        startActivity(new Intent(this, TechnicalRegisterActivity.class));
    }

    public void reset(View view) {

    }

    public void skip(View view) {
        SharedHelper.putKey(getApplicationContext(), IS_LOGIN, "no");

    }

}
