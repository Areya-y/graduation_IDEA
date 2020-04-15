package com.xmut.project.entity;

public class userSetting {
    private Integer settingID;
    private Integer userID;
    private Integer wordBook;
    private String remind_time;
    private Integer wordsNumPer;

    public userSetting() {
    }

    public userSetting( Integer userID, Integer wordBook, String remind_time, Integer wordsNumPer) {
        this.userID = userID;
        this.wordBook = wordBook;
        this.remind_time = remind_time;
        this.wordsNumPer = wordsNumPer;
    }

    public Integer getSettingID() {
        return settingID;
    }

    public void setSettingID(Integer settingID) {
        this.settingID = settingID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getWordBook() {
        return wordBook;
    }

    public void setWordBook(Integer wordBook) {
        this.wordBook = wordBook;
    }

    public String getRemind_time() {
        return remind_time;
    }

    public void setRemind_time(String remind_time) {
        this.remind_time = remind_time;
    }

    public Integer getWordsNumPer() {
        return wordsNumPer;
    }

    public void setWordsNumPer(Integer wordsNumPer) {
        this.wordsNumPer = wordsNumPer;
    }

    @Override
    public String toString() {
        return "userSetting{" +
                "settingID=" + settingID +
                ", userID=" + userID +
                ", wordBook=" + wordBook +
                ", remind_time='" + remind_time + '\'' +
                ", wordsNumPer=" + wordsNumPer +
                '}';
    }
}
