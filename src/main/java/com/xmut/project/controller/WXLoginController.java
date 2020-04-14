package com.xmut.project.controller;
import java.lang.String;
import java.util.List;

import com.xmut.project.common.HttpClientUtil;
import com.xmut.project.common.JSONResult;
import com.xmut.project.common.JsonUtils;
import com.xmut.project.entity.WXSessionModel;
import com.xmut.project.entity.user;
import com.xmut.project.service.serviceImpl.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WXLoginController {
    @Autowired
    private userServiceImpl userServiceImpl;

    @PostMapping("/wxLogin")
    public JSONResult wxLogin(String code,String nickName){
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
        return JSONResult.ok();
    }
}
