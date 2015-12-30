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
import com.pku.pkuapp.base.MyConfig;
import com.pku.pkuapp.base.MyLog;

/**
 * Created by admin on 15/12/29.
 */
public class ClassQueryActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        ListView lv = (ListView) findViewById(R.id.lv_class_list);
        final String classes[] = MyConfig.classes;
        lv.setAdapter(new ClassesAdapter(this, classes));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLog.i("click position" + i);
                Intent intent = new Intent(ClassQueryActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
