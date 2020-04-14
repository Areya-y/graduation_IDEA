package com.xmut.project.controller;


import com.xmut.project.entity.word;
import com.xmut.project.entity.wordDetalis;
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
@RequestMapping("/superadmin")
public class wordDetalisController {
    @Autowired
    private wordDetalisService wdService;
    /**
     * 获取所有的单词详情
     *
     * @return
     */
    @RequestMapping(value = "/listwords", method = RequestMethod.GET)
    private Map<String, Object> listWords(){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<wordDetalis> wordDetalisList=new ArrayList<wordDetalis>();
        List<word> wordList=new ArrayList<>();

        wordDetalisList=wdService.queryWordDetail();
        for(wordDetalis wd:wordDetalisList){
            word word=new word(wd.getWordId(),wd.getWord(),wd.getSoundMark(),wd.getInflexion(),wd.getTestRequency(),wd.getDegree());

            String[] wd_sentences;
            wd_sentences=wd.getSentences().split("=");
            word.setSentences(wd_sentences);

            List<String> interpretations=new ArrayList<>();
            if (wd.getNoun()!=null&&"".equals(wd.getNoun())==false){
                interpretations.add(wd.getNoun());
            }
            if (wd.getAdjectives()!=null&&"".equals(wd.getAdjectives())==false){
                interpretations.add(wd.getAdjectives());
            }
            if (wd.getAdverbs()!=null&&"".equals(wd.getAdverbs())==false){
                interpretations.add(wd.getAdverbs());
            }
            if (wd.getConjunction()!=null&&"".equals(wd.getConjunction())==false){
                interpretations.add(wd.getConjunction());
            }
            if (wd.getIntransitiveVerb()!=null&&"".equals(wd.getIntransitiveVerb())==false){
                interpretations.add(wd.getIntransitiveVerb());
            }
            if (wd.getTransitiveVerb()!=null&&"".equals(wd.getTransitiveVerb())==false){
                interpretations.add(wd.getTransitiveVerb());
            }
            if (wd.getPreposition()!=null&&"".equals(wd.getPreposition())==false){
                interpretations.add(wd.getPreposition());
            }
            if (wd.getPronouns()!=null&&"".equals(wd.getPronouns())==false){
                interpretations.add(wd.getPronouns());
            }
            word.setInterpretation(interpretations);
            wordList.add(word);
            System.out.println(word.toString());
        }
        modelMap.put("listWords",wordList);
        return modelMap;
    }
    /**
     * 通过Id获取信息
     *
     * @return
     */
    @RequestMapping(value = "/getwordbyid", method = RequestMethod.GET)
    private Map<String, Object> getAreaById(Integer wordId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        wordDetalis wordDetalis = wdService.queryWordDetailById(wordId);
        modelMap.put("word", wordDetalis);
        return modelMap;
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
