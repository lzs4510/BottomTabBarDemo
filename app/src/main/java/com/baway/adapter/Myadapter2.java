package com.baway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.R;
import com.baway.bean.Tea;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 右侧列表的适配器
 * Created by 李宗书 on 2017/8/2.
 */

public class Myadapter2 extends BaseAdapter {
     private List<Tea.DataBean.Category.Product> mlist;
           private Context mcontext;
           private LayoutInflater mlf;

      public Myadapter2(Context mcontext,List<Tea.DataBean.Category.Product> list) {

          this.mcontext = mcontext;
          this.mlist=list;
      }
             @Override
             public int getCount() {

                 return mlist==null?0:mlist.size();
             }

             @Override
             public Object getItem(int position) {

                 return mlist.get(position);
             }

             @Override
             public long getItemId(int position) {

                 return position;
             }

             @Override
             public View getView(int position, View convertView, ViewGroup parent) {
                 ViewHolder vh;
               mlf=  LayoutInflater.from(mcontext);
              if (convertView==null){
                 convertView= mlf.inflate(R.layout.fm11item,null);
                  vh=new ViewHolder();
                vh.iv=(ImageView)convertView.findViewById(R.id.fm11_image);
                vh.teaname=(TextView) convertView.findViewById(R.id.fm11_teaname);
                vh.teaprice=(TextView)  convertView.findViewById(R.id.fm11_price);
                  vh.teaprice2=(TextView)  convertView.findViewById(R.id.fm11_price2);
                  convertView.setTag(vh);
              }else{
              vh=(ViewHolder)convertView.getTag();
              }
                 Tea.DataBean.Category.Product product = mlist.get(position);
                 vh.teaname.setText(product.name);
                 vh.teaprice.setText("￥"+product.featured_price);
                 vh.teaprice2.setText("￥"+product.price);
                 Glide.with(mcontext).load(product.image_small).into(vh.iv);

                 return convertView;
             }

             class ViewHolder{
                 ImageView iv;
                 TextView teaname;
                 TextView teaprice,teaprice2;
             }
}
