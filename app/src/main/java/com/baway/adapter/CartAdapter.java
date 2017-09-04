package com.baway.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.MyView;
import com.baway.R;
import com.baway.bean.CartBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 李宗书 on 2017/8/18.
 */

public class CartAdapter extends BaseAdapter {
    private List<CartBean.DataBean.Category.Product> mlist;
    private Context context;
    private GaroupClick garoupClick;
    private int index;
    private CountClick countClick;

    public CartAdapter(Context context) {
        this.context = context;
    }

    public void setGaroupClick(GaroupClick garoupClick) {
        this.garoupClick = garoupClick;
    }

    public void setCountClick(CountClick countClick) {
        this.countClick = countClick;
    }

    //手写的方法
    public void setData(List<CartBean.DataBean.Category.Product> list){
        this.mlist=list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mlist.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
           convertView= View.inflate(context, R.layout.shoppingcartitem,null);
            holder.childCheck = (CheckBox) convertView.findViewById(R.id.cb_chid_checked);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.txt_price);
            holder.myView = (MyView) convertView.findViewById(R.id.myview);
            holder.name=(TextView)convertView.findViewById(R.id.name) ;
            holder.image=(ImageView)convertView.findViewById(R.id.cart_image);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        final CartBean.DataBean.Category.Product bean = mlist.get(position);
        holder.childCheck.setChecked(bean.isChoose());
        holder.txtPrice.setText("￥："+bean.getPrice());
        holder.name.setText(bean.getName());
        holder.myView.setText(bean.getCount());
        Glide.with(context).load(bean.image_small).into(holder.image);
        holder.myView.setOnclickNum(new MyView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                bean.setCount(amount);

            }
        });

        boolean checked = holder.childCheck.isChecked();
        if (checked){
            holder.myView.setOnclickNum(new MyView.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {

                    if (countClick!=null){
                        countClick.onNumClick(position,amount);
                    }
                }
            });
        }
        holder.childCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setChoose(((CheckBox)v).isChecked());
                if(garoupClick!=null){
                    garoupClick.onItemClick(position,((CheckBox)v).isChecked());

                }
            }
        });
        return convertView;
    }

    class ViewHolder{
        CheckBox childCheck;
        TextView txtPrice,name;
        ImageView image;
        MyView myView;
    }

    public interface GaroupClick{

        void onItemClick(int position,boolean isChoose);
    }
    public interface CountClick{
        void onNumClick(int position,int count);
    }
}
