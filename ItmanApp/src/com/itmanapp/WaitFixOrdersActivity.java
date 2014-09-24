package com.itmanapp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.adapter.WorkOrderAdatper;
import com.itmanapp.entity.WorkOrderEntity;
import com.itmanapp.json.GetWorkOrderJson;
import com.itmanapp.util.AppManager;
import com.itmanapp.util.NetworkCheck;
import com.itmanapp.widget.listview.XListView;
import com.itmanapp.widget.listview.XListView.IXListViewListener;

/**
 * @date 2014-8-1
 * @author wangpeng
 * @class description 待确认工单页面
 * 
 */
public class WaitFixOrdersActivity extends Activity implements OnItemClickListener,OnClickListener{

	/** 返回按钮 */
	private ImageView backBtn;
	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**列表控件*/
	private XListView confirmWorkOrderLv;
	
	/**巡检记录适配器*/
	private WorkOrderAdatper adapter;
	
	/**工单数据接收集合*/
	private List<WorkOrderEntity> list = new ArrayList<WorkOrderEntity>();
	
	/**工单适配数据集合*/
	private List<WorkOrderEntity> listAll = new ArrayList<WorkOrderEntity>();
	
	/** 页码 */
	private int pageIndex = 1;

	/** 加载标识 */
	private boolean loading = false;

	/** 更新数据 */
	private boolean updateFlag = true;

	/** 加载更多标示 */
	private boolean loadFlag = true;
	private boolean flag = false;

	/** 最小的行数 */
	private final int MIN_NUM = 9;

	/** 记录数据库总行数 */
	private int count = 0;
	
	/**本地保存*/
	private SharedPreferences spf = null;
	
	/**用户Id*/
	private int userId;
	
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pending_confirm_work_order);
		AppManager.getAppManager().addActivity(this);
		spf = getSharedPreferences("user",Context.MODE_PRIVATE);
		userId=spf.getInt("Id", 0);
		getView();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		mDialog = new ProgressDialog(WaitFixOrdersActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		confirmWorkOrderLv=(XListView)findViewById(R.id.confirmWorkOrderLv);
		confirmWorkOrderLv.setOnItemClickListener(this);
		adapter=new WorkOrderAdatper(WaitFixOrdersActivity.this, listAll);
		confirmWorkOrderLv.setAdapter(adapter);
		
		key = getRandomString(5);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);

		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
		
		getResult();
		
	}
	
	/**
	 * description 解析数据
	 * 
	 * @return void
	 */
	private void getResult() {

		if (!loading) {
			// tencent 123456
			String url = "http://211.155.229.136:8080/assetapi2/order/list?"
					+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
					+ "&userId="+userId+"&status=2"+"&page="+pageIndex
					+ "&accountType=1";
			System.out.println(url);

			HashMap<String, String> params = new HashMap<String, String>();

			JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
					new JSONObject(params), new Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							loading = true;
							System.out.println("@@" + response.toString());
							list=GetWorkOrderJson.getJson(response.toString());
							int result = GetWorkOrderJson.result;
							if (result == 1) {
								if (list != null && list.size() > 0) {
									// 适配数据
									setLvData(list);
								} else {
									myHandler.sendEmptyMessage(0X001);
								}
								//handler.sendEmptyMessage(1);
							} else if (result == -1) {
								handler.sendEmptyMessage(-1);
							} else if (result == 0) {
								handler.sendEmptyMessage(0);
							} else if (result == 103) {
								handler.sendEmptyMessage(103);
							}

						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {

							System.out.println("##" + error.toString());
							handler.sendEmptyMessage(0);

						}
					});
			DemoApplication.getInstance().getRequestQueue().add(req);
		}
		
	}

	/**
	 * description 消息处理
	 * 
	 * @return void
	 */
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				//Toast.makeText(PendingConfirmWorkOrdersActivity.this, "获取成功", 1000).show();
				break;
			case -1:
				Toast.makeText(WaitFixOrdersActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(WaitFixOrdersActivity.this, "获取失败", 1000).show();
				break;
			case 103:
				Toast.makeText(WaitFixOrdersActivity.this, "参数错误", 1000).show();
				break;
			}
			// 关闭ProgressDialog
			if (mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
			}
		}
	};

	/**
	 * description 随机生成5位字符串
	 * 
	 * @return void
	 */
	public String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * description 对密码进行加密
	 * 
	 * @param password2
	 * @return
	 */
	private String md5(String password) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString().toUpperCase();
	}

	/**
	 * 列表点击事件 
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent=new Intent(WaitFixOrdersActivity.this, FixActivity.class);
		intent.putExtra("id", listAll.get(arg2-1).getDetailId());
		System.out.println(listAll.get(arg2-1).getDetailId());
		position=arg2-1;
		System.out.println(arg2);
		startActivityForResult(intent, 100);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(20==resultCode){
			listAll.remove(position);
			adapter.notifyDataSetChanged();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * description 获取并加载数据
	 * 
	 * @param list
	 * 
	 * @return void
	 */
	private void setLvData(List<WorkOrderEntity> list) {
		// 刷新数据、适配数据
		if (updateFlag) {
			// 适配数据
			myHandler.sendEmptyMessage(0X000);
		} else {
			// 刷新数据
			myHandler.sendEmptyMessage(0X002);
		}
	}

	/**
	 * description 根据加载的数据 判断是否显示加载更多
	 * 
	 * @return void
	 */

	private void initSetPullLoad() {

		// 判断是否加载上拉加载更多
		count = GetWorkOrderJson.total;
		if (adapter.getCount() < MIN_NUM || adapter.getCount() == count) {

			confirmWorkOrderLv.setPullLoadEnable(false);

		} else {

			confirmWorkOrderLv.setPullLoadEnable(true);
		}
	}

	/**
	 * description 刷新和加载更多
	 * 
	 * @return void
	 */

	private void refreshAndLoad() {

		confirmWorkOrderLv.setPullRefreshEnable(true);
		confirmWorkOrderLv.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				updateData();
			}

			@Override
			public void onLoadMore() {
				if (NetworkCheck.check(WaitFixOrdersActivity.this) != null) {
					if (!flag) {

						flag = true;
						// 改变更新标识
						updateFlag = false;

						// 适配的总量跟数据库总量对比
						if (adapter.getCount() < count) {

							pageIndex++;

							getResult();

							confirmWorkOrderLv.stopLoadMore();
						}
					}
				} else {
					myHandler.sendEmptyMessage(0X320);// 没有网络操作
				}

			}
		});

	}

	/**
	 * description 更新数据
	 * 
	 * @return void
	 */
	private void updateData() {

		if (updateFlag) {
			updateFlag = false;
			loadFlag = false;
			pageIndex = 1;

			// 重新加载数据
			getResult();

		}
	}

	/**
	 * description 处理数据
	 * 
	 * @return void
	 */
	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			// 适配
			case 0X000:
				if (!loadFlag) {
					// 清空适配数据集
					listAll.clear();
				}
				listAll.addAll(list);

				adapter.notifyDataSetChanged();
				refreshAndLoad();
				// 判断是否加载更多
				initSetPullLoad();
				// 停止加载更多复位页脚视图
				confirmWorkOrderLv.stopLoadMore();
				// 停止刷新,复位标题视图
				confirmWorkOrderLv.stopRefresh();
				// 关闭ProgressDialog
				if (mDialog != null && mDialog.isShowing()) {
					mDialog.dismiss();
				}
				break;
			// 无数据
			case 0X001:
				listAll.clear();
				refreshAndLoad();
				Toast.makeText(WaitFixOrdersActivity.this,
						getString(R.string.no_data), Toast.LENGTH_SHORT).show();
				// 停止加载更多复位页脚视图
				confirmWorkOrderLv.stopLoadMore();
				// 停止刷新,复位标题视图
				confirmWorkOrderLv.stopRefresh();
				// 关闭ProgressDialog
				if (mDialog != null && mDialog.isShowing()) {
					mDialog.dismiss();
				}
				break;

			// 刷新
			case 0X002:
				if (!loadFlag) {
					// 清空适配数据集
					listAll.clear();
				}
				listAll.addAll(list);

				adapter.notifyDataSetChanged();
				// 判断是否加载更多
				initSetPullLoad();
				// 停止加载更多复位页脚视图
				confirmWorkOrderLv.stopLoadMore();
				// 停止刷新,复位标题视图
				confirmWorkOrderLv.stopRefresh();
				flag = false;
				// 关闭ProgressDialog
				if (mDialog != null && mDialog.isShowing()) {
					mDialog.dismiss();
				}
				break;
			// 无网络
			case 0X320:
				listAll.clear();
				Toast.makeText(WaitFixOrdersActivity.this,
						getString(R.string.noNetwork), Toast.LENGTH_SHORT)
						.show();
				refreshAndLoad();
				// 停止加载更多复位页脚视图
				confirmWorkOrderLv.stopLoadMore();
				// 停止刷新,复位标题视图
				confirmWorkOrderLv.stopRefresh();
				// 关闭ProgressDialog
				if (mDialog != null && mDialog.isShowing()) {
					mDialog.dismiss();
				}
				break;
			// 请求服务器超时
			case 0X006:
				refreshAndLoad();
				// 停止加载更多复位页脚视图
				confirmWorkOrderLv.stopLoadMore();
				// 停止刷新,复位标题视图
				confirmWorkOrderLv.stopRefresh();
				break;

			}

			updateFlag = true;
			loadFlag = true;
			loading = false;
		}

	};

	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;
		}
		
	}
}
