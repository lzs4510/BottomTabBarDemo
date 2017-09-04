package com.baway.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baway.HttpUtils.HttpUtils;
import com.baway.HttpUtils.OkHttpUtils;
import com.baway.NetDataCallback;
import com.baway.NetDataCallback2;
import com.baway.R;
import com.baway.adapter.Fm2Vp2Adapter;
import com.baway.adapter.Fm2vp1adapter;
import com.baway.adapter.Pageradapter;
import com.baway.bean.Jingxuan;
import com.baway.bean.Youhui;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by 李宗书 on 2017/8/2.
 */

public class TwoFragment extends Fragment implements NetDataCallback,NetDataCallback2<Jingxuan>{
    private TabLayout tb;
    private ViewPager mvp;
    private Pageradapter pd;
    private RecyclerView mrv,mrv2;
    //天天优惠的适配器类
    private Fm2vp1adapter md;
    //为你精选的适配器类
    private Fm2Vp2Adapter f22ad;
    //天天优惠接口
    private String url="http://api.eleteam.com/v1/product/list-featured-price";
    //为你精选的接口
    private String url2="http://api.eleteam.com/v1/product/list-featured-topic";
    private HttpUtils http=new HttpUtils();
    private OkHttpUtils http2=new OkHttpUtils();
    private ArrayList<Youhui.DataBean.ProductsBean> mlist2=new ArrayList<>();
    private ArrayList<Jingxuan.DataBean.ProductsBean> mlist3=new ArrayList<>();
    private ArrayList<View> mlist = new ArrayList<>();
    private Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Gson gson=new Gson();
      if (msg.what==1){
          Youhui youhui = gson.fromJson(msg.obj.toString(), Youhui.class);
          mlist2= (ArrayList<Youhui.DataBean.ProductsBean>) youhui.getData().getProducts();
          md.setdata(mlist2);
          md.notifyDataSetChanged();
      }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);
        tb = (TabLayout)view.findViewById(R.id.tab);
        mvp = (ViewPager)view.findViewById(R.id.vp);

        tb.addTab(tb.newTab().setText("天天优惠"));
        tb.addTab(tb.newTab().setText("为你精选"));
        tb.addTab(tb.newTab().setText("亲的最爱"));

        LayoutInflater lf = LayoutInflater.from(getActivity());
        View view1 = lf.inflate(R.layout.fm2vp1, null);
        View view2 = lf.inflate(R.layout.fm2vp2, null);
        View view3 = lf.inflate(R.layout.fm2vp3, null);
       mlist.add(view1); mlist.add(view2); mlist.add(view3);
       pd=new Pageradapter(mlist);
       mvp.setAdapter(pd);
        //Viewpager的监听事件：
        mvp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb));

        //TableLayout的监听事件
        tb.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int i = tab.getPosition();
                mvp.setCurrentItem(i);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
         //天天优惠界面
        mrv=(RecyclerView)view1.findViewById(R.id.rv);
        //加载网络数据
        loaddata();
        loaddata2();
        //设置适配器
       md=new Fm2vp1adapter(getActivity(),mlist2);
        mrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrv.setAdapter(md);
        //RecyclerView的条目点击事件
        md.setOnItemClickListener(new Fm2vp1adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(getActivity(),mlist2.get(postion).getName(),Toast.LENGTH_SHORT).show();
            }
        });

       //为你精选界面
           mrv2= (RecyclerView) view2.findViewById(R.id.rv2);

         f22ad=new Fm2Vp2Adapter(getActivity(),mlist3);
        mrv2.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrv2.setAdapter(f22ad);
        f22ad.setOnItemClickListener(new Fm2Vp2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(getActivity(),mlist3.get(postion).getName(),Toast.LENGTH_SHORT).show();
            }
        });


        return view;

    }
    //加载为你精选接口
   private void loaddata2() {
       http2.getdata(url2,this,Jingxuan.class);
    }
   //加载天天优惠的接口
    private void loaddata() {
      //  http=new HttpUtils();
       http.getdata(url,this);
    }

    @Override
    public void err(int code, String str) {

    }
    //天天优惠的网络请求
    @Override
    public void callback(String st) {
        Message msg = Message.obtain();
        msg.what=1;
        msg.obj=st;
        hand.sendMessage(msg);

    }


    //为你精选的网络请求
    @Override
    public void success(Jingxuan jingxuan) {

       mlist3= (ArrayList<Jingxuan.DataBean.ProductsBean>) jingxuan.getData().getProducts();
        f22ad.setdata(mlist3);
        f22ad.notifyDataSetChanged();

    }

    @Override
    public void fail(int code, String str) {

    }
}
