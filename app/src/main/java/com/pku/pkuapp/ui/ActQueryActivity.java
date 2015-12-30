package com.pku.pkuapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseActivity;
import com.pku.pkuapp.base.MyLog;
import com.pku.pkuapp.model.LectureData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15/12/29.
 */
public class ActQueryActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);
        ListView lv = (ListView) findViewById(R.id.lv_act_list);
        final List<LectureData> lectureDatas = initLectureData();
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return lectureDatas.size();
            }

            @Override
            public Object getItem(int i) {
                return lectureDatas.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(ActQueryActivity.this).inflate(R.layout.lv_act_item, null);
                }
                TextView titleView = (TextView) view.findViewById(R.id.tv_act_name);
                TextView addrView = (TextView) view.findViewById(R.id.tv_act_where);
                TextView timeView = (TextView) view.findViewById(R.id.tv_act_time);
                TextView priceView = (TextView) view.findViewById(R.id.tv_act_price);
                LectureData lectureData = lectureDatas.get(i);
                titleView.setText(lectureData.getTitle());
                addrView.setText(lectureData.getWhere());
                timeView.setText(lectureData.getTime());
                priceView.setText(lectureData.getPrice());
                return view;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLog.i("click position" + i);
                Intent intent = new Intent(ActQueryActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<LectureData> initLectureData() {
        List<LectureData> lectureDatas = new ArrayList<LectureData>();
        for (int i = 0; i < 10; i++) {
            LectureData lectureData = new LectureData(String.valueOf(i), "baidu.com", "李尔在此", "2015-12-31 至 2016-01-01", "讲堂多功能厅", "20 40 60 80 VIP 100 150 200", "");
            lectureDatas.add(lectureData);
        }
        return lectureDatas;
    }
}
