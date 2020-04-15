package com.xmut.project.controller;

import com.xmut.project.entity.userSetting;
import com.xmut.project.service.userSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class userSettingController {
    @Autowired
    userSettingService userSettingService;

    /**
     * 修改用户设置信息
     *
     */
    @RequestMapping(value = "/modifyusersetting", method = RequestMethod.POST)
    private Map<String, Object> modifyWord(Integer userID,String remindTime,Integer user_wordsBook,Integer user_wordNum) {
        userSetting userSetting=new userSetting(userID,user_wordsBook,remindTime,user_wordNum);
        System.out.println(userSetting.toString());
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success",userSettingService.updateUserSetting(userSetting));
        return modelMap;
    }

    /**
     * 根据用户ID获取该用户设置信息
     *
     */
    @RequestMapping(value = "/queryusersetting", method = RequestMethod.POST)
    private userSetting queryUserSetting(Integer userID){
        userSetting userSetting=userSettingService.queryUserSettingById(userID);
        return userSetting;
    }
}
