package com.baway;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 李宗书 on 2017/8/18.
 */

public class MyView extends LinearLayout implements View.OnClickListener {
    private OnAmountChangeListener mListener;
    private int count;
    private TextView numText;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = View.inflate(context, R.layout.shoppingcartadd, this);
        initView(view);


    }
    public void setText(int num){
        numText.setText(""+num);
    }
    public int getText(){
        getTextString();
        return count;
    }

    private void initView(View view) {

        Button addBtn = (Button) view.findViewById(R.id.btn_add);
        Button jianBtn = (Button) view.findViewById(R.id.btn_jian);
        numText = (TextView) view.findViewById(R.id.txt_num);
        getTextString();
        addBtn.setOnClickListener(this);
        jianBtn.setOnClickListener(this);
    }

    public void setOnclickNum(OnAmountChangeListener mListener){
        this.mListener = mListener;
    }
    @Override
    public void onClick(View view) {
        getTextString();
        switch (view.getId()){
            case R.id.btn_add:
                count++;
                break;
            case R.id.btn_jian:
                if(count>0){
                    count--;
                }
                break;
        }
        numText.setText(""+count);
        if(mListener!=null) {
            mListener.onAmountChange(view, count);
        }
    }

    public void getTextString() {

        String numStr = numText.getText().toString().trim();
        count = Integer.parseInt(numStr);
    }
    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }
}
