package com.lewis.daiosi.diaosicheng.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.jaeger.library.StatusBarUtil;


//作者：知乎用户
//		链接：https://www.zhihu.com/question/47045239/answer/105086885
//		来源：知乎
//		著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

public abstract class BaseActivity extends ActionBarActivity implements
		View.OnClickListener {
	/** 是否沉浸状态栏 **/
	private boolean isSetStatusBar = true;
	/** 是否允许全屏 **/
	private boolean mAllowFullScreen = false;
	/** 是否禁止旋转屏幕 **/
	private boolean isAllowScreenRoate = true;
	/** 当前Activity渲染的视图View **/
	private View mContextView = null;
	/** 是否输出日志信息 **/
	private boolean isDebug;
	private String APP_NAME;
	protected final String TAG = this.getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		isDebug = MApplication.isDebug;
//		APP_NAME = MApplication.APP_NAME;
		$Log(TAG + "-->onCreate()");
		try {
			Bundle bundle = getIntent().getExtras();
			initParms(bundle);
			mContextView = LayoutInflater.from(this)
					.inflate(bindLayout(), null);

			setContentView(mContextView);
			if (!isAllowScreenRoate) {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			} else {
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}
			initView(mContextView);
			doBusiness(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setStatusBar(Activity act,int color) {
		StatusBarUtil.setColor(act,color);
	}

	/**
	 * [沉浸状态栏]



	/**
	 * [初始化Bundle参数]
	 * 
	 * @param parms
	 */
	public abstract void initParms(Bundle parms);

	/**
	 * [绑定布局]
	 * 
	 * @return
	 */
	public abstract int bindLayout();

	/**
	 * [重写： 1.是否沉浸状态栏 2.是否全屏 3.是否禁止旋转屏幕]
	 */
	// public abstract void setActivityPre();

	/**
	 * [初始化控件]
	 * 
	 * @param view
	 */
	public abstract void initView(final View view);

	/**
	 * [业务操作]
	 * 
	 * @param mContext
	 */
	public abstract void doBusiness(Context mContext);

	/** View点击 **/
	public abstract void widgetClick(View v);

	@Override
	public void onClick(View v) {
		if (fastClick())
			widgetClick(v);
	}

	/**
	 * [页面跳转]
	 * 
	 * @param clz
	 */
	public void startActivity(Class<?> clz) {
		startActivity(clz, null);
	}

	/**
	 * [携带数据的页面跳转]
	 * 
	 * @param clz
	 * @param bundle
	 */
	public void startActivity(Class<?> clz, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, clz);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	@SuppressWarnings("unchecked")
	public <T extends View> T $(int resId) {
		return (T) super.findViewById(resId);
	}

	/**
	 * [含有Bundle通过Class打开编辑界面]
	 * 
	 * @param cls
	 * @param bundle
	 * @param requestCode
	 */
	public void startActivityForResult(Class<?> cls, Bundle bundle,
			int requestCode) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode);
	}

	@Override
	protected void onResume() {
		super.onResume();
		$Log(TAG + "--->onResume()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		$Log(TAG + "--->onDestroy()");
	}

	/**
	 * [是否允许全屏]
	 * 
	 * @param allowFullScreen
	 */
	public void setAllowFullScreen(boolean allowFullScreen) {
		this.mAllowFullScreen = allowFullScreen;
	}

	/**
	 * [是否设置沉浸状态栏]
	 * 
	 * @param
	 */
//	//public void setSteepStatusBar(boolean isSetStatusBar) {
//		this.isSetStatusBar = isSetStatusBar;
//	}

	/**
	 * [是否允许屏幕旋转]
	 * 
	 * @param isAllowScreenRoate
	 */
	public void setScreenRoate(boolean isAllowScreenRoate) {
		this.isAllowScreenRoate = isAllowScreenRoate;
	}

	/**
	 * [日志输出]
	 * 
	 * @param msg
	 */
	protected void $Log(String msg) {
		if (isDebug) {
			Log.d(APP_NAME, msg);
		}
	}

	/**
	 * [防止快速点击]
	 * 
	 * @return
	 */
	private boolean fastClick() {
		long lastClick = 0;
		if (System.currentTimeMillis() - lastClick <= 1000) {
			return false;
		}
		lastClick = System.currentTimeMillis();
		return true;
	}
}