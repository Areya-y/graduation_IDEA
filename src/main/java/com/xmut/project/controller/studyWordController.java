package com.xmut.project.controller;

import com.xmut.project.entity.word;
import com.xmut.project.entity.wordDetalis;
import com.xmut.project.service.wordDetalisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/studyWordController")
public class studyWordController {

    @Autowired
    wordDetalisService wordDetalisService;
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

    /**
     * 根据输入的wordsList获取相应的selections、answers
     * @param wordsList
     * @return
     */
    @RequestMapping(value = "/getselections", method = RequestMethod.POST)
    private Map<String, Object> getSelections(@RequestBody List<word> wordsList){
        Map<String,Object> resultMap=new HashMap<>();

        List<word> wordAllList=wordDetalisToWord(wordDetalisService.queryWordDetail());
        //生成answersList
        int randomNumber;
        List<String> answersList=new ArrayList<>();
        List randomNumberList=new ArrayList();

        for (int i = 0; i <wordsList.size() ; i++) {
            randomNumber = (int) Math.round(Math.random()*(3-0)+0);
            randomNumberList.add(randomNumber);
            if(randomNumber==0){
                answersList.add("A");
            }
            if(randomNumber==1){
                answersList.add("B");
            }
            if(randomNumber==2){
                answersList.add("C");
            }
            if(randomNumber==3){
                answersList.add("D");
            }
        }
        resultMap.put("answersList",answersList);

        //定义selections列表
        List<List<String>> selectionsList=new ArrayList<>();
        List<List>  setList=new ArrayList<List>();
        for (int i = 0; i <wordsList.size() ; i++){
//            System.out.println("==="+wordsList.get(i).getWord());
            LinkedHashSet<String> set = new LinkedHashSet<String>();
            set.add(listToString(wordsList.get(i).getInterpretation()));
            randomSet(wordsList.size()-1,4, set,wordAllList);

            List selections=Arrays.asList(set.toArray());

            randomNumber= (int) randomNumberList.get(i);
            if (randomNumber!=0){
                String right=(String) selections.get(0);
                String wrong=(String) selections.get(randomNumber);
                selections.set(0,wrong);
                selections.set(randomNumber,right);
            }
            selectionsList.add(selections);
        }
        resultMap.put("selectionsList",selectionsList);

        return resultMap;
    }

    /**
     * 随机生成selectionsList
     * 利用HashSet的特征，只能存放不同的值
     * @param max 指定范围最大值
     * @param set 随机数结果集
     */
    public void randomSet(int max, int n, LinkedHashSet<String> set, List<word> wordAllList) {
        int min=0;
        if (n > (max - min + 1) || max < min) {
            return ;
        }
        for (int i = 0; i < n; i++) {
            if(set.size()==n){
                break;
            }
            // 调用Math.random()方法
            int num = (int) (Math.random() * (max - min)) + min;
            set.add(listToString(wordAllList.get(num).getInterpretation()));// 将不同的数存入LinkedHashSet中
        }
        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
        if (set.size() <n) {
            randomSet( max, n , set,wordAllList);// 递归
        }
    }
    public String listToString(List list){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i==list.size()-1){
                sb.append(list.get(i));
            }else {
                sb.append(list.get(i));
                sb.append(' ');
            }
        }
        return sb.toString();
    }
    public Map<String, List<word>> ListToLists(List<word> wordsList){
        List<word> strangeWordsList=new ArrayList<>();
        List<word> knowWordsList=new ArrayList<>();
        List<word> familiarWordsList=new ArrayList<>();

        for (word i:wordsList){
            if (i.getStudyNum()==null){
                i.setStudyNum(0);
                strangeWordsList.add(i);
                continue;
            }
            if (i.getStudyNum()==0){
                strangeWordsList.add(i);
            }else{
                if(i.getStudyNum()==1){
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

    public List<word> wordDetalisToWord(List<wordDetalis> wordDetalisList){
        List<word> wordList=new ArrayList<>();
        for(wordDetalis ul:wordDetalisList){
            word word=new word(ul.getWordId(),ul.getWord(),ul.getSoundMark(),ul.getInflexion());
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

}
