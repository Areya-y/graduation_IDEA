package com.xmut.project.dao;

import com.xmut.project.entity.userLearning;
import com.xmut.project.entity.word;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface userWordLearningDao {
    /**
     * 创建用户的学习记录表
     * @param tableName
     * @return
     */
    int  createNewTable(String tableName);

    /**
     * 根据用户tableName获取用户单词学习详细信息
     * @param tableName
     * @return
     */
    List<userLearning> getAllWordLearningInfo(String tableName);

    /**
     * 模糊查询单词
     * @param word
     * @return
     */

    List<userLearning> searchWordbyID(@Param("word")String word, @Param("tableName")String tableName);

    /**
     * 增加单词学习记录
     * @param wordID
     * @param tableName
     * @return
     */
    int insertWordLearning(@Param("wordID")Integer wordID, @Param("tableName")String tableName);
    /**
     * 添加收藏
     * @param wordID
     * @param tableName
     * @return
     */
    int addCollect(@Param("wordID")Integer wordID, @Param("tableName")String tableName);

    /**
     * 取消收藏
     * @param wordID
     * @param tableName
     * @return
     */
    int cancelCollect(@Param("wordID")Integer wordID, @Param("tableName")String tableName);

    /**
     * 根据word_id是否在userLearning中
     * @param wordID
     * @param tableName
     * @return
     */
    Integer queryWordLearningInfoByID(@Param("wordID")Integer wordID, @Param("tableName")String tableName);

    /**
     * 获取每个程度下的所有的单词，并按照完成、未完成两部分返回
     * @param tableName
     * @param degree
     * @param wordBook
     * @return
     */
    List<userLearning> getWordsPerBook(@Param("tableName")String tableName,@Param("degree")Integer degree,@Param("wordBook")Integer wordBook);

    /**
     * 更新用户对word的学习情况
     * @param map
     * @return
     */
    Integer updateWordLearning(Map map);

    /**
     * 创建用户的签到表
     * @param tableName
     * @return
     */
    Integer createSignInTable(String tableName);

    /**
     * 插入用户的签到数据
     * @param tableName
     * @param signInDate
     * @return
     */
    Integer signIn(@Param("tableName")String tableName,@Param("signInDate")Date signInDate);

    /**
     * 获取用户的签到数据
     * @param tableName
     * @return
     */
    List<Date> signData(@Param("tableName")String tableName);

    /**
     * 获得用户生词本的数据
     * @param tableName
     * @return
     */

    List<userLearning> wordListCollect(@Param("tableName")String tableName);

    /**
     * 获得单词的信息
     * @param word
     * @param tableName
     * @return
     */
    userLearning getWordDetail(@Param("word")String word,@Param("tableName")String tableName);

}
