package com.baway.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.baway.HttpUtils.OkHttpUtils;
import com.baway.NetDataCallback2;
import com.baway.R;
import com.baway.adapter.CartAdapter;
import com.baway.bean.CartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李宗书 on 2017/8/2.
 */

public class ThreeFragment extends Fragment implements NetDataCallback2<CartBean>,CartAdapter.CountClick,CartAdapter.GaroupClick{
    private Button jiesuanBtn;
    private ListView mListView;
    private CheckBox allCheck;
    private TextView priceText;
    private List<CartBean.DataBean.Category.Product> mlist = new ArrayList<>();
    private int numPrice = 0;
    private int count = 0;
    private CartAdapter adapter;
    private String url="http://api.eleteam.com/v1/category/list-with-product";
    private OkHttpUtils okhttp;
    private CartAdapter md;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        jiesuanBtn = (Button)view.findViewById(R.id.btn_num);
        mListView = (ListView) view.findViewById(R.id.listview);
        allCheck = (CheckBox) view.findViewById(R.id.cb_all_checked);
        priceText = (TextView)view.findViewById(R.id.txt_price_sum);

        loaddata();
        initview();
        adapter = new CartAdapter(getActivity());
        adapter.setData(mlist);
        mListView.setAdapter(adapter);
        adapter.setCountClick(this);
        adapter.setGaroupClick(this);


        return view;

    }

    private void initview() {
        allCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlist.size()!=0) {
                    if(allCheck.isChecked()) {
                        for (CartBean.DataBean.Category.Product qq:mlist){
                          qq.setChoose(true);
                            adapter.notifyDataSetChanged();
                        }

                    }else{
                        for (CartBean.DataBean.Category.Product qq:mlist){
                            qq.setChoose(false);
                            adapter.notifyDataSetChanged();
                        }

                    }
                    upDate();
                }

            }
        });

    }

    private void upDate() {
      numPrice = 0;
       count = 0;
        for (CartBean.DataBean.Category.Product qq:mlist){
            if (qq.isChoose()){
                count++;
                numPrice +=  qq.getPrice()*qq.getCount();

            }
        }
        jiesuanBtn.setText("结算（"+count+"）");
        priceText.setText("合计:￥"+numPrice);

    }

    private void loaddata() {
        okhttp = new OkHttpUtils();
        okhttp.getdata(url,this,CartBean.class);

    }
    public boolean isAllChecked(){
        for (CartBean.DataBean.Category.Product qq:mlist){
            //遍历集合，有一个不选return false
            if (!qq.isChoose()){
                return false;
            }
        }
        return true;
    }


    @Override
    public void success(CartBean cartBean) {

        mlist.addAll(cartBean.data.categories.get(0).products);
        for (CartBean.DataBean.Category.Product qq:mlist){
          qq.setCount(1);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fail(int code, String str) {

    }


    @Override
    public void onNumClick(int position, int count) {
        mlist.get(position).setCount(count);
        adapter.notifyDataSetChanged();
        upDate();

    }

    @Override
    public void onItemClick(int position, boolean isChoose) {
        mlist.get(position).setChoose(isChoose);
        if(isAllChecked()){
            allCheck.setChecked(true);
        }else{
            allCheck.setChecked(false);
        }
        adapter.notifyDataSetChanged();
        upDate();

    }
}
