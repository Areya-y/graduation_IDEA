package com.xmut.project.entity;

import java.util.Arrays;
import java.util.List;

public class word {

    private Integer wordId ;
    private String word ;
    private String soundMark ;
    private List<String> interpretation ;
    private String[] sentences ;
    private String inflexion ;
    private	Integer	isCollect;
    private	Integer	studyNum;
    private	Integer	writeNum;
    private	Integer	testRequency;
    private	Integer	degree;

    public Integer getTestRequency() {
        return testRequency;
    }

    public void setTestRequency(Integer testRequency) {
        this.testRequency = testRequency;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }


    public word(Integer wordId, String word, String soundMark, String inflexion) {
        this.wordId = wordId;
        this.word = word;
        this.soundMark = soundMark;
        this.inflexion = inflexion;

    }

    public word(Integer wordId, String word, String soundMark, String inflexion,  Integer isCollect, Integer studyNum, Integer writeNum) {
        this.wordId = wordId;
        this.word = word;
        this.soundMark = soundMark;
        this.inflexion = inflexion;

        this.isCollect = isCollect;
        this.studyNum = studyNum;
        this.writeNum = writeNum;
    }

    public Integer getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Integer isCollect) {
        this.isCollect = isCollect;
    }

    public Integer getStudyNum() {
        return studyNum;
    }

    public void setStudyNum(Integer studyNum) {
        this.studyNum = studyNum;
    }

    public Integer getWriteNum() {
        return writeNum;
    }

    public void setWriteNum(Integer writeNum) {
        this.writeNum = writeNum;
    }



    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSoundMark() {
        return soundMark;
    }

    public void setSoundMark(String soundMark) {
        this.soundMark = soundMark;
    }

    public List<String> getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(List<String> interpretation) {
        this.interpretation = interpretation;
    }

    public String[] getSentences() {
        return sentences;
    }

    public void setSentences(String[] sentences) {
        this.sentences = sentences;
    }

    public String getInflexion() {
        return inflexion;
    }

    public void setInflexion(String inflexion) {
        this.inflexion = inflexion;
    }



    public word() {
    }



}
