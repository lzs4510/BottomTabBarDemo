package com.baway;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baway.fragment.FourFragment;
import com.baway.fragment.OneFragment;
import com.baway.fragment.ThreeFragment;
import com.baway.fragment.TwoFragment;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {
private BottomTabBar mb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      mb=(BottomTabBar)findViewById(R.id.bottom_tab_bar);

        mb.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(8)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem("月光茶人",R.drawable.tab_home, OneFragment.class)
                .addTabItem("优惠",R.drawable.tab_topic, TwoFragment.class)
                .addTabItem("购物车",R.drawable.main_index_cart_normal, ThreeFragment.class)
                .addTabItem("我的",R.drawable.main_index_my_normal, FourFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }
}
