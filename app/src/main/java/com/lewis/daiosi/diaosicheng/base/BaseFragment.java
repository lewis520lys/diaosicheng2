package com.lewis.daiosi.diaosicheng.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//作者：知乎用户
//		链接：https://www.zhihu.com/question/47045239/answer/105086885
//		来源：知乎
//		著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private boolean isDebug;
	private String APP_NAME;
	protected final String TAG = this.getClass().getSimpleName();
	private View mContextView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		isDebug = MApplication.isDebug;
//		APP_NAME = MApplication.APP_NAME;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		mContextView = inflater.inflate(bindLayout(), container, false);
		initView(mContextView);
		doBusiness(getActivity());
		return mContextView;
	}

	/**
	 * [绑定布局]
	 * 
	 * @return
	 */
	public abstract int bindLayout();

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

	@SuppressWarnings("unchecked")
	public <T extends View> T $(View view, int resId) {
		return (T) view.findViewById(resId);
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