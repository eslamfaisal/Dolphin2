package com.fekrah.dolphin.client.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.dolphin.R;
import com.fekrah.dolphin.client.activities.RegisterActivity;
import com.fekrah.dolphin.helper.SharedHelper;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.address)
    TextView address;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.phone)
    TextView phone;

    @BindView(R.id.image)

    SimpleDraweeView image;
    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        name.setText(SharedHelper.getKey(getActivity(), RegisterActivity.USER_NAME));
        phone.setText(SharedHelper.getKey(getActivity(), RegisterActivity.PHONE));
        email.setText(SharedHelper.getKey(getActivity(), RegisterActivity.EMAIL));
        address.setText(SharedHelper.getKey(getActivity(), RegisterActivity.ADDRESS));

        image.setImageURI("http://dolphin-ksa.com/uploads/images/"+SharedHelper.getKey(getActivity(), RegisterActivity.IMAGE));
    }
}
