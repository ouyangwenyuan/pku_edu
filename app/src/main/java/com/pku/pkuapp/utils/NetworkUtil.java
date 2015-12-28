package com.pku.pkuapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/**
 *
* @ClassName: NetworkUtil
* @Description: 工具类包括： 判断网络请求
* @author liuqi qiliu_17173@cyou-inc.com
* @date 2014-7-25 下午5:07:46
 */
public class NetworkUtil {

	/**
	 * 是否禁用2G/3G网,只有是Wifi连接才可以是有效连接 WangQing 2013-8-13 boolean
	 * @param context
	 *            上下文
	 * @param isforbid2Gor3G
	 *            判断是2G 还是 3G
	 * @return 是否可用
	 */
	public static boolean isNetworkAvailable(Context context,
			Boolean isforbid2Gor3G) {
		Boolean returnV = false;
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			return false;
		} else {
			// 获取代表联网状态的NetWorkInfo对象
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null) {
				boolean available = info.isAvailable();
				if (isforbid2Gor3G) {
					// 只有Wifi 可用
					// 获取当前的网络连接是否可用
					State state = connectivity.getNetworkInfo(
							ConnectivityManager.TYPE_WIFI).getState();
					if (State.CONNECTED == state && available) {
						returnV = true;
					}
				} else {
					// 可以使用3G 网络访问
					returnV = available;
				}
			} else {
				return false;
			}
		}
		return returnV;

	}


	/**
	 * 检查网络是否连通
	 *
	 * @return true or false
	 */
	public static boolean isNetworkAvailable(Context context) {
		return isNetworkAvailable(context, false);
	}
}
