package com.fekrah.dolphin.client.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.ClientHomeActivity;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.models.RegisterResponse;
import com.fekrah.dolphin.server.BaseClient;
import com.fekrah.dolphin.technicale.activities.TechnicalLoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientLoginActivity extends AppCompatActivity {

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

    @BindView(R.id.pass)
    EditText pass;

    @BindView(R.id.phone)
    EditText email;
    public static Activity activity;
    public static final  String IS_LOGIN_TYPE = "IS_LOGIN_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);
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

                if (response.body().getSuccess_login() == 1) {
                    RegisterResponse user = response.body();
                    SharedHelper.putKey(getApplicationContext(), IS_LOGIN, "yes");
                    SharedHelper.putKey(getApplicationContext(), IS_LOGIN_TYPE, "عميل");
                    SharedHelper.putKey(getApplicationContext(), CITY, user.getAr_city_title());
                    SharedHelper.putKey(getApplicationContext(), USER_NAME, user.getUser_full_name());
                    SharedHelper.putKey(getApplicationContext(), ADDRESS, user.getUser_address());
                    SharedHelper.putKey(getApplicationContext(), EMAIL, user.getUser_email());
                    SharedHelper.putKey(getApplicationContext(), IMAGE, user.getUser_photo());
                    SharedHelper.putKey(getApplicationContext(), TOAKEN, user.getUser_token_id());
                    SharedHelper.putKey(getApplicationContext(), PHONE, user.getUser_photo());
                    SharedHelper.putKey(getApplicationContext(), USER_ID, user.getUser_id());

                    Log.d("llllll", "onResponse:  =  http://dolphin-ksa.com/uploads/images/" + user.getUser_photo());
//                    if (response.body().getUser_type().equals("1")) {
//                        startActivity(new Intent(getApplicationContext(), ClientHomeActivity.class));
//                    } else if (response.body().getUser_type().equals("2")) {
//                        startActivity(new Intent(getApplicationContext(), TechnicalLoginActivity.class));
//                    }
                    setResult(123);
                    finish();
                } else {
                    Toast.makeText(ClientLoginActivity.this, getString(R.string.registerd), Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(ClientLoginActivity.this, getString(R.string.error_try_again), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    public void goMain(View view) {
        startActivity(new Intent(this, ClientHomeActivity.class));
    }

    public void goRegister(View view) {

        startActivityForResult(new Intent(getApplicationContext(), RegisterActivity.class), 12587);
    }

    public void reset(View view) {

    }

    public void skip(View view) {
        SharedHelper.putKey(getApplicationContext(), IS_LOGIN, "no");

    }

    public void technicalLogin(View view) {

        startActivityForResult(new Intent(getApplicationContext(), TechnicalLoginActivity.class), 12587);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==12587&&resultCode==123){
            setResult(123);
            finish();
        }
    }
}
