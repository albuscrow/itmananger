package com.itmanapp.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.itmanapp.R;

/**
 * @date 2014-7-15
 * @author wp
 * @class description 网络请求错误处理
 */
public class VolleyErrorHelper {
	/**
	 * 根据错误类型返回对应的提示信息
	 * 
	 * @param error
	 * @param context
	 * @return
	 */
	public static String getMessage(Object error, Context context) {
		if (error instanceof TimeoutError) {// 连接超时
			return context.getString(R.string.loadingNo);
		} else if (isServerProblem(error)) {
			return handleServerError(error, context);
		} else if (isNetworkProblem(error)) {// 无网络
			return context.getString(R.string.noNetwork);
		}
		// 服务器错误
		return context.getString(R.string.serverError);
	}

	/**
	 * 网络问题
	 * 
	 * @param error
	 * @return
	 */
	private static boolean isNetworkProblem(Object error) {
		return (error instanceof NetworkError)
				|| (error instanceof NoConnectionError);
	}

	/**
	 * 服务端问题
	 * 
	 * @param error
	 * @return
	 */
	private static boolean isServerProblem(Object error) {
		return (error instanceof ServerError)
				|| (error instanceof AuthFailureError);
	}

	/**
	 * 
	 * 服务端错误类型
	 * 
	 * @param err
	 * @param context
	 * @return
	 */
	private static String handleServerError(Object err, Context context) {
		VolleyError error = (VolleyError) err;

		NetworkResponse response = error.networkResponse;

		if (response != null) {
			switch (response.statusCode) {
			case 404:
			case 422:
			case 401:
				try {
					// server might return error like this { "error":
					// "Some error occured" }
					// // Use "Gson" to parse the result
					// HashMap<String, String> result = new Gson().fromJson(new
					// String(response.data),
					// new TypeToken<Map<String, String>>() {
					// }.getType());
					//
					// if (result != null && result.containsKey("error")) {
					// return result.get("error");
					// }

				} catch (Exception e) {
					e.printStackTrace();
				}
				// invalid request
				return error.getMessage();

			default:
				return context.getString(R.string.loadingNo);
			}
		}
		return context.getString(R.string.serverError);
	}
}
