package com.baway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baway.R;
import com.baway.bean.Tea;

import java.util.List;

/**
 * 最左侧列表的适配器
 * Created by 李宗书 on 2017/8/2.
 */

public class Myadapter extends BaseAdapter {
    private List<Tea.DataBean.Category> mlist;
    private Context mcontext;
    private LayoutInflater mlf;

    public Myadapter(Context mcontext, List<Tea.DataBean.Category> list) {

        this.mcontext = mcontext;
        this.mlist = list;
    }

    @Override
    public int getCount() {

        return mlist == null ? 0 : mlist.size();
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
        mlf = LayoutInflater.from(mcontext);
        if (convertView == null) {
            convertView = mlf.inflate(R.layout.fm1item, null);
            vh = new ViewHolder();

            vh.typename = (TextView) convertView.findViewById(R.id.type);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Tea.DataBean.Category bean = mlist.get(position);
        vh.typename.setText(bean.name);
        return convertView;
    }

    class ViewHolder {
        TextView typename;
    }
}
