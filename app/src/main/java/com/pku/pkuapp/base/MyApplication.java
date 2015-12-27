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

/**
 * Created by admin on 15/12/27.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(this);
    }

    private void initImageLoader(Context context) {
//        BitmapDisplayer bitmapDisplayer = new FadeInBitmapDisplayer(context.getResources().getInteger(android.R.integer.config_shortAnimTime), true, false, false);
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
                .cacheOnDisk(true).resetViewBeforeLoading(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context.getApplicationContext()).threadPriority(Thread.MAX_PRIORITY)
                .defaultDisplayImageOptions(displayImageOptions).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }
}
