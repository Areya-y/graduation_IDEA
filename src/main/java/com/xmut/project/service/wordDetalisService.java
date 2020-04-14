package com.xmut.project.service;

import com.xmut.project.entity.wordDetalis;

import java.util.List;

public interface wordDetalisService {
    /**
     * 列出单词列表
     *
     * @return wordDetailList
     */
    List<wordDetalis> queryWordDetail();

    /**
     * 根据word列出单词具体信息
     *
     * @return word
     */
    List<wordDetalis> searchWord(String word);

    /**
     * 插入单词信息
     *
     * @param wordDetalis
     * @return
     */
    boolean insertWordDetail(wordDetalis wordDetalis);

    /**
     * 更新区域信息
     *
     * @param wordDetalis
     * @return
     */
    boolean updateWordDetail(wordDetalis wordDetalis);

    /**
     * 删除区域信息
     *
     * @param wordId
     * @return
     */
    boolean deleteWordDetail(int wordId);
}
