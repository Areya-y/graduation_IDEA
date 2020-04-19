package com.xmut.project.controller;

import com.xmut.project.entity.word;
import com.xmut.project.service.userWordLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/wordLearningController")
@RestController
public class wordLearningController {
    @Autowired
    userWordLearningService userWordLearningService;

    /**
     * 创建单词学习记录
     *
     */
    @RequestMapping(value = "/insertWordLearning", method = RequestMethod.POST)
    private Map<String, Object> insertWordLearning(Integer wordID, Integer userID){


        Map<String, Object> modelMap = new HashMap<String, Object>();
        Integer wlID=userWordLearningService.queryWordLearningInfoByID(wordID, userID);
        String resultStr=new String();
        if(wlID!=null){
            resultStr="word_learning_"+userID+"已有"+wordID+"。";
        }else {
            userWordLearningService.insertWordLearning(wordID,userID);
            resultStr="word_learning_"+userID+"没有"+wordID+"     已插入";
        }
        System.out.println(resultStr);
        modelMap.put("success", resultStr);
        return modelMap;
    }

    /**
     * 增加单词书的学习记录
     *
     */
    @RequestMapping(value = "/insertWordBookLearning", method = RequestMethod.POST)
    private Map<String, Object> insertWordBookLearning(@RequestBody List<word> wordList,Integer userID){
        System.out.println("用户"+userID+" insertWordLearning:===");
        System.out.println(userID);
        Integer updateNum=0;
        for (word i:wordList){
            updateNum+=userWordLearningService.updateWordLearning(i,userID);
        }
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String resultStr="成功更新用户"+userID+"的"+updateNum+"条数据";
        modelMap.put("success", resultStr);
        return modelMap;
    }
    /**
     * 添加收藏
     *
     */
    @RequestMapping(value = "/addCollect", method = RequestMethod.POST)
    private Map<String, Object> addCollect(Integer wordID,Integer userID){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success",userWordLearningService.addCollect(wordID,userID));
        return modelMap;
    }
    /**
     * 取消收藏
     *
     */
    @RequestMapping(value = "/cancelCollect", method = RequestMethod.POST)
    private Map<String, Object> cancelCollect(Integer wordID,Integer userID){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success",userWordLearningService.cancelCollect(wordID,userID));
        return modelMap;
    }


}
