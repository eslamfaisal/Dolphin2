package com.fekrah.dolphin.client.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.activities.RegisterActivity;
import com.fekrah.dolphin.client.adapters.NotificationsAdapter;
import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.models.Notification;
import com.fekrah.dolphin.server.BaseClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationClientFragment extends Fragment {

    @BindView(R.id.notifications)
    RecyclerView notifications;

    public NotificationClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_client, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        notifications.setLayoutManager(new LinearLayoutManager(getActivity()));
        BaseClient.getApi().getNotifications(SharedHelper.getKey(getActivity(), RegisterActivity.USER_ID)).enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                notifications.setAdapter(new NotificationsAdapter(response.body(),getActivity()));
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {

            }
        });
    }
}
