package com.pku.pkuapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseFragment;
import com.pku.pkuapp.widget.MySlipSwitch;

public class MineFragment extends BaseFragment {
    private boolean openNet = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_2, container, false);

        MySlipSwitch switchBtn = (MySlipSwitch) view.findViewById(R.id.iv_open_button);
        switchBtn.setSwitchState(openNet);
        switchBtn.setImageResource(R.drawable.switch_background_open, R.drawable.switch_background_close, R.drawable.switch_button);
        switchBtn.setOnSwitchListener(new MySlipSwitch.OnSwitchListener() {

            @Override
            public void onSwitched(boolean isSwitchOn) {
                if (isSwitchOn) {
                    openNet = true;
                    //spu.setValue(MyConstants.SharedConfigKey.CONFIG_IS_SHOW_ALARM, true);
                } else {
                    openNet = false;
                    //spu.setValue(MyConstants.SharedConfigKey.CONFIG_IS_SHOW_ALARM, false);
                }
            }
        });
        View setBtn = view.findViewById(R.id.tv_settting);
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });
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
