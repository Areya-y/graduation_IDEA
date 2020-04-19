package com.xmut.project.controller;
import java.lang.String;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xmut.project.common.HttpClientUtil;
import com.xmut.project.common.JsonUtils;
import com.xmut.project.entity.WXSessionModel;
import com.xmut.project.entity.user;
import com.xmut.project.entity.wordDetalis;
import com.xmut.project.service.serviceImpl.userServiceImpl;
import com.xmut.project.service.userSettingService;
import com.xmut.project.service.userWordLearningService;
import com.xmut.project.service.wordDetalisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WXLoginController {
    @Autowired
    private userServiceImpl userServiceImpl;
    @Autowired
    private userSettingService userSettingService;
    @Autowired
    private userWordLearningService userWordLearningService;
    @Autowired
    private wordDetalisService wordDetalisService;

    @PostMapping("/wxLogin")
    public Map<String, Object> wxLogin(String code, String nickName){
        System.out.println("wxlogin - code: " + code);
        System.out.println("wxlogin - nickName: " + nickName);

//		https://api.weixin.qq.com/sns/jscode2session?
//				appid=APPID&
//				secret=SECRET&
//				js_code=JSCODE&
//				grant_type=authorization_code

        String appID="wxb33d8964a4bc109d";
        String appSecret="a7549588234d57a41bcfae166dbbe0e6";
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appID+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
        //不可以传到前端（不安全）
        String wxResult=HttpClientUtil.doGet(url,null);
        System.out.println(wxResult);
        WXSessionModel model=JsonUtils.jsonToPojo(wxResult,WXSessionModel.class);
        System.out.println(model.toString());
        //存入session到mysql
        user user=new user();
        user.setOpenid(model.getOpenid());
        user.setSession_key(model.getSession_key());
        user.setNickName(nickName);
        System.out.println(user.toString());
        boolean isAdd=false;
        if (userServiceImpl.queryUserById(user.getOpenid())!=null){
            isAdd=userServiceImpl.updateUser(user);
            System.out.println(isAdd);
        }else {

            isAdd=userServiceImpl.insertUser(user);
            System.out.println(isAdd);
        }
        if(isAdd){
            System.out.println("用户添加成功");
        }else {
            System.out.println("用户添加失败");
        }
        List<user> userList=userServiceImpl.queryUser();
        System.out.println("===用户列表");
        for(user i:userList){
            System.out.println(i.toString());
        }

        //返回userID
        Integer userID=userServiceImpl.queryUserById(user.getOpenid()).getUserId();
        user userEntity=new user();
        userEntity.setUserId(userID);
        System.out.println("返回用户");
        System.out.println(userEntity.toString());

        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("userID",userEntity);

        //将用户ID加入user_setting表中
        boolean isHaveUser=false;
        if (userSettingService.queryUserSettingById(userID)==null){
            isHaveUser=userSettingService.insertUserSetting(userID);
            System.out.println("=将user_id加入userSetting表中");
            modelMap.put("success","将user_id加入userSetting表中");
        }

        //根据用户userID创建用户自己的学习情况表
        if (userServiceImpl.queryUserById(user.getOpenid())==null){
            System.out.println("create:"+userID);
            userWordLearningService.createNewTable(userID);
            modelMap.put("success","create:word_learning_10006");
            List<wordDetalis> wordDetalisList=wordDetalisService.queryWordDetail();
            Integer insertNum=0;
            for (wordDetalis i:wordDetalisList){
                if(userWordLearningService.insertWordLearning(i.getWordId(),userID)){
                    insertNum++;
                }
            }
            System.out.println("成功insert用户"+userID+"的"+insertNum+"条数据");
            modelMap.put("success","成功insert用户"+userID+"的"+insertNum+"条数据");

        }


        return modelMap;
    }
}
