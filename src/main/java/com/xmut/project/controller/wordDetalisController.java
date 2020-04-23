package com.xmut.project.controller;


import com.xmut.project.entity.userLearning;
import com.xmut.project.entity.userSetting;
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

    /**
     * 通过word获取信息
     * @param word
     * @param userID
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
     * 获取所有的单词详情
     * @param userID
     * @return
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

    /**
     *获取每个程度下的所有的单词，并按照完成、未完成两部分返回
     * @param degree
     * @param userID
     * @return
     */
    @RequestMapping(value = "/getWordsPerBook", method = RequestMethod.POST)
    private Map<String, Object> getWordsPerBook(Integer degree,Integer userID){
        System.out.println("getWordsPerBook=>");
        userSetting userSetting=userSettingService.queryUserSettingById(userID);
        Integer wordBook=userSetting.getWordBook();
        Integer wordsNumPer=userSetting.getWordsNumPer();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<userLearning> userLearningList=userWordLearningService.getWordsPerBook(userID,degree,wordBook);
        List<word> wordList=new ArrayList<>();
        wordList=wordDetalisToWord(userLearningList,userID);

        List<word> unfinished_words=new ArrayList<>();
        List<word> finished_words=new ArrayList<>();

        for (word i:wordList){
            if (i.getStudyNum()==null||i.getWriteNum()==null){
                unfinished_words.add(i);
            } else {

                if (i.getStudyNum()==2&&i.getWriteNum()==2){
                    finished_words.add(i);
                }else {
                    unfinished_words.add(i);
                }
            }
        }

        List<List<word>> unfinished_wordBook=groupList(unfinished_words,wordsNumPer);
        List<List<word>> finished_wordBook=groupList(finished_words,wordsNumPer);
        System.out.println("unfinished_wordBook.size():"+unfinished_wordBook.size());
        System.out.println("finished_wordBook.size():"+finished_wordBook.size());

        modelMap.put("unfinished_wordBook",unfinished_wordBook);
        modelMap.put("finished_wordBook",finished_wordBook);
        return modelMap;
    }


    /**
     * 获得用户生词本的数据
     * @param userID
     * @return
     */
    @RequestMapping(value = "/wordlistcollect", method = RequestMethod.POST)
    Map<String, Object> wordListCollect(Integer userID){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<userLearning> userLearningList=userWordLearningService.wordListCollect(userID);
        List<word> wordList=new ArrayList<>();
        wordList=wordDetalisToWord(userLearningList,userID);
        modelMap.put("wordlistcollect",wordList);
        return modelMap;
    }

    /*
     * List分割
     */
    public static List<List<word>> groupList(List<word> list,int toIndex) {
        List<List<word>> listGroup = new ArrayList<List<word>>();
        int listSize = list.size();
        for (int i = 0; i < list.size(); i += toIndex) {
            if (i + toIndex > listSize) {
                toIndex = listSize - i;
            }
            List<word> newList = list.subList(i, i + toIndex);
            listGroup.add(newList);
        }
        return listGroup;
    }


    public List<word> wordDetalisToWord(List<userLearning> userLearningList,Integer userID){
        List<word> wordList=new ArrayList<>();
        Integer wordBook=userSettingService.queryUserSettingById(userID).getWordBook();
        for(userLearning ul:userLearningList){
            word word=new word(ul.getWordId(),ul.getWord(),ul.getSoundMark(),ul.getInflexion(),ul.getIsCollect(),ul.getStudy_num(),ul.getWrite_num());

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
        System.out.println("wordDetalisToWord=>wordList.size:"+wordList.size());

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
