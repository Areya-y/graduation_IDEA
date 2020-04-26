package com.xmut.project.entity;

public class userRank implements Comparable<userRank>{
    private Integer userID;
    private String nickName;
    private Integer studyNum;

    public userRank() {
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getStudyNum() {
        return studyNum;
    }

    public void setStudyNum(Integer studyNum) {
        this.studyNum = studyNum;
    }

    @Override
    public String toString() {
        return "userRank{" +
                "userID=" + userID +
                ", nickName='" + nickName + '\'' +
                ", studyNum=" + studyNum +
                '}';
    }

    //降序排列
    @Override
    public int compareTo(userRank userRank) {
        return userRank.studyNum-this.studyNum;
    }
}
