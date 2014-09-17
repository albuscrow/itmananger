package com.itmanapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 
 * @author wp
 * @create date: 2014/7/29
 * @class description 网络检测
 */
public class NetworkCheck {

	/**
	 * 检测网络状态的方法
	 * 
	 * @param context
	 * @return
	 */
	public static NetworkInfo check(Context context) {

		NetworkInfo activeNetInfo;

		try {
			// 获取系统的连接服务
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			// 获取网络的连接情况
			// 判断是否得到 ，否则报错 NullPointerException
			// if(connectivityManager!=null)
			// {
			activeNetInfo = connectivityManager.getActiveNetworkInfo();
			// }else
			// {
			// activeNetInfo=null;
			// }
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			activeNetInfo = null;
		}
		return activeNetInfo;
	}

	
	/**
	 * 判断当前网络是不是wifi，
	 * @param mComContext
	 * @return true wifi
	 * 			false  手机网络
	 */
	public static boolean judgeIsWifi(Context  mComContext){
		boolean  isWifi=false;
		ConnectivityManager connectMgr = (ConnectivityManager) mComContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo info = connectMgr.getActiveNetworkInfo();
		if(info.getType() == ConnectivityManager.TYPE_WIFI){
			isWifi=true;
		}
		
		//判断手机网络
		if(info !=null && info.getType() ==  ConnectivityManager.TYPE_MOBILE){
			isWifi=false;
		}
		return isWifi;
		
	}
	
}
