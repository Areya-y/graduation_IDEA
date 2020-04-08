package com.xmut.project.entity;

public class wordDetalis {
    private Integer wordId ;
    private String word ;
    private String soundMark ;
    private String noun ;
    private String transitiveVerb ;
    private String intransitiveVerb ;
    private String adjectives ;
    private String adverbs ;
    private String conjunction ;
    private String preposition ;
    private String pronouns ;
    private String sentences ;
    private String inflexion ;
    private	Integer	testRequency 	;
    private	Integer	degree 	;

    public wordDetalis() {
    }

    @Override
    public String toString() {
        return "wordDetalis{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", soundMark='" + soundMark + '\'' +
                ", noun='" + noun + '\'' +
                ", transitiveVerb='" + transitiveVerb + '\'' +
                ", intransitiveVerb='" + intransitiveVerb + '\'' +
                ", adjectives='" + adjectives + '\'' +
                ", adverbs='" + adverbs + '\'' +
                ", conjunction='" + conjunction + '\'' +
                ", preposition='" + preposition + '\'' +
                ", pronouns='" + pronouns + '\'' +
                ", sentences='" + sentences + '\'' +
                ", inflexion='" + inflexion + '\'' +
                ", testRequency=" + testRequency +
                ", degree=" + degree +
                '}';
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

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public String getTransitiveVerb() {
        return transitiveVerb;
    }

    public void setTransitiveVerb(String transitiveVerb) {
        this.transitiveVerb = transitiveVerb;
    }

    public String getIntransitiveVerb() {
        return intransitiveVerb;
    }

    public void setIntransitiveVerb(String intransitiveVerb) {
        this.intransitiveVerb = intransitiveVerb;
    }

    public String getAdjectives() {
        return adjectives;
    }

    public void setAdjectives(String adjectives) {
        this.adjectives = adjectives;
    }

    public String getAdverbs() {
        return adverbs;
    }

    public void setAdverbs(String adverbs) {
        this.adverbs = adverbs;
    }

    public String getConjunction() {
        return conjunction;
    }

    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    public String getPreposition() {
        return preposition;
    }

    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public String getSentences() {
        return sentences;
    }

    public void setSentences(String sentences) {
        this.sentences = sentences;
    }

    public String getInflexion() {
        return inflexion;
    }

    public void setInflexion(String inflexion) {
        this.inflexion = inflexion;
    }
}
