package com.baway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baway.HttpUtils.OkHttpUtils;
import com.baway.NetDataCallback2;
import com.baway.R;
import com.baway.bean.Regist1;

/**
 * Created by 李宗书 on 2017/8/9.
 */

public class Regist1Activity extends AppCompatActivity implements NetDataCallback2<Regist1>{
    private EditText registphone;
    private Button next1;
    private String url=" http://api.eleteam.com/v1/user/register-step1?mobile=";
    private OkHttpUtils http;
    private  String s;
    private int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        registphone=(EditText)findViewById(R.id.registphone);
        next1=(Button)findViewById(R.id.next1);
        s = registphone.getText().toString();
        loaddata();
    }
    private void loaddata() {
        //填写完手机号后的点击事件
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(Regist1Activity.this,"哈哈哈",Toast.LENGTH_SHORT).show();
                s = registphone.getText().toString();
                if(s!=null){
                    http=new OkHttpUtils();
                    http.getdata(url+s,Regist1Activity.this,Regist1.class);
                }
            }
        });
        }
    //成功的方法
    @Override
    public void success(Regist1 regist1) {
             i = regist1.getCode();
                if (i==1){
                    Intent intent=new Intent(Regist1Activity.this,Regist2Activity.class);
                    intent.putExtra("phone",s);
                    Toast.makeText(Regist1Activity.this,"跳转到下个界面",Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }
    }
    @Override
    public void fail(int code, String str) {

    }
}
