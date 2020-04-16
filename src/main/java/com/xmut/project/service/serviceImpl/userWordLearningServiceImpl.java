package com.xmut.project.service.serviceImpl;

import com.xmut.project.dao.userWordLearningDao;
import com.xmut.project.entity.userLearning;
import com.xmut.project.service.userWordLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class userWordLearningServiceImpl implements userWordLearningService {
    @Autowired
    userWordLearningDao userWordLearningDao;

    @Override
    public boolean createNewTable(Integer userID) {
        String newTableName="word_learning_"+String.valueOf(userID);
        int num= userWordLearningDao.createNewTable(newTableName);
        System.out.println("creat:"+newTableName);
        return true;
    }

    @Override
    public List<userLearning> getAllWordLearningInfo(Integer userID) {
        String newTableName="word_learning_"+String.valueOf(userID);
        return userWordLearningDao.getAllWordLearningInfo(newTableName);
    }

    @Override
    public List<userLearning> searchWord(String word, Integer userID) {
        String newTableName="word_learning_"+String.valueOf(userID);
        return userWordLearningDao.searchWordbyID(word,newTableName);
    }

}
