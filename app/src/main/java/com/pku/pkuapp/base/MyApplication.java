package com.pku.pkuapp.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.testin.agent.TestinAgent;
import com.testin.agent.TestinAgentConfig;

/**
 * Created by admin on 15/12/27.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalManager.getManager().init(this);
        initPreim();
    }

    private void initPreim() {
        if (MyLog.isDebuging()) {
            TestinAgentConfig config = new TestinAgentConfig.Builder(this)
                    .withReportOnlyWifi(true)     // 仅在 WiFi 下上报崩溃信息
                    .withQAMaster(true).withDebugModel(true)     // 开启摇一摇反馈
                    .withErrorActivity(true).withOpenCrash(true).withOpenEx(true).withLogCat(true)
                    .build();
            TestinAgent.init(config);
        }
    }
}
