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

import com.lewis.daiosi.diaosicheng.base.BaseActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView recyclerView;
    private List<String> tabList ;


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
        tabList=new ArrayList<>();
        tabList.add("主页");
        tabList.add("会员");
        tabList.add("活动");
        tabList.add("钱包");
        tabList.add("设置");
        setStatusBar(this,getResources().getColor(R.color.colorAccent));
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
        recyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.item_tab, tabList)
        {
            @Override
            public void convert(ViewHolder holder, String s,int p)
            {
                holder.setText(R.id.tab, s);
            }
        });


    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.tb_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        recyclerView = (RecyclerView) findViewById(R.id.lv_left_menu);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }
}
