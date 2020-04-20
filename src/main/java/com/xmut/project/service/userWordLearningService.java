package com.xmut.project.service;

import com.xmut.project.entity.userLearning;
import com.xmut.project.entity.word;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 更新用户对word的学习情况
     * @param word
     * @param userID
     * @return
     */
    Integer updateWordLearning(word word,Integer userID);

    /**
     * 创建用户的签到表
     * @param userID
     * @return
     */
    Integer createSignInTable(Integer userID);

    /**
     * 插入用户的签到数据
     * @param userID
     * @param signInDate
     * @return
     */
    Integer signIn(Integer userID,Date signInDate);

    /**
     * 获取用户的签到数据
     * @param userID
     * @return
     */
    List<Date> signData(Integer userID);
}
