package com.baway.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 李宗书 on 2017/8/2.
 */

public class Pageradapter extends PagerAdapter {
    private ArrayList<View> mlist;

    public Pageradapter(ArrayList<View> mlist) {
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mlist.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
     container.removeView(mlist.get(position));

    }
}
