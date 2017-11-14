package com.quantpower.usquotes.model;

import java.util.List;

/**
 * Created by likai on 2017/1/11.
 * email: codingkai@163.com
 */

public class CollectionNewsModel {


    /**
     * code : 0
     * msg : 请求成功
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * type : null
         * news_time : null
         * title : 专家热议2017年金融市场 美元、黄金和原油究竟如何走？
         * img : http://118.178.121.211/Public/imgs/originalnews/5861dc96b34cd.jpg|58623a04cae4d.jpg|58623a0da1429.jpg
         * tips : 1
         * by : 兰森金融
         */

        private String id;
        private String type;
        private Object news_time;
        private String title;
        private String img;
        private String tips;
        private String by;
        private String state;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getNews_time() {
            return news_time;
        }

        public void setNews_time(Object news_time) {
            this.news_time = news_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public String getBy() {
            return by;
        }

        public void setBy(String by) {
            this.by = by;
        }
    }
}
