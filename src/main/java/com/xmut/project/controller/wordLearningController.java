package com.xmut.project.controller;

import com.xmut.project.entity.word;
import com.xmut.project.service.userWordLearningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    /**
     * 签到
     *
     */
    @RequestMapping(value = "/usersignin", method = RequestMethod.POST)
    private Map<String, Object> userSignIn(Integer userID) throws ParseException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date signInDate =  sdf.parse(sdf.format(new Date()));
        Integer signInDateNum=userWordLearningService.signIn(userID,signInDate);
        modelMap.put("success",signInDateNum);
        return modelMap;
    }

    /**
     * 获取签到数据
     *
     */
    @RequestMapping(value = "/signindata", method = RequestMethod.POST)
    private Map<String, Object> signInData(Integer userID) throws ParseException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> signinDates= userWordLearningService.signData(userID);
        Set<Date> dateSet=new LinkedHashSet<>();
        for (Date i:signinDates){
            dateSet.add(i);
        }
        //注入签到天数
        modelMap.put("signInDayNum",dateSet.size());
//        for(Date i:dateSet){
//            System.out.println(sdf.format(i));
//        }
        List dateList=Arrays.asList(dateSet.toArray());

        Date lastday =  sdf.parse(sdf.format(new Date()));
        Map<String,Integer> result=new HashMap<>();

        List<Integer> continuousSignInDayList=new ArrayList<>();
        result = getContinuousSignInDay(dateList,lastday);
        //注入连续签到
        continuousSignInDayList.add(result.get("continuousDay"));
        modelMap.put("continuousDay",result.get("continuousDay"));
        while (result.get("lastday")!=0){
            lastday=(Date) dateList.get(result.get("lastday"));
//            System.out.println("lastday:"+sdf.format(lastday));
            result=getContinuousSignInDay(dateList.subList(0,result.get("lastday")+1),lastday);
            continuousSignInDayList.add(result.get("continuousDay"));
        }
        //注入最长连续签到
        modelMap.put("longestContinuousDay",Collections.max(continuousSignInDayList));
        return modelMap;
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
}
