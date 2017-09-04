package com.baway.bean;

/**
 * Created by 李宗书 on 2017/8/9.
 */

public class Regist3 {

    /**
     * status : true
     * code : 1
     * message : 调用成功
     * data : {"mobile":"12445884784"}
     * share : {}
     */

    private boolean status;
    private int code;
    private String message;
    private DataBean data;
    private ShareBean share;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
    }

    public static class DataBean {
        /**
         * mobile : 12445884784
         */

        private String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

    public static class ShareBean {
    }
}
