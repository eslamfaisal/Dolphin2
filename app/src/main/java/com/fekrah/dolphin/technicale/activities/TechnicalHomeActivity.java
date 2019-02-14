package com.fekrah.dolphin.technicale.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.ClientHomeActivity;
import com.fekrah.dolphin.client.activities.RegisterActivity;
import com.fekrah.dolphin.client.fragments.CallUsFragment;
import com.fekrah.dolphin.client.fragments.HomeClientFragment;
import com.fekrah.dolphin.client.fragments.MyAccountFragment;
import com.fekrah.dolphin.client.fragments.MyOrdersClientFragment;
import com.fekrah.dolphin.client.fragments.NotificationClientFragment;
import com.fekrah.dolphin.client.fragments.TermsFragment;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.technicale.fragments.TechnicalHomeFragment;

import static com.fekrah.dolphin.client.activities.ClientLoginActivity.IS_LOGIN_TYPE;

public class TechnicalHomeActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.container, new TechnicalHomeFragment()).commit();
                    return true;
                case R.id.navigation_account:
                    fragmentManager.beginTransaction().replace(R.id.container, new MyAccountFragment()).commit();//                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    fragmentManager.beginTransaction().replace(R.id.container, new NotificationClientFragment()).commit();//                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    NavigationView navigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager.beginTransaction().add(R.id.container, new TechnicalHomeFragment()).commit();

        logIn();
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
            fragmentManager.beginTransaction().replace(R.id.container, new TechnicalHomeFragment()).commit();
        } else if (id == R.id.nav_call_us) {
            fragmentManager.beginTransaction().replace(R.id.container, new CallUsFragment()).commit();//
            //          startActivity(intent);
        } else if (id == R.id.nav_out) {
            SharedHelper.putKey(getApplicationContext(), IS_LOGIN_TYPE, "");
            SharedHelper.putKey(getApplicationContext(), RegisterActivity.IS_LOGIN, "no");
            startActivity(new Intent(getApplicationContext(), ClientHomeActivity.class));
            finish();
        } else if (id == R.id.nav_profile) {
            fragmentManager.beginTransaction().replace(R.id.container, new MyAccountFragment()).commit();

        } else if (id == R.id.nav_terms) {
            fragmentManager.beginTransaction().replace(R.id.container, new TermsFragment()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logIn(){
        if (SharedHelper.getKey(this, RegisterActivity.IS_LOGIN).equals("yes")) {
            SimpleDraweeView simpleDraweeView = navigationView.getHeaderView(0).findViewById(R.id.profile_image);
            TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profile_name);
            Log.d("SimpleDraweeView", "onCreate: " + "http://dolphin-ksa.com/uploads/images/" + SharedHelper.getKey(this, RegisterActivity.IMAGE));
            simpleDraweeView.setImageURI("http://dolphin-ksa.com/uploads/images/" + SharedHelper.getKey(this, RegisterActivity.IMAGE));
            textView.setText(SharedHelper.getKey(this, RegisterActivity.USER_NAME));
            navigationView.getMenu().findItem(R.id.nav_out).setVisible(true);


        }
    }

}
