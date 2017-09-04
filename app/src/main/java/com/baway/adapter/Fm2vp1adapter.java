package com.baway.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.R;
import com.baway.bean.Youhui;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by 李宗书 on 2017/8/2.
 */

public class Fm2vp1adapter extends RecyclerView.Adapter{
            private View view;
            private Context mcontext;
            private ArrayList<Youhui.DataBean.ProductsBean> mlist=new ArrayList<>();
            private OnItemClickListener mClickListener;

            public Fm2vp1adapter(Context mcontext,ArrayList<Youhui.DataBean.ProductsBean> list)

                              {
                this.mcontext = mcontext;
                this.mlist=list;
            }

            //找到需要导入的布局
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                view = LayoutInflater.from(mcontext).inflate(R.layout.fm2vp1item, null);
                return new ViewHolder(view,mClickListener);

            }
            //加载相应的内容
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ViewHolder viewholder=(ViewHolder)holder;
                viewholder.name.setText(""+getItem(position).getName());
                viewholder.jieshao.setText(""+getItem(position).getShort_description());
                viewholder.price.setText("￥："+getItem(position).getPrice());
                Glide.with(mcontext).load(getItem(position).getImage_small()).into(viewholder.ima);
            }

            //手写的重置数据的方法
            public void setdata(ArrayList<Youhui.DataBean.ProductsBean> list){
                if (list!=null&&list.size()>0){
                    mlist.addAll(list);
                }

            }
            //手写的获得视图的方法
            public Youhui.DataBean.ProductsBean getItem(int position){
                return mlist.get(position);

            };

            @Override
            public int getItemCount() {
                return mlist.size();
            }


            //定义的ViewHolder类继承RecyclerView.ViewHolder 并找到布局中的ID
            public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
                private OnItemClickListener mListener;// 声明自定义的接口
                private TextView name,jieshao,price;
                private ImageView ima;
                public ViewHolder(View itemView,OnItemClickListener  listener) {
                    super(itemView);
                    mListener=listener;
                    //给item设置点击事件
                    itemView.setOnClickListener(this);

                    name=(TextView)itemView.findViewById(R.id.fm2vp1_name);
                    jieshao=(TextView)itemView.findViewById(R.id.fm2vp1_jieshao);
                    price=(TextView)itemView.findViewById(R.id.fm2vp1_price);
                    ima=(ImageView)itemView.findViewById(R.id.fm2vp1_image);
                }

                @Override
                public void onClick(View v) {
                    // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
                    mListener.onItemClick(v,getPosition());

                }
            }
            //手写的一个接口
            public interface OnItemClickListener {

                public void onItemClick(View view, int postion);
            }
            //自定义的方法
            public void setOnItemClickListener(OnItemClickListener listener){
                this.mClickListener=listener;
            }
}
