package com.lewis.daiosi.diaosicheng.fragment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidkun.xtablayout.XTabLayout;
import com.lewis.daiosi.diaosicheng.R;
import com.lewis.daiosi.diaosicheng.adapter.BaseFragmentPageAdapter;
import com.lewis.daiosi.diaosicheng.base.BaseFragment;
import com.lewis.daiosi.diaosicheng.fragment.homefragment.ArticleFragment;
import com.lewis.daiosi.diaosicheng.fragment.homefragment.MovieFragment;
import com.lewis.daiosi.diaosicheng.fragment.homefragment.PictureFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class HomeFragment extends BaseFragment {
    private XTabLayout xTablayout;
    private ViewPager vp;
    private List<BaseFragment> fragments;
    private List<String> titles;

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        xTablayout = (XTabLayout) view.findViewById(R.id.xTablayout);
        vp = (ViewPager) view.findViewById(R.id.vp);
        initData();
    }

    private void initData() {
        fragments=new ArrayList<>();
        titles=new ArrayList<>();
        fragments.add(new MovieFragment());
        fragments.add(new PictureFragment());
        fragments.add(new ArticleFragment());
        fragments.add(new MovieFragment());
        titles.add("视频");
        titles.add("美图");
        titles.add("文章");
        titles.add("美食");
    }

    @Override
    public void doBusiness(Context mContext) {
        vp.setAdapter(new BaseFragmentPageAdapter(getChildFragmentManager(),fragments,titles));
        xTablayout.setupWithViewPager(vp);
    }

    @Override
    public void widgetClick(View v) {

    }
}
