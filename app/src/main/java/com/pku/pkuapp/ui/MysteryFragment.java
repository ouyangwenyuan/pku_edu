package com.pku.pkuapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseFragment;
import com.pku.pkuapp.base.MyLog;

/**
 * Created by admin on 15/12/30.
 */
public class MysteryFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_item, null);
        ListView lv = (ListView) view.findViewById(R.id.lv_content);
        final String classes[] = {"公共课", "法学院", "外国语学院", "社会学院", "计算机科学与技术系", "信息管理学院", "建筑与城市规划学院", "文学院艺术硕士", "新闻传播学院", "生命科学学院", "物理学院", "数学系"};
        lv.setAdapter(new ClassesAdapter(getActivity(), classes));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLog.i("click position" + i);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
