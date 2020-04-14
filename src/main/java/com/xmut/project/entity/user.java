package com.xmut.project.entity;

public class user {
    private Integer userId ;
    private String nickName ;
    private String session_key ;
    private String openid ;

    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", session_key='" + session_key + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


}
