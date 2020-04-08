package com.xmut.project.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.xmut.project.entity.wordDetalis;
import com.xmut.project.service.wordDetalisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
        List<wordDetalis> wordsList=new ArrayList<wordDetalis>();
        wordsList=wdService.queryWordDetail();
        for(wordDetalis wd:wordsList)
            System.out.print(wd.getWordId());
        modelMap.put("listWords",wordsList);
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
