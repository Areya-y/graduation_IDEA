package com.xmut.project.controller;


import com.xmut.project.dao.userDao;
import com.xmut.project.entity.user;
import com.xmut.project.entity.userLearning;
import com.xmut.project.entity.word;
import com.xmut.project.entity.wordDetalis;
import com.xmut.project.service.userSettingService;
import com.xmut.project.service.userWordLearningService;
import com.xmut.project.service.wordDetalisService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wordDetalisController")
public class wordDetalisController {
    @Autowired
    private wordDetalisService wdService;
    @Autowired
    private userWordLearningService userWordLearningService;
    @Autowired
    private userSettingService userSettingService;

//    /**
//     * 获取所有的单词详情
//     *
//     * @return
//     */
//    @RequestMapping(value = "/listwords", method = RequestMethod.GET)
//    private Map<String, Object> listWords(){
//        Map<String, Object> modelMap = new HashMap<String, Object>();
//        List<wordDetalis> wordDetalisList=new ArrayList<wordDetalis>();
//        List<word> wordList=new ArrayList<>();
//        wordDetalisList=wdService.queryWordDetail();
//        wordList=wordDetalisToWord(wordDetalisList);
//        modelMap.put("listWords",wordList);
//        return modelMap;
//    }
    /**
     * 通过word获取信息
     *
     * @return
     */
    @RequestMapping(value = "/searchword", method = RequestMethod.GET)
    private Map<String, Object> searchWord(String word,Integer userID) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<userLearning> userLearningList= userWordLearningService.searchWord(word,userID);
        List<word> wordList=new ArrayList<>();
        wordList=wordDetalisToWord(userLearningList,userID);
        modelMap.put("searchword", wordList);
        return modelMap;
    }
    /**
     *获取所有的单词详情
     *
     */
    @RequestMapping(value = "/getAllWordLearningInfo", method = RequestMethod.GET)
    private Map<String, Object> getAllWordLearningInfo(Integer userID) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<userLearning> userLearningList=userWordLearningService.getAllWordLearningInfo(userID);
        List<word> wordList=new ArrayList<>();
        wordList=wordDetalisToWord(userLearningList,userID);
        modelMap.put("success",wordList);
        return modelMap;
    }

    private List<word> wordDetalisToWord(List<userLearning> userLearningList,Integer userID){
        List<word> wordList=new ArrayList<>();
        Integer wordBook=userSettingService.queryUserSettingById(userID).getWordBook();
        for(userLearning ul:userLearningList){
            word word=new word(ul.getWordId(),ul.getWord(),ul.getSoundMark(),ul.getInflexion());
            if (ul.getIsCollect()==null)
                word.setIsCollect(0);
            if (ul.getStudy_num()==null)
                word.setStudyNum(0);
            if (ul.getWrite_num()==null)
                word.setWriteNum(0);
            if(wordBook==0){
                word.setTestRequency(ul.getTestRequencyOne());
                word.setDegree(ul.getDegreeOne());
            }else {
                word.setTestRequency(ul.getTestRequencyTwo());
                word.setDegree(ul.getDegreeTwo());
            }
            String[] ul_sentences;
            ul_sentences=ul.getSentences().split("=");
            word.setSentences(ul_sentences);
            List<String> interpretations=new ArrayList<>();
            if (ul.getNoun()!=null&&"".equals(ul.getNoun())==false){
                interpretations.add(ul.getNoun());
            }
            if (ul.getAdjectives()!=null&&"".equals(ul.getAdjectives())==false){
                interpretations.add(ul.getAdjectives());
            }
            if (ul.getAdverbs()!=null&&"".equals(ul.getAdverbs())==false){
                interpretations.add(ul.getAdverbs());
            }
            if (ul.getConjunction()!=null&&"".equals(ul.getConjunction())==false){
                interpretations.add(ul.getConjunction());
            }
            if (ul.getIntransitiveVerb()!=null&&"".equals(ul.getIntransitiveVerb())==false){
                interpretations.add(ul.getIntransitiveVerb());
            }
            if (ul.getTransitiveVerb()!=null&&"".equals(ul.getTransitiveVerb())==false){
                interpretations.add(ul.getTransitiveVerb());
            }
            if (ul.getPreposition()!=null&&"".equals(ul.getPreposition())==false){
                interpretations.add(ul.getPreposition());
            }
            if (ul.getPronouns()!=null&&"".equals(ul.getPronouns())==false){
                interpretations.add(ul.getPronouns());
            }
            word.setInterpretation(interpretations);
            wordList.add(word);
        }
        System.out.println("wordList.size:"+wordList.size());

        return wordList;
    }


    /**
     * 添加信息
     *
     */
    @RequestMapping(value = "/addword", method = RequestMethod.POST)
    private Map<String, Object> addWordDetail(@RequestBody wordDetalis wordDetalis) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 添加区域信息
        modelMap.put("success", wdService.insertWordDetail(wordDetalis));
        return modelMap;
    }
    /**
     * 修改区域信息，主要修改名字
     *
     */
    @RequestMapping(value = "/modifyword", method = RequestMethod.POST)
    private Map<String, Object> modifyWord(@RequestBody wordDetalis wordDetalis) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success",wdService.updateWordDetail(wordDetalis));
        return modelMap;
    }

    @RequestMapping(value = "/removeword", method = RequestMethod.GET)
    private Map<String, Object> removeWord(Integer wordId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", wdService.deleteWordDetail(wordId));
        return modelMap;
    }


}
