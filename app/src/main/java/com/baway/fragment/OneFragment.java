package com.baway.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baway.HttpUtils.HttpUtils;
import com.baway.NetDataCallback;
import com.baway.R;
import com.baway.activity.XiangQingYe;
import com.baway.adapter.Myadapter;
import com.baway.adapter.Myadapter2;
import com.baway.bean.Tea;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李宗书 on 2017/8/2.
 */

public class OneFragment extends Fragment implements NetDataCallback{
    private HttpUtils http;
    //左侧的集合
    private List<Tea.DataBean.Category> mlist=new ArrayList<>();
    //右侧的集合
    private List<Tea.DataBean.Category.Product> mlist2=new ArrayList<>();
    //左侧的适配器
    private Myadapter md;
    //右侧的适配器
    private Myadapter2 md2;
    //左侧
    private ListView mlv;
    //右侧
    private ListView mlv2;
    private String url="http://api.eleteam.com/v1/category/list-with-product";
    private Handler hand=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Gson gson=new Gson();
            if (msg.what==1){

                Tea tea = gson.fromJson(msg.obj.toString(), Tea.class);
                Log.e("..........",msg.obj.toString());
                mlist.addAll(tea.data.categories);
                md.notifyDataSetChanged();

            }

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        mlv=(ListView)view.findViewById(R.id.list1);
        mlv2=(ListView)view.findViewById(R.id.list2);
        initdata();
        md=new Myadapter(getActivity(),mlist);
        mlv.setAdapter(md);
        //左侧listview条目的点击事件
        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //刷新第二层的适配器
              mlist2= mlist.get(position).products;
                md2=new Myadapter2(getActivity(),mlist2);
                mlv2.setAdapter(md2);
                //点击右侧item跳转到详情页
                mlv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //获得对应位置的属性信息传到详情页
                       String name2 = mlist2.get(position).name;
                        String price2 = mlist2.get(position).price;
                        String featured_price2 = mlist2.get(position).featured_price;
                        String short_description2 = mlist2.get(position).short_description;
                        String image1 = mlist2.get(position).app_long_image1;
                        String image2 = mlist2.get(position).app_long_image2;
                        String image3 = mlist2.get(position).app_long_image3;
                        String image4 = mlist2.get(position).app_long_image4;
                        String image5 = mlist2.get(position).app_long_image5;

                        Intent intent=new Intent(getActivity(), XiangQingYe.class);
                         intent.putExtra("name",name2);
                        intent.putExtra("price",price2);
                        intent.putExtra("short_description",short_description2);
                        intent.putExtra("featured_price",featured_price2);
                        intent.putExtra("image1",image1);
                        intent.putExtra("image2",image2);
                        intent.putExtra("image3",image3);
                        intent.putExtra("image4",image4);
                        intent.putExtra("image5",image5);
                        startActivity(intent);

                    }
                });
                
            }
        });

        return view;

    }

    private void initdata() {
        http=new HttpUtils();
        http.getdata(url,this);

    }

    @Override
    public void err(int code, String str) {

    }

    @Override
    public void callback(String st) {
        Message msg = Message.obtain();
        msg.what=1;
        msg.obj=st;
        Log.e("//////////",st);
        hand.sendMessage(msg);

    }
}
