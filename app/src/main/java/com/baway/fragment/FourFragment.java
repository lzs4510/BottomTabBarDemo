package com.baway.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.R;
import com.baway.activity.LoginActivity;
import com.xys.libzxing.zxing.activity.CaptureActivity;

/**
 * Created by 李宗书 on 2017/8/2.
 */

public class FourFragment extends Fragment {

    private TextView login1,saoyisao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);

        login1=(TextView)view.findViewById(R.id.login1);
        saoyisao=(TextView)view.findViewById(R.id.saoyisao) ;
        //扫一扫的点击事件
        saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CaptureActivity.class );
                startActivity(intent);

            }
        });
        //登陆的点击事件
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });
        return view;

    }


}
