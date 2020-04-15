package com.xmut.project.dao;

import com.xmut.project.entity.userSetting;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按方法名大小升序执行
class userSettingDaoTest {
    @Autowired
    private userSettingDao userSettingDao;

    @Test
    void queryUserSettingById() {
        Integer userID=10004;
        userSetting userSetting=userSettingDao.queryUserSettingById(userID);
        System.out.println(userSetting.toString());
//        assertEquals(1,);

    }

    @Test
    void insertUserSetting() {
        Integer userID=10004;
        int num=userSettingDao.insertUserSetting(userID);
        assertEquals(1,num);
    }

    @Test
    void updateUserSetting() {
        userSetting userSetting=new userSetting();
        userSetting.setUserID(10004);
        userSetting.setWordBook(1);
        userSetting.setRemind_time("10:00");
        userSetting.setWordsNumPer(56);
        int num=userSettingDao.updateUserSetting(userSetting);
        assertEquals(1,num);
    }
}