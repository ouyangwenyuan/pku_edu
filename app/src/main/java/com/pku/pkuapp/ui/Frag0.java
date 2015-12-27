package com.pku.pkuapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseFragment;
import com.pku.pkuapp.widget.SlideShowView;

public class Frag0 extends BaseFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_0, container, false);
//        SlideShowView slideShowView = new SlideShowView(getActivity());
//        slideShowView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, (int) getActivity().getResources().getDimension(R.dimen.slide_pic_height)));
//        FrameLayout parentView = (FrameLayout) view.findViewById(R.id.show_pictures);
//        parentView.addView(slideShowView);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
