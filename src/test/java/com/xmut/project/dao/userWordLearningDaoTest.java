package com.xmut.project.dao;

import com.xmut.project.entity.userLearning;
import com.xmut.project.entity.word;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按方法名大小升序执行
class userWordLearningDaoTest {
    @Autowired
    userWordLearningDao userWordLearningDao;

    @Test
    void createNewTable() {
        Integer userID=10006;
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
        Integer userID=10008;
        String newTableName="word_learning_"+String.valueOf(userID);
        String word="acc";
        List<userLearning> userLearningList=userWordLearningDao.searchWordbyID(word,newTableName);
        for(userLearning i:userLearningList){
            System.out.println(i.toString());
        }

    }
    @Test
    void insertWordLearning(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer wordID=10700;
        int num=userWordLearningDao.insertWordLearning(wordID,newTableName);
        assertEquals(1,num);
    }
    @Test
    void addCollect(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer wordID=10693;
        int num=userWordLearningDao.addCollect(wordID,newTableName);
        assertEquals(1,num);
    }
    @Test
    void cancelCollect(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer wordID=10693;
        int num=userWordLearningDao.cancelCollect(wordID,newTableName);
        assertEquals(1,num);
    }

    @Test
    void queryWordLearningInfoByID(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer wordID=10500;
        Integer ulID=userWordLearningDao.queryWordLearningInfoByID(wordID,newTableName);
        System.out.println(ulID);
        assertEquals(10001,ulID);
    }

    @Test
    void getWordsPerBook(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer degree=0;
        Integer wordBook=1;
        List<userLearning> userLearningList=userWordLearningDao.getWordsPerBook(newTableName,degree,wordBook);
        assertEquals(263,userLearningList.size());
    }

    @Test
    void updateWordLearning(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        word word=new word(10694,"yawn","jɔːn","名词: yawner 过去式: yawned 过去分词: yawned 现在分词: yawning 第三人称单数: yawns",1,1,0);
        Map map =new HashMap();
        map.put("tableName",newTableName);
        map.put("isCollect",word.getIsCollect());
        map.put("studyNum",word.getStudyNum());
        map.put("writeNum",word.getWriteNum());
        map.put("wordID",word.getWordId());
        Integer num=userWordLearningDao.updateWordLearning(map);
        assertEquals(1,num);
    }
}