package com.xmut.project.service;

import com.xmut.project.entity.userLearning;
import org.apache.ibatis.annotations.Param;

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
    /**
     * 增加单词学习记录
     * @param wordID
     * @param userID
     * @return
     */
    boolean insertWordLearning(Integer wordID,Integer userID);
    /**
     * 添加收藏
     * @param wordID
     * @param userID
     * @return
     */
    boolean addCollect(Integer wordID,Integer userID);

    /**
     * 取消收藏
     * @param wordID
     * @param userID
     * @return
     */
    boolean cancelCollect(Integer wordID,Integer userID);
    /**
     * 根据word_id是否在userLearning中
     * @param wordID
     * @param userID
     * @return
     */
    Integer queryWordLearningInfoByID(Integer wordID,Integer userID);

    /**
     * 获取每个程度下的所有的单词，并按照完成、未完成两部分返回
     * @param userID
     * @param degree
     * @param wordBook
     * @return
     */
    List<userLearning> getWordsPerBook(Integer userID,Integer degree,Integer wordBook);


}
