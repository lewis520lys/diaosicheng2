package com.lewis.daiosi.diaosicheng;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.lewis.daiosi.diaosicheng.base.BaseActivity;
import com.lewis.daiosi.diaosicheng.base.BaseFragment;
import com.lewis.daiosi.diaosicheng.fragment.ActivityFragment;
import com.lewis.daiosi.diaosicheng.fragment.HomeFragment;
import com.lewis.daiosi.diaosicheng.fragment.MemberFragment;
import com.lewis.daiosi.diaosicheng.fragment.MoneyFragment;
import com.lewis.daiosi.diaosicheng.fragment.SettingFragment;
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
    private CommonAdapter<TabBean> adapter;
    private BaseFragment homeFragment, memberFragment,activityFragment,moneyFragment,settingFragment;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private int selindex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();

        if (savedInstanceState != null) {
            //读取上一次界面Save的时候tab选中的状态
            selindex = savedInstanceState.getInt(PRV_SELINDEX, selindex);
            homeFragment = (HomeFragment) fm.findFragmentByTag(FRAGMENT_TAG[0]);
            memberFragment = (MemberFragment) fm.findFragmentByTag(FRAGMENT_TAG[1]);
            activityFragment = (ActivityFragment) fm.findFragmentByTag(FRAGMENT_TAG[2]);
            moneyFragment = (MoneyFragment) fm.findFragmentByTag(FRAGMENT_TAG[3]);
            settingFragment = (SettingFragment) fm.findFragmentByTag(FRAGMENT_TAG[4]);
        }
        showFragment(selindex);
    }

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
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
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
               showFragment(position);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存tab选中的状态
        outState.putInt(PRV_SELINDEX, selindex);
        super.onSaveInstanceState(outState);
    }

    private static final String PRV_SELINDEX = "PREV_SELINDEX";
    private String[] FRAGMENT_TAG = new String[]{"home", "member", "activity", "money", "setting"};
    private void showFragment(int index) {
        // ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
        transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment != null)
                    transaction.show(homeFragment);
                else {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.continer, homeFragment, FRAGMENT_TAG[index]);

                }
                break;
            case 1:
                if (memberFragment != null) {
                    transaction.show(memberFragment);
                } else {
                    memberFragment = new MemberFragment();
                    transaction.add(R.id.continer, memberFragment, FRAGMENT_TAG[index]);
                }
                break;
            case 2:
                if (activityFragment!= null) {
                    transaction.show(activityFragment);
                } else {
                    activityFragment = new ActivityFragment();
                    transaction.add(R.id.continer, activityFragment, FRAGMENT_TAG[index]);
                }
                break;
            case 3:

                if (moneyFragment != null) {
                    transaction.show(moneyFragment);
                } else {
                    moneyFragment = new MoneyFragment();
                    transaction.add(R.id.continer, moneyFragment, FRAGMENT_TAG[index]);
                }
                break;
            case 4:

                if (settingFragment != null) {
                    transaction.show(settingFragment);
                } else {
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.continer, settingFragment, FRAGMENT_TAG[index]);
                }
                break;
        }

        selindex = index;
        transaction.commit();
    }
    private void hideFragment(FragmentTransaction ft) {
        if (homeFragment != null)
            ft.hide(homeFragment);
        if (memberFragment != null)
            ft.hide(memberFragment);
        if (activityFragment != null)
            ft.hide(activityFragment);
        if (moneyFragment != null)
            ft.hide(moneyFragment);
        if (settingFragment != null)
            ft.hide(settingFragment);
    }
    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.tb_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        recyclerView = (RecyclerView) findViewById(R.id.lv_left_menu);

    }
    private void initData(){
        tabList.add(new TabBean("首页", R.drawable.ic_home_icon));
        tabList.add(new TabBean("会员", R.drawable.ic_member_icon));
        tabList.add(new TabBean("活动", R.drawable.ic_activity_icon));
        tabList.add(new TabBean("钱包", R.drawable.ic_money_icon));
        tabList.add(new TabBean("设置", R.drawable.ic_setting_icon));


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
