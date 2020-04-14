package com.xmut.project.entity;

import java.util.Arrays;
import java.util.List;

public class word {
    @Override
    public String toString() {
        return "word{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", soundMark='" + soundMark + '\'' +
                ", interpretation=" + interpretation +
                ", sentences=" + Arrays.toString(sentences) +
                ", inflexion='" + inflexion + '\'' +
                ", testRequency=" + testRequency +
                ", degree=" + degree +
                '}';
    }

    private Integer wordId ;
    private String word ;
    private String soundMark ;
    private List<String> interpretation ;
    private String[] sentences ;
    private String inflexion ;
    private	Integer	testRequency 	;
    private	Integer	degree 	;
//    private	Integer	isCollect;

    public word() {
    }


    public word(Integer wordId, String word, String soundMark, String inflexion, Integer testRequency, Integer degree) {
        this.wordId = wordId;
        this.word = word;
        this.soundMark = soundMark;
        this.inflexion = inflexion;
        this.testRequency = testRequency;
        this.degree = degree;
//        this.isCollect=isCollect;
    }
//    @Override
//    public String toString() {
//        return "word{" +
//                "wordId=" + wordId +
//                ", word='" + word + '\'' +
//                ", soundMark='" + soundMark + '\'' +
//                ", interpretation=" + interpretation +
//                ", sentences=" + Arrays.toString(sentences) +
//                ", inflexion='" + inflexion + '\'' +
//                ", testRequency=" + testRequency +
//                ", degree=" + degree +
//                ", isCollect=" + isCollect +
//                '}';
//    }

    public List<String> getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(List<String> interpretation) {
        this.interpretation = interpretation;
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
//    public Integer getIsCollect() {
//        return isCollect;
//    }
//
//    public void setIsCollect(Integer isCollect) {
//        this.isCollect = isCollect;
//    }


}
