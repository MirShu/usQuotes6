package com.quantpower.usquotes.model;

/**
 * Created by likai on 2016/12/5.
 * email: codingkai@163.com
 */

public class GetUserInfoResponse {

    /**
     * code : 0
     * data : {"
     * id":"16",
     * "phone":"15813337262",
     * "password":"abed4842e22b37cc669e55b31cf6a90e",
     * "salt":"gHoIsv",
     * "nickname":"1581333726",
     * "point":"0",
     * "header":"http://118.178.121.211/Public/imgs/user/5848b04e3b027.png",
     * "continued_sign_day":"0","lastlogin":"2016-12-15 17:38:11",
     * "regtime":"2016-12-06 19:28:01",
     * "token":"fad44b86ed2415d62f530c12ac779921",
     * "expiry_date":null,
     * "user_title":null}
     * msg : 请求成功*
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * id : 16
         * phone : 15813337262
         * password : abed4842e22b37cc669e55b31cf6a90e
         * salt : gHoIsv
         * nickname : 1581333726
         * point : 0
         * header : http://118.178.121.211/Public/imgs/user/5848b04e3b027.png
         * continued_sign_day : 0
         * lastlogin : 2016-12-15 17:38:11
         * regtime : 2016-12-06 19:28:01
         * token : fad44b86ed2415d62f530c12ac779921
         * expiry_date : null
         * user_title : null
         * <p>
         * <p>
         * <p>
         * "count": [
         * {
         * "count(id)": "0"
         * }
         * ],
         * "sign": 0,
         * "signpoint": "5"
         * "invite_code": null,
         * "invitationed_uid": null,
         * "push_live_state": "0",
         * "push_information_state": "1",
         */
        private String invite_code;
        private String invitationed_uid;
        private String push_live_state;
        private String push_information_state;
        private String id;
        private String phone;
        private String password;
        private String salt;
        private String nickname;
        private String point;
        private String header;
        private String continued_sign_day;
        private String lastlogin;
        private String regtime;
        private String token;

        public String getLive_state() {
            return live_state;
        }

        public void setLive_state(String live_state) {
            this.live_state = live_state;
        }

        public String getNews_state() {
            return news_state;
        }

        public void setNews_state(String news_state) {
            this.news_state = news_state;
        }

        private String live_state;
        private String news_state;

        private Object expiry_date;
        private Object user_title;
        private int sign;
        private String signpoint;

        public String getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(String invite_code) {
            this.invite_code = invite_code;
        }

        public String getInvitationed_uid() {
            return invitationed_uid;
        }

        public void setInvitationed_uid(String invitationed_uid) {
            this.invitationed_uid = invitationed_uid;
        }

        public String getPush_live_state() {
            return push_live_state;
        }

        public void setPush_live_state(String push_live_state) {
            this.push_live_state = push_live_state;
        }

        public String getPush_information_state() {
            return push_information_state;
        }

        public void setPush_information_state(String push_information_state) {
            this.push_information_state = push_information_state;
        }

        public String getSignpoint() {
            return signpoint;
        }

        public void setSignpoint(String signpoint) {
            this.signpoint = signpoint;
        }

        public int getSign() {
            return sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getContinued_sign_day() {
            return continued_sign_day;
        }

        public void setContinued_sign_day(String continued_sign_day) {
            this.continued_sign_day = continued_sign_day;
        }

        public String getLastlogin() {
            return lastlogin;
        }

        public void setLastlogin(String lastlogin) {
            this.lastlogin = lastlogin;
        }

        public String getRegtime() {
            return regtime;
        }

        public void setRegtime(String regtime) {
            this.regtime = regtime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getExpiry_date() {
            return expiry_date;
        }

        public void setExpiry_date(Object expiry_date) {
            this.expiry_date = expiry_date;
        }

        public Object getUser_title() {
            return user_title;
        }

        public void setUser_title(Object user_title) {
            this.user_title = user_title;
        }
    }
}
