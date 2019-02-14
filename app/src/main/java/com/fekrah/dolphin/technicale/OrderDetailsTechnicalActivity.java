package com.fekrah.dolphin.technicale;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.fragments.CurrentOrdersClientFragment;
import com.fekrah.dolphin.client.fragments.NewOrdersFragment;
import com.fekrah.dolphin.technicale.fragments.TechnicalCurrentOrderFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class OrderDetailsTechnicalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_technical);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(this.getString(R.string.new_orders), TechnicalCurrentOrderFragment.class)
                .add(this.getString(R.string.current_orders), CurrentOrdersClientFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

    }
}
