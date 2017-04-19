package com.lewis.daiosi.diaosicheng.fragment.homefragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lewis.daiosi.diaosicheng.R;
import com.lewis.daiosi.diaosicheng.base.BaseFragment;
import com.lewis.daiosi.diaosicheng.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19 0019.
 */

public class MovieFragment extends BaseFragment {
    private Banner banner;
    private RecyclerView recyclerView;
    List<String> imgList;
    List<String> movies;
    private CommonAdapter<String> adapter;

    @Override
    public int bindLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public void initView(View view) {
        banner= (Banner) view.findViewById(R.id.banner);
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
        imgList=new ArrayList<>();
        movies=new ArrayList<>();
    }

    @Override
    public void doBusiness(Context mContext) {
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic1xjab4j30ci08cjrv.jpg");
        imgList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        for (int i = 0; i < 10; i++) {
            movies.add("金刚狼"+i);
        }
        initBanner(imgList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new CommonAdapter<String>(getContext(), R.layout.item_fragment_movie, movies) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                holder.setText(R.id.movie_name,o);
            }
        };
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void widgetClick(View v) {

    }
    private void  initBanner(List<String> images){
       // banner.setBannerStyle(BannerConfig.DURATION);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        // banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
