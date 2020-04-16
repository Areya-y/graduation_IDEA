package com.xmut.project.service;

import com.xmut.project.entity.userLearning;

import java.util.List;

public interface userWordLearningService {
    /**
     * 根据userID创建用户的学习记录表
     * @param userID
     * @return
     */
    boolean  createNewTable(Integer userID);

    /**
     * 根据用户userID获取用户单词学习详细信息
     * @param userID
     * @return
     */
    List<userLearning> getAllWordLearningInfo(Integer userID);

    /**
     * 根据word列出单词具体信息
     *
     * @return word
     */
    List<userLearning> searchWord(String word,Integer userID);
}
