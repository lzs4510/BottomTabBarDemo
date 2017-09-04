package com.baway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baway.HttpUtils.OkHttpUtils;
import com.baway.NetDataCallback2;
import com.baway.R;
import com.baway.bean.Regist2;

/**
 * Created by 李宗书 on 2017/8/9.
 */

public class Regist2Activity extends AppCompatActivity implements NetDataCallback2<Regist2>{
    private EditText regist22;
    private Button next2;
    private OkHttpUtils http;
    private String url="http://api.eleteam.com/v1/user/register-step2?mobile=";
    private String phone;
    private String s2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist2);
        regist22=(EditText)findViewById(R.id.regist2password);
        next2=(Button)findViewById(R.id.next2);
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");

        //下一步的点击事件
        next2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            s2 =regist22.getText().toString();
            if (phone!=null&&s2!=null){
                http=new OkHttpUtils();
                http.getdata(url+ phone +"&&password="+s2,Regist2Activity.this,Regist2.class);

            }

        }
    });

    }

    @Override
    public void success(Regist2 regist2) {
        int i = regist2.getCode();
        if (i==1){
      Intent intent1=new Intent(Regist2Activity.this,Regist3Activity.class);
            intent1.putExtra("password",s2);
            intent1.putExtra("phone",phone);
            startActivity(intent1);

        }
    }

    @Override
    public void fail(int code, String str) {

    }
}
