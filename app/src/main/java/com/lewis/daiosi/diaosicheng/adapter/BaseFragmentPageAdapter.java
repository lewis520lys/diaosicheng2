package com.lewis.daiosi.diaosicheng.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lewis.daiosi.diaosicheng.base.BaseFragment;

import java.util.List;

public class BaseFragmentPageAdapter extends FragmentPagerAdapter {
   List<BaseFragment> datas;
   List<String> titles;

    public BaseFragmentPageAdapter(FragmentManager fm,List<BaseFragment> datas,List<String> titles) {
        super(fm);
        this.datas = datas;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles == null ? "" : titles.get(position);
    }
}