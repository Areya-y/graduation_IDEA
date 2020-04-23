package com.xmut.project.dao;

import com.xmut.project.entity.userLearning;
import com.xmut.project.entity.word;
import org.assertj.core.internal.Dates;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按方法名大小升序执行
class userWordLearningDaoTest {
    @Autowired
    userWordLearningDao userWordLearningDao;

    @Test
    void createNewTable() {
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        int num=userWordLearningDao.createNewTable(newTableName);
        System.out.println(newTableName);
        assertEquals(0,num);
    }

    @Test
    void getAllWordLearningInfo() {
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        List<userLearning> userLearningList=userWordLearningDao.getAllWordLearningInfo(newTableName);
//        for (userLearning i:userLearningList)
//            System.out.println(i.);
        assertEquals(692,userLearningList.size());
    }

    @Test
    void searchWordbyID(){
        Integer userID=10008;
        String newTableName="word_learning_"+String.valueOf(userID);
        String word="acc";
        List<userLearning> userLearningList=userWordLearningDao.searchWordbyID(word,newTableName);
        for(userLearning i:userLearningList){
            System.out.println(i.toString());
        }

    }
    @Test
    void insertWordLearning(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer wordID=10700;
        int num=userWordLearningDao.insertWordLearning(wordID,newTableName);
        assertEquals(1,num);
    }
    @Test
    void addCollect(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer wordID=10693;
        int num=userWordLearningDao.addCollect(wordID,newTableName);
        assertEquals(1,num);
    }
    @Test
    void cancelCollect(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer wordID=10693;
        int num=userWordLearningDao.cancelCollect(wordID,newTableName);
        assertEquals(1,num);
    }

    @Test
    void queryWordLearningInfoByID(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer wordID=10500;
        Integer ulID=userWordLearningDao.queryWordLearningInfoByID(wordID,newTableName);
        System.out.println(ulID);
        assertEquals(10001,ulID);
    }

    @Test
    void getWordsPerBook(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        Integer degree=0;
        Integer wordBook=1;
        List<userLearning> userLearningList=userWordLearningDao.getWordsPerBook(newTableName,degree,wordBook);
        assertEquals(263,userLearningList.size());
    }

    @Test
    void updateWordLearning(){
        Integer userID=10006;
        String newTableName="word_learning_"+String.valueOf(userID);
        word word=new word(10694,"yawn","jɔːn","名词: yawner 过去式: yawned 过去分词: yawned 现在分词: yawning 第三人称单数: yawns",1,1,0);
        Map map =new HashMap();
        map.put("tableName",newTableName);
        map.put("isCollect",word.getIsCollect());
        map.put("studyNum",word.getStudyNum());
        map.put("writeNum",word.getWriteNum());
        map.put("wordID",word.getWordId());
        Integer num=userWordLearningDao.updateWordLearning(map);
        assertEquals(1,num);
    }

    @Test
    void createSignInTable() {
        int userID=10008;
        String signinTableName="user_signin_"+String.valueOf(userID);
        Integer num=userWordLearningDao.createSignInTable(signinTableName);
    }

    @Test
    void signIn() throws ParseException {
        int userID=10010;
        String signinTableName="user_signin_"+String.valueOf(userID);
        List<String> sourceStringList=new ArrayList<>();
        sourceStringList.add("2019-09-02");
        sourceStringList.add("2019-09-03");
        sourceStringList.add("2019-09-04");
        sourceStringList.add("2019-09-06");
        sourceStringList.add("2019-09-07");
        sourceStringList.add("2019-09-07");
        sourceStringList.add("2019-09-08");
        sourceStringList.add("2019-09-09");
        sourceStringList.add("2019-09-10");
        sourceStringList.add("2019-09-10");
        sourceStringList.add("2019-10-02");
        sourceStringList.add("2019-10-02");
        sourceStringList.add("2019-10-02");
        sourceStringList.add("2019-10-08");
        sourceStringList.add("2019-10-09");
        sourceStringList.add("2019-11-02");
        sourceStringList.add("2019-11-03");
        sourceStringList.add("2019-11-04");
        sourceStringList.add("2019-11-04");
        sourceStringList.add("2019-11-05");
        sourceStringList.add("2019-11-05");
        Integer num=0;
        for(String i :sourceStringList){
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(i);
            num+=userWordLearningDao.signIn(signinTableName,date);
        }
        assertEquals(sourceStringList.size(),num);
    }

    @Test
    void signData() throws ParseException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int userID=10010;
        String signinTableName="user_signin_"+String.valueOf(userID);
        List<Date> signinDates=userWordLearningDao.signData(signinTableName);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Set<Date> dateSet=new LinkedHashSet<>();
        for (Date i:signinDates){
            dateSet.add(i);
        }
        modelMap.put("signInDayNum",dateSet.size());
        for(Date i:dateSet){
            System.out.println(sdf.format(i));
        }
        List dateList=Arrays.asList(dateSet.toArray());

//        Date today =  sdf.parse(sdf.format(new Date()));
        Date lastday =  sdf.parse("2019-11-05");
        Map<String,Integer> result=new HashMap<>();

        List<Integer> continuousSignInDayList=new ArrayList<>();
        result = getContinuousSignInDay(dateList,lastday);
        continuousSignInDayList.add(result.get("continuousDay"));
        modelMap.put("continuousDay",result.get("continuousDay"));
        while (result.get("lastday")!=0){
            lastday=(Date) dateList.get(result.get("lastday"));
//            System.out.println("lastday:"+sdf.format(lastday));
            result=getContinuousSignInDay(dateList.subList(0,result.get("lastday")+1),lastday);
            continuousSignInDayList.add(result.get("continuousDay"));
        }
        System.out.println("MAX:");

        System.out.println(Collections.max(continuousSignInDayList));

        //从continuousSignInDayList获取最大值就是最大连续天数
//        modelMap.put("MaxContinuousDay",);
    }


    /**
     * 连续签到天数
     *
     * @return int
     * @Param
     **/
    private static Map<String,Integer> getContinuousSignInDay(List dateList,Date lastDate) throws ParseException {
        Map<String,Integer> resultMap=new HashMap<>();
        //continuousDay 连续签到数
        int continuousDay = 1;
        boolean todaySignIn = false;
        resultMap.put("lastday",0);
        for (int i = dateList.size()-1; i >=0; i--) {
            Date date = (Date) dateList.get(i);
//            System.out.println("date:"+date);
            int intervalDay = distanceDay(lastDate, date);
            //当天签到
            if (intervalDay == 0 && i == dateList.size()-1) {
                todaySignIn = true;
            }
            else if (intervalDay == continuousDay) {
                continuousDay++;
            }
            else {
                //不连续，终止判断
                resultMap.put("lastday",i);
                break;
            }
        }
        if (!todaySignIn) {
            continuousDay--;
        }
        System.out.println("continuousDay："+continuousDay);
        resultMap.put("continuousDay",continuousDay);
        return resultMap;
    }

    /**
     * 两个日期对比间隔天数
     *
     * @param smallDay
     * @return boolean
     * @Param largeDay
     **/
    private static int distanceDay(Date largeDay, Date smallDay) {
        int day = (int) ((largeDay.getTime() - smallDay.getTime()) / (1000 * 60 * 60 * 24));
        return day;
    }

    @Test
    void wordListCollect() {
        int userID=10010;
        String collectWordsName="word_learning_"+String.valueOf(userID);
        List<userLearning> userLearningList=userWordLearningDao.wordListCollect(collectWordsName);
        for(userLearning i:userLearningList){
            System.out.println(i.toString());
        }
    }

}


