package com.fekrah.dolphin;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
//import com.yayandroid.locationmanager.LocationManager;

public class App extends Application {

//
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference mUserReference;
//    private FirebaseAuth mAuth;
//    private FirebaseUser mCurrentUser;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
      //  LocationManager.enableLog(true);
        Fresco.initialize(this);

//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mAuth = FirebaseAuth.getInstance();
//        mCurrentUser = mAuth.getCurrentUser();
//        if (mCurrentUser != null) {
//            mFirebaseDatabase.getReference().child("Customer_current_order")
//                    .child(mCurrentUser.getUid()).onDisconnect()
//                    .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//
//                }
//            });
//
//        }

    }
}
