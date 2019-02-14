package com.fekrah.dolphin.client.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.fekrah.dolphin.R;
import com.fekrah.dolphin.models.FuckenResponse;
import com.fekrah.dolphin.server.BaseClient;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallUsFragment extends Fragment {

    @BindViews({R.id.name,R.id.message,R.id.phone})
    List<EditText> nameViews;

    public CallUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_us, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);


    }

    @OnClick(R.id.btn_send)
    void send(){

        if (nameViews.get(0).getText().toString().equals("")||nameViews.get(1).getText().toString().equals("")||nameViews.get(2).getText().toString().equals("")){
            Toast.makeText(getActivity(), ""+getString(R.string.fill_all), Toast.LENGTH_SHORT).show();
           return;
        }else {
            Dialog dialog = new Dialog(getActivity());
            dialog.show();
            BaseClient.getApi().contactUs(nameViews.get(0).getText().toString(),nameViews.get(1).getText().toString(),nameViews.get(2).getText().toString())
                    .enqueue(new Callback<FuckenResponse>() {
                        @Override
                        public void onResponse(Call<FuckenResponse> call, Response<FuckenResponse> response) {
                            if (response.body().getSuccess_contact()==1){
                                Toast.makeText(getActivity(), "تم اراسل الرسالة", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "لم يتم اراسل الرسالة", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<FuckenResponse> call, Throwable t) {
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "لم يتم اراسل الرسالة", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }
}
