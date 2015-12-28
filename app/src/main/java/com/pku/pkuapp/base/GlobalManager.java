package com.pku.pkuapp.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.pku.pkuapp.R;


import java.util.ArrayList;
import java.util.List;

public class GlobalManager {

    private static final int DB_VERSION = 0;
    private static GlobalManager manager;

    private Context context;
    private DbUtils finalDb;
    private BitmapUtils bitmapUtils;
    private HttpUtils httpUtils;

    public static final GlobalManager getManager() {
        if (manager == null) {
            manager = new GlobalManager();
        }
        return manager;
    }


    public void init(Context context) {
        this.context = context;
        httpUtils = new HttpUtils();
        initImageLoader(context);
        initDb(context);
        initXutlsBitmapUtils(context);
    }

    private void initXutlsBitmapUtils(Context context) {
        bitmapUtils = new BitmapUtils(context);
        bitmapUtils.configDefaultAutoRotation(true).configDefaultConnectTimeout(60 * 1000).configThreadPoolSize(10).configDefaultLoadFailedImage(R.drawable.show_picture_0)
                .configMemoryCacheEnabled(true).configDiskCacheEnabled(true).configDefaultLoadingImage(R.drawable.show_picture_0);
    }

    public Context getContext() {
        return context;
    }

    public BitmapUtils getBitmapUtils() {
        return bitmapUtils;
    }

    public HttpUtils getHttpUtils() {
        return httpUtils;
    }

    public DbUtils getDb() {
        return finalDb;
    }

    /**
     * 初始化图片加载组件 ImageLoader
     *
     * @param context
     */
    private void initImageLoader(Context context) {
        BitmapDisplayer bitmapDisplayer = new FadeInBitmapDisplayer(context.getResources().getInteger(android.R.integer.config_shortAnimTime), true, false, false);
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
                .cacheOnDisk(true).displayer(bitmapDisplayer).resetViewBeforeLoading(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT).build();
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context.getApplicationContext()).threadPriority(Thread.MAX_PRIORITY)
                .defaultDisplayImageOptions(displayImageOptions).build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

    private void initDb(final Context context) {
        finalDb = DbUtils.create(context, "pku_edu.db", DB_VERSION, new DbUtils.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbUtils dbUtils, int i, int i1) {
            }
        });
    }

}
