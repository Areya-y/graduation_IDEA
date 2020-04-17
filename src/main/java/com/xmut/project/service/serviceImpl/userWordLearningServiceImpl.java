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

    @Override
    public boolean insertWordLearning(Integer wordID, Integer userID) {
        if(wordID==null&&wordID==0&&userID==null&&userID==0){
            throw new  RuntimeException("插入单词学习记录：wordID、userID不能为空或0");
        }else{
            try {
                String newTableName="word_learning_"+String.valueOf(userID);
                int num=userWordLearningDao.insertWordLearning(wordID,newTableName);
                if(num>0){
                    return true;
                }else {
                    throw new RuntimeException("插入单词学习记录失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("插入单词学习记录失败:"+e.getMessage());
            }
        }
    }

    @Override
    public boolean addCollect(Integer wordID, Integer userID) {
        if(wordID==null&&wordID==0&&userID==null&&userID==0){
            throw new  RuntimeException("添加收藏失败：wordID、userID不能为空或0");
        }else{
            try {
                String newTableName="word_learning_"+String.valueOf(userID);
                int num=userWordLearningDao.addCollect(wordID,newTableName);
                if(num>0){
                    return true;
                }else {
                    throw new RuntimeException("添加收藏失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("添加收藏失败:"+e.getMessage());
            }
        }
    }

    @Override
    public boolean cancelCollect(Integer wordID, Integer userID) {
        if(wordID==null&&wordID==0&&userID==null&&userID==0){
            throw new  RuntimeException("取消收藏失败：wordID、userID不能为空或0");
        }else{
            try {
                String newTableName="word_learning_"+String.valueOf(userID);
                int num=userWordLearningDao.cancelCollect(wordID,newTableName);
                if(num>0){
                    return true;
                }else {
                    throw new RuntimeException("取消收藏失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("取消收藏失败:"+e.getMessage());
            }
        }
    }

    @Override
    public Integer queryWordLearningInfoByID(Integer wordID, Integer userID) {
        if(wordID==null&&wordID==0&&userID==null&&userID==0){
            throw new  RuntimeException("查询wi_id失败：wordID、userID不能为空或0");
        }else{
            try {
                String newTableName="word_learning_"+String.valueOf(userID);
                Integer wlID=userWordLearningDao.queryWordLearningInfoByID(wordID,newTableName);
                return wlID;
            }catch (Exception e){
                throw new RuntimeException("查询wi_id失败:"+e.getMessage());
            }
        }
    }

    @Override
    public List<userLearning> getWordsPerBook(Integer userID, Integer degree, Integer wordBook) {
        if(degree==null&&wordBook==null&&userID==null&&userID==0){
            throw new  RuntimeException("获取每个程度下的所有的单词，并按照完成、未完成两部分返回失败：userID不能为空或0,degree不能为空,wordBook不能为空");
        }else{
            try {
                String newTableName="word_learning_"+String.valueOf(userID);
                List<userLearning> userLearningList=userWordLearningDao.getWordsPerBook(newTableName,degree,wordBook);
                return userLearningList;
            }catch (Exception e){
                throw new RuntimeException("获取每个程度下的所有的单词，并按照完成、未完成两部分返回失败:"+e.getMessage());
            }
        }
    }


}
