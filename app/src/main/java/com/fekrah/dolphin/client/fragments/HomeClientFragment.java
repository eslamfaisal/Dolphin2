package com.fekrah.dolphin.client.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.BookTechnicalClientActivity;
import com.fekrah.dolphin.client.adapters.SpecializationsAdapter;
import com.fekrah.dolphin.models.Specialize;
import com.fekrah.dolphin.server.BaseClient;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class HomeClientFragment extends Fragment {

    public static String SPECIALIZE_DATA= "SPECIALIZE_DATA";

    public HomeClientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_client, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

    }


    @OnClick(R.id.kahrbaae)
    void kahrbaae(){
        Intent intent = new Intent(getActivity(), BookTechnicalClientActivity.class);
        intent.putExtra(SPECIALIZE_DATA,new Specialize("1","كهربائي"));
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.takeef)
    void takeef(){
        Intent intent = new Intent(getActivity(), BookTechnicalClientActivity.class);
        intent.putExtra(SPECIALIZE_DATA,new Specialize("2","فنى تكييف"));
        getActivity().startActivity(intent);
    }

    @OnClick(R.id.sbaak)
    void sbaak(){
        Intent intent = new Intent(getActivity(), BookTechnicalClientActivity.class);
        intent.putExtra(SPECIALIZE_DATA,new Specialize("3","سباك"));
        getActivity().startActivity(intent);
    }

}
