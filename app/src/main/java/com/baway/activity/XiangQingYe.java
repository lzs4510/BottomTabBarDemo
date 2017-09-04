package com.baway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baway.BannerImageLoad;
import com.baway.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 * Created by 李宗书 on 2017/8/11.
 */

public class XiangQingYe extends AppCompatActivity {
    private TextView teaname,price1,price2,introduction;
    //private ImageView ima;
    private Banner ban;
    //private String url="http://api.eleteam.com/v1/product/view?id=3";
    //private OkHttpUtils http;
    private ArrayList<String> images=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiangqingye);
        initview();
        loaddata();

    }
    private void initview() {
        teaname=(TextView)findViewById(R.id.teaname);
        price1=(TextView)findViewById(R.id.price1);
        price2=(TextView)findViewById(R.id.price2);
        introduction=(TextView)findViewById(R.id.introduction);
      //  ima=(ImageView)findViewById(R.id.image);
        ban=(Banner)findViewById(R.id.banner);

        //设置图片加载器
        BannerImageLoad bil=new BannerImageLoad();
        ban.setImageLoader(bil);
        //设置显示样式(显示数字指示器和标题)
        ban.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

    }

    private void loaddata() {
     /*   http=new OkHttpUtils();
        http.getdata(url,this,Food.class);*/

        Intent  intent=getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String featured_price = intent.getStringExtra("featured_price");
        String short_description = intent.getStringExtra("short_description");
        String image11 = intent.getStringExtra("image1");
        String image22 = intent.getStringExtra("image2");
        String image33 = intent.getStringExtra("image3");
        String image44 = intent.getStringExtra("image4");
        String image55 = intent.getStringExtra("image5");


        //赋值
        teaname.setText(name);
        price1.setText("优惠价：￥"+featured_price);
        price2.setText("原价：￥"+price);
        introduction.setText(short_description);
        // Glide.with(this).load(food.getData().getProduct().getImage_small()).into(ima);
        String image1 = image11;
        String image2 = image22;
        String image3 = image33;
        String image4 = image44;
        String image5 = image55;
        images.add(image1);images.add(image2);images.add(image3);
        images.add(image4);images.add(image5);
        ban.setImages(images);
        ban.start();




    }




}
