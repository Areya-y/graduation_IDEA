package com.xmut.project.dao;

import com.xmut.project.entity.wordDetalis;

import java.util.List;


public interface wordDetailDao {
    /**
     * 列出单词列表
     *
     * @return wordDetailList
     */
    List<wordDetalis> queryWordDetail();

    /**
     * 根据Id列出单词具体信息
     *
     * @return wordDetalis
     */
    wordDetalis queryWordDetailById(int wordId);

    /**
     * 插入单词信息
     *
     * @param wordDetalis
     * @return
     */
    int insertWordDetail(wordDetalis wordDetalis);

    /**
     * 更新区域信息
     *
     * @param wordDetalis
     * @return
     */
    int updateWordDetail(wordDetalis wordDetalis);

    /**
     * 删除区域信息
     *
     * @param wordId
     * @return
     */
    int deleteWordDetail(int wordId);
}
