package com.xmut.project.entity;

public class userLearning extends wordDetalis{

    private Integer ulID;
    private Integer isCollect;
    private Integer study_num;
    private Integer write_num;

    public Integer getUlID() {
        return ulID;
    }

    public void setUlID(Integer ulID) {
        this.ulID = ulID;
    }
    public Integer getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }

    public Integer getStudy_num() {
        return study_num;
    }

    public void setStudy_num(Integer study_num) {
        this.study_num = study_num;
    }

    public Integer getWrite_num() {
        return write_num;
    }

    public void setWrite_num(Integer write_num) {
        this.write_num = write_num;
    }
}
