package com.misu.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.misu.core.AppManager;
import com.misu.utils.ScreenUtils;
import com.misu.utils.ToastUtil;


/**
 * Activity基类
 * 
 * @author jack_jiang
 * 
 */
public abstract class BaseActivity extends FragmentActivity {
	protected Context context;
	protected int mScreenWidth;
	protected int mScreenHeight;

	// 应用是否销毁标志
	protected boolean isDestroy;

	// 防止重复点击设置的标志，涉及到点击打开其他Activity时，将该标志设置为false，在onResume事件中设置为true
	private boolean clickable = true;

	protected boolean savedIstance=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		create(savedInstanceState);
		if(!savedIstance){
			context = this;
			mScreenWidth = ScreenUtils.getScreenWidth(context);
			mScreenHeight = ScreenUtils.getScreenHeight(context);
			init();
			// 添加Activity到堆栈
			AppManager.getAppManager().addActivity(this);
		}
	}

	@Override
	protected void onDestroy() {
		destory();
		isDestroy = true;
		// 结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
		super.onDestroy();
	}
	
	protected void create(Bundle savedInstanceState){
		
	}

	/**
	 * 初始化
	 */
	protected void init() {
		setContentView();
		findViews();
		getData();
		showContent();
	}

	/**
	 * destory
	 */
	protected void destory() {

	}

	/**
	 * 加载布局
	 */
	protected abstract void setContentView();

	/**
	 * 初始化控件
	 */
	protected void findViews() {

	}

	/**
	 * 初始化数据
	 */
	protected abstract void getData();

	/**
	 * 处理数据
	 */
	protected abstract void showContent();

	@Override
	protected void onResume() {
		super.onResume();
		// 每次返回界面时，将点击标志设置为可点击
		clickable = true;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	/**
	 * 当前是否可以点击
	 * 
	 * @return
	 */
	protected boolean isClickable() {
		return clickable;
	}

	/**
	 * 锁定点击
	 */
	protected void lockClick() {
		clickable = false;
	}

	/**
	 * 加载图片
	 * 
	 * @param imageView
	 * @param url
	 */
	protected void loadImage(ImageView imageView, String url) {
		if (!TextUtils.isEmpty(url)) {
//			Picasso.with(context.getApplicationContext()).load(url)
//					.config(Bitmap.Config.RGB_565).into(imageView);
		}
	}

	/**
	 * 提示信息
	 * 
	 * @param message
	 */
	protected void show(String message) {
		ToastUtil.show(context, message);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (isClickable()) {
			lockClick();
			super.onActivityResult(requestCode, resultCode, data);
		}
	}


}
