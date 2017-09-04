package com.baway.bean;

import java.util.List;

/**
 * Created by 李宗书 on 2017/8/18.
 */

public class CartBean {
    public boolean status;
    public int code;
    public String message;
    public CartBean.DataBean data;
    public CartBean.ShareBean share;

    public static class DataBean {
        public List<CartBean.DataBean.Category> categories;

        public static class Category {
            public int id;
            public String featured;
            public String image;
            public String featured_image;
            public String image_small;
            public String name;
            private String short_description;
            public List<CartBean.DataBean.Category.Product> products;


            public static class Product{
                public String name;
                public int price;
                private int count;
                public String image_small;
                private boolean choose;
                public String featured_price;
                public String app_long_image1;
                public String app_long_image2;
                public String app_long_image3;
                public String app_long_image4;
                public String app_long_image5;
                public String short_description;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public boolean isChoose() {
                    return choose;
                }

                public void setChoose(boolean choose) {
                    this.choose = choose;
                }
            }
        }

    }

    public static class ShareBean {
    }
    /*private String name;
    private int price;
    private int count;
    private boolean choose;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }*/
}
