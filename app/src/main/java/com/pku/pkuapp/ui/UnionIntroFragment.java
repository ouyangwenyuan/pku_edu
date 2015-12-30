package com.pku.pkuapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseFragment;

/**
 * Created by admin on 15/12/30.
 */
public class UnionIntroFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introduction, null);
        view.findViewById(R.id.tv_union_introduction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WebViewActivity.class));
            }
        });
        view.findViewById(R.id.tv_union_frame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UnionDepartActivity.class));
            }
        });
        return view;
    }
}
