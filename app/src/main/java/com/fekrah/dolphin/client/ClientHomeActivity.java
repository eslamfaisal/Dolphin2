package com.fekrah.dolphin.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.activities.ClientLoginActivity;
import com.fekrah.dolphin.client.activities.RegisterActivity;
import com.fekrah.dolphin.client.fragments.CallUsFragment;
import com.fekrah.dolphin.client.fragments.HomeClientFragment;
import com.fekrah.dolphin.client.fragments.MyAccountFragment;
import com.fekrah.dolphin.client.fragments.MyOrdersClientFragment;
import com.fekrah.dolphin.client.fragments.NotificationClientFragment;
import com.fekrah.dolphin.client.fragments.TermsFragment;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.technicale.activities.TechnicalLoginActivity;

import static com.fekrah.dolphin.client.activities.ClientLoginActivity.IS_LOGIN_TYPE;

public class ClientHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager = getSupportFragmentManager();
    MyOrdersClientFragment myOrdersClientFragment = new MyOrdersClientFragment();
    HomeClientFragment homeClientFragment = new HomeClientFragment();
    CallUsFragment callUsFragment = new CallUsFragment();
    MyAccountFragment myAccountFragment = new MyAccountFragment();

    NotificationClientFragment notificationClientFragment = new NotificationClientFragment();

   public static Activity activity;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.navigation_home) {
                fragmentManager.beginTransaction().replace(R.id.container, homeClientFragment).commit();
            } else if (id == R.id.navigation_orders) {

                if (!SharedHelper.getKey(getApplicationContext(), RegisterActivity.IS_LOGIN).equals("yes")) {
                    startLoginActivity();
                } else {
                    fragmentManager.beginTransaction().replace(R.id.container, myOrdersClientFragment).commit();
                    invalidateOptionsMenu();
                }

            } else if (id == R.id.navigation_account) {
                if (!SharedHelper.getKey(getApplicationContext(), RegisterActivity.IS_LOGIN).equals("yes")) {
                    startLoginActivity();
                } else {
                    fragmentManager.beginTransaction().replace(R.id.container, myAccountFragment).commit();
                }

            } else if (id == R.id.navigation_notifications) {
                if (!SharedHelper.getKey(getApplicationContext(), RegisterActivity.IS_LOGIN).equals("yes")) {
                    startLoginActivity();
                } else {
                    fragmentManager.beginTransaction().replace(R.id.container, notificationClientFragment).commit();
                    invalidateOptionsMenu();
                }
            }
            return false;
        }
    };


    public static void logOut() {
        SharedHelper.putKey(activity, IS_LOGIN_TYPE, "");
        SharedHelper.putKey(activity, RegisterActivity.IS_LOGIN, "no");
        SimpleDraweeView simpleDraweeView = navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profile_name);
        simpleDraweeView.setImageURI("http://dolphin-ksa.com/uploads/images/"+SharedHelper.getKey(activity, RegisterActivity.IMAGE));
        textView.setText("");
        navigationView.getMenu().findItem(R.id.nav_out).setVisible(false);
        navigationView.getMenu().findItem(R.id.nave_in).setVisible(true);

    }


    public static NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
        activity = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager.beginTransaction().add(R.id.container, new HomeClientFragment()).commit();

       logIn();


    }
    private void logIn(){
        if (SharedHelper.getKey(this, RegisterActivity.IS_LOGIN).equals("yes")) {
            SimpleDraweeView simpleDraweeView = navigationView.getHeaderView(0).findViewById(R.id.profile_image);
            TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profile_name);
            Log.d("SimpleDraweeView", "onCreate: " + "http://dolphin-ksa.com/uploads/images/" + SharedHelper.getKey(this, RegisterActivity.IMAGE));
            simpleDraweeView.setImageURI("http://dolphin-ksa.com/uploads/images/" + SharedHelper.getKey(this, RegisterActivity.IMAGE));
            textView.setText(SharedHelper.getKey(this, RegisterActivity.USER_NAME));
            navigationView.getMenu().findItem(R.id.nav_out).setVisible(true);
            navigationView.getMenu().findItem(R.id.nave_in).setVisible(false);

        }else {
            logOut();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            fragmentManager.beginTransaction().replace(R.id.container, homeClientFragment).commit();
        } else if (id == R.id.nav_call_us) {
            fragmentManager.beginTransaction().replace(R.id.container, callUsFragment).commit();//
            //          startActivity(intent);
        } else if (id == R.id.nav_out) {
            logOut();
        } else if (id == R.id.nav_my_orders) {

            if (!SharedHelper.getKey(this, RegisterActivity.IS_LOGIN).equals("yes")) {
                startLoginActivity();
            } else {
                fragmentManager.beginTransaction().replace(R.id.container, myOrdersClientFragment).commit();
                invalidateOptionsMenu();
            }

        } else if (id == R.id.nav_profile) {
            if (!SharedHelper.getKey(this, RegisterActivity.IS_LOGIN).equals("yes")) {
                startLoginActivity();
            } else {
                fragmentManager.beginTransaction().replace(R.id.container, myAccountFragment).commit();

                invalidateOptionsMenu();
            }

        } else if (id == R.id.nav_terms) {
            fragmentManager.beginTransaction().replace(R.id.container, new TermsFragment()).commit();

        } else if (id == R.id.nave_in) {
            startLoginActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==12587&&resultCode==123){
            logIn();
        }
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, ClientLoginActivity.class);
        startActivityForResult(intent, 12587);
    }


    @Override
    protected void onResume() {
        super.onResume();
        logIn();
    }
}
