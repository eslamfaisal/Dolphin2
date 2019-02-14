package com.fekrah.dolphin.client.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.activities.RegisterActivity;
import com.fekrah.dolphin.client.adapters.CurrentOrdersAdapters;
import com.fekrah.dolphin.client.adapters.NewOrdersAdapters;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.models.OrderResponse;
import com.fekrah.dolphin.server.BaseClient;
import com.fekrah.dolphin.technicale.OrderDetailsTechnicalActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewOrdersFragment extends Fragment {


    @BindView(R.id.current_orders_recycler)
    RecyclerView currentOrdersRecycler;


    public NewOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_old_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        currentOrdersRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        BaseClient.getApi().getNewtOrders(SharedHelper.getKey(getActivity(), RegisterActivity.USER_ID))
                .enqueue(new Callback<List<OrderResponse>>() {
                    @Override
                    public void onResponse(Call<List<OrderResponse>> call, Response<List<OrderResponse>> response) {

                        if (response.body().size()>0){
                            currentOrdersRecycler.setAdapter(new NewOrdersAdapters(response.body(),getActivity()));
                        }

                    }

                    @Override
                    public void onFailure(Call<List<OrderResponse>> call, Throwable t) {

                    }
                });
    }
}
