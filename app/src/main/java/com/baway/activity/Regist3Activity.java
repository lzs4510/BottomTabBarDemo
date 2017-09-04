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
import com.baway.bean.Regist3;

import static com.baway.R.layout.regist3;

/**
 * Created by 李宗书 on 2017/8/9.
 */

public class Regist3Activity extends AppCompatActivity implements NetDataCallback2<Regist3>{
    private EditText yanzhengma;
    private Button finish;
    private OkHttpUtils http;
    private String url="http://api.eleteam.com/v1/user/register-step3?mobile=";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(regist3);
       yanzhengma=(EditText) findViewById(R.id.regist3yanzhengma);
        finish=(Button)findViewById(R.id.next3);

        Intent intent3 = getIntent();
        final String phone = intent3.getStringExtra("phone");
        final String password = intent3.getStringExtra("password");

        finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              //  Toast.makeText(Regist3Activity.this,"注册完成..........",Toast.LENGTH_LONG).show();
                if (yanzhengma.getText().toString()!=null){
                    http=new OkHttpUtils();
                    http.getdata(url+phone+"&&password="+password+"&&code="+
                            yanzhengma.getText().toString(),Regist3Activity.this,Regist3.class);

                }

            }
        });

    }

    @Override
    public void success(Regist3 regist3) {
        int i = regist3.getCode();
        if (i==1){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Regist3Activity.this,"注册完成",Toast.LENGTH_LONG).show();
                    //跳转到登录界面
                    Intent intent4=new Intent(Regist3Activity.this,LoginActivity.class);
                    startActivity(intent4);
                }
            });
          /*  Toast.makeText(Regist3Activity.this,"注册完成",Toast.LENGTH_LONG).show();
            Intent intent4=new Intent(Regist3Activity.this,LoginActivity.class);
            startActivity(intent4);*/

        }


    }

    @Override
    public void fail(int code, String str) {

    }
}
