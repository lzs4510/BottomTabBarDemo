package com.baway.bean;

import java.util.List;

/**
 * Created by 李宗书 on 2017/8/2.
 */

public class Tea {
    public boolean status;
    public int code;
    public String message;
    public DataBean data;
    public ShareBean share;

    public static class DataBean {
        public List<Category> categories;

        public static class Category {
            public int id;
            public String featured;
            public String image;
            public String featured_image;
            public String image_small;
            public String name;
            private String short_description;
            public List<Product> products;


            public static class Product{
                public String name;
                public String image_small;
                public String price;
                public String featured_price;
                public String app_long_image1;
                public String app_long_image2;
                public String app_long_image3;
                public String app_long_image4;
                public String app_long_image5;
                public String short_description;
            }
        }

    }

    public static class ShareBean {
    }
}
