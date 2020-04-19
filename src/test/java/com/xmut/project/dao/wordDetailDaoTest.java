package com.xmut.project.dao;

import com.xmut.project.entity.word;
import com.xmut.project.entity.wordDetalis;
import com.xmut.project.service.wordDetalisService;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按方法名大小升序执行
class wordDetailDaoTest {
    //通过spring容器注入Dao的实现类
    @Autowired
    private wordDetailDao wordDetailDao;
    @Autowired
    wordDetalisService wordDetalisService;

    @Test
    @Ignore
    void queryWordDetail() {
        List<wordDetalis> wordDetailList=wordDetailDao.queryWordDetail();
        for(wordDetalis wd:wordDetailList){
            System.out.println("Dao:");
            System.out.print(wd.getWordId());

        }

        // 验证预期值和实际值是否相符
        assertEquals( 20,wordDetailList.size());
    }

    @Test
    void queryWordDetailById() {
        List<wordDetalis> wordDetailList = wordDetailDao.searchWord("ac");
        for(wordDetalis wd:wordDetailList){
            System.out.print("Dao:");
            System.out.println(wd.toString());
        }
        System.out.println(wordDetailList.size());
//        assertEquals("n.酸；酸性物质；迷幻药",wd.getNoun());
    }

    @Test
    void insertWordDetail() {
        //创建一个区域对象
        wordDetalis wd = new wordDetalis();
        wd.setWord("word");
        wd.setSoundMark("wɜːrd");
//        wd.setTestRequency(4);
//        wd.setDegree(2);
        //将该对象实例添加入库
        int effectedNum = wordDetailDao.insertWordDetail(wd);
        //检测影响行数
        assertEquals(1, effectedNum);
        //校验总数是否+1
        List<wordDetalis> areaList = wordDetailDao.queryWordDetail();
        assertEquals(218, areaList.size());
    }

    @Test
    void updateWordDetail() {
        wordDetalis wd = new wordDetalis();
        wd.setWordId(10221);
        wd.setWord("development ");
        wd.setSoundMark("dɪ'veləpmənt");
        wd.setNoun("n.发展；开发；生长；进展；[摄]显影；新开发区");
        wd.setInflexion("形容词: developmental 副词: developmentally");
        System.out.print("==="+wd.toString());
        int num=wordDetailDao.updateWordDetail(wd);
        assertEquals(1,num);
    }

    @Test
    void deleteWordDetail() {
        int num=wordDetailDao.deleteWordDetail(10221);
        assertEquals(1,num);
    }

    @Test
    void test1(){
        int size=18;
        int randomNumber;
        List<String> answersList=new ArrayList<>();
        List randomNumberList=new ArrayList();

        for (int i = 0; i <size ; i++) {
            randomNumber = (int) Math.round(Math.random()*(4-1)+1);
            randomNumberList.add(randomNumber);
            if(randomNumber==1){
                answersList.add("A");
            }
            if(randomNumber==2){
                answersList.add("B");
            }
            if(randomNumber==3){
                answersList.add("C");
            }
            if(randomNumber==4){
                answersList.add("D");
            }
        }

    }
    @Test
    void test2(){
        List<word> wordAllList=wordDetalisToWord(wordDetalisService.queryWordDetail());
        List<word> wordsList= wordAllList.subList(5,13);




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