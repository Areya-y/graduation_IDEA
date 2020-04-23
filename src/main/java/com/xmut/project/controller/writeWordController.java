package com.xmut.project.controller;

import com.xmut.project.entity.word;
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
@RequestMapping("/writeWordController")
public class writeWordController {
    @Autowired
    com.xmut.project.service.wordDetalisService wordDetalisService;
    /**
     * 将wordsList分成三个List
     * @param wordsList
     * @return
     */
    @RequestMapping(value = "/convertwordlist", method = RequestMethod.POST)
    private Map<String, List<word>> convertWordList(@RequestBody List<word> wordsList){
        Map<String, List<word>> resultMap=ListToLists(wordsList);
        return resultMap;
    }
    public Map<String, List<word>> ListToLists(List<word> wordsList){
        List<word> strangeWordsList=new ArrayList<>();
        List<word> knowWordsList=new ArrayList<>();
        List<word> familiarWordsList=new ArrayList<>();

        for (word i:wordsList){
            if (i.getWriteNum()==null){
                i.setWriteNum(0);
                strangeWordsList.add(i);
                continue;
            }
            if (i.getWriteNum()==0){
                strangeWordsList.add(i);
            }else{
                if(i.getWriteNum()==1){
                    knowWordsList.add(i);
                }else {
                    familiarWordsList.add(i);
                }
            }
        }

        Map<String, List<word>> resultMap=new HashMap<>();
        resultMap.put("strangeWordsList",strangeWordsList);
        resultMap.put("knowWordsList",knowWordsList);
        resultMap.put("familiarWordsList",familiarWordsList);
        return resultMap;
    }

}
