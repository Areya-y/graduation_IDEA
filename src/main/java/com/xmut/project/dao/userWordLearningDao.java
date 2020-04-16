package com.xmut.project.dao;

import com.xmut.project.entity.userLearning;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param word
     * @param tableName
     * @return
     */
    int addCollect(@Param("word")String word, @Param("tableName")String tableName);

    /**
     * 取消收藏
     * @param word
     * @param tableName
     * @return
     */
    int cancelCollect(@Param("word")String word, @Param("tableName")String tableName);
}
