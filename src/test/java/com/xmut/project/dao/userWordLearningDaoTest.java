package com.xmut.project.dao;

import com.xmut.project.entity.userLearning;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按方法名大小升序执行
class userWordLearningDaoTest {
    @Autowired
    userWordLearningDao userWordLearningDao;

    @Test
    void createNewTable() {
        Integer userID=10004;
        String newTableName="word_learning_"+String.valueOf(userID);
        int num=userWordLearningDao.createNewTable(newTableName);
        System.out.println(newTableName);
        assertEquals(0,num);
    }

    @Test
    void getAllWordLearningInfo() {
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        List<userLearning> userLearningList=userWordLearningDao.getAllWordLearningInfo(newTableName);
//        for (userLearning i:userLearningList)
//            System.out.println(i.);
        assertEquals(692,userLearningList.size());
    }

    @Test
    void searchWordbyID(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        String word="acc";
        List<userLearning> userLearningList=userWordLearningDao.searchWordbyID(word,newTableName);
        for(userLearning i:userLearningList){
            System.out.println(i.toString());
        }

    }
}