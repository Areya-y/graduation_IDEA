package com.xmut.project.controller;

import com.xmut.project.service.userWordLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/wordLearningController")
@RestController
public class wordLearningController {
    @Autowired
    userWordLearningService userWordLearningService;

    /**
     * 增加单词学习记录
     *
     */
    @RequestMapping(value = "/insertWordLearning", method = RequestMethod.POST)
    private Map<String, Object> insertWordLearning(Integer wordID,Integer userID){
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
