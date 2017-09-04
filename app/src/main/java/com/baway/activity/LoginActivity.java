package com.baway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.HttpUtils.HttpUtils;
import com.baway.MainActivity;
import com.baway.NetDataCallback;
import com.baway.R;

/**
 * Created by 李宗书 on 2017/8/9.
 */

public class LoginActivity extends AppCompatActivity implements NetDataCallback{
    private EditText phone,password;
    private Button login2;
    private TextView regist;
    private HttpUtils http;
    private String url="http://api.eleteam.com/v1/user/login?mobile=";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
       initview();
       initdata();
    }

    private void initdata() {
        //点击注册的话跳转到注册界面
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, Regist1Activity.class);
                startActivity(intent);
            }
        });



        //点击登录监听事件
        login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.getText().toString()!=null&&password.getText().toString()!=null){
                           http=new HttpUtils();
                           http.getdata(url+phone.getText().toString()+"&&password="+password.getText().toString(),LoginActivity.this);

                }

            }
        });


    }

    private void initview() {
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
       login2=(Button) findViewById(R.id.login2);
        regist=(TextView) findViewById(R.id.regist);

    }

    @Override
    public void err(int code, String str) {


    }
//成功的情况
    @Override
    public void callback(String st) {
        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
