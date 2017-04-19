package com.lewis.daiosi.diaosicheng;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.lewis.daiosi.diaosicheng.base.BaseActivity;
import com.lewis.daiosi.diaosicheng.base.BaseFragment;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView recyclerView;
    private List<TabBean> tabList;

    private List<BaseFragment> fragments;
    private List<String> titles;
    private CommonAdapter<TabBean> adapter;


    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        findViews(); //获取控件
        tabList = new ArrayList<>();
        fragments=new ArrayList<>();
        titles=new ArrayList<>();
        initData();
        setStatusBar(this, getResources().getColor(R.color.colorAccent));
        toolbar.setTitle("宅男城");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<TabBean>(this, R.layout.item_tab, tabList) {
            @Override
            public void convert(ViewHolder holder, TabBean s, int p) {
                holder.setText(R.id.tab, s.tabs);
                ImageView img = holder.getView(R.id.tab_iv);
                img.setImageResource(s.tab_img);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.tb_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        recyclerView = (RecyclerView) findViewById(R.id.lv_left_menu);

    }
    private void initData(){
        tabList.add(new TabBean("首页", R.drawable.ic_home_page));
        tabList.add(new TabBean("会员", R.drawable.ic_member));
        tabList.add(new TabBean("活动", R.drawable.ic_activity));
        tabList.add(new TabBean("钱包", R.drawable.ic_money_wallet));
        tabList.add(new TabBean("设置", R.drawable.ic_settings));


    }
    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }



    private class TabBean {
        public String tabs;
        public int tab_img;

        public TabBean(String tabs, int tab_img) {
            this.tabs = tabs;
            this.tab_img = tab_img;
        }
    }
}
