package com.xmut.project.dao;

import com.xmut.project.entity.user;
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
class userDaoTest {
    //通过spring容器注入Dao的实现类
    @Autowired
    private userDao userDao;
    @Test
    void queryUser() {
        List<user> userList=userDao.queryUser();
        for(user user:userList){
            System.out.println("Dao:");
            System.out.print(user.getNickName());
        }
        // 验证预期值和实际值是否相符
        assertEquals( 1,userList.size());
    }

    @Test
    void queryUserById() {
        user user=userDao.queryUserById("oPY2I5AIoJW_aq7oyAkL-Z49jj8M");
        assertEquals("Areya_",user.getNickName());
    }

    @Test
    void insertUser() {
        //创建一个区域对象
        user user = new user();
        user.setNickName("Areya_");
        user.setOpenid("oPY2I5AIoJW_aq7oyAkL-Z49jj8M");
        user.setSession_key("5j74j9sFq2V+qQry0QSCog==");
        //将该对象实例添加入库
        int effectedNum =userDao.insertUser(user);
        //检测影响行数
        assertEquals(1, effectedNum);
//        //校验总数是否+1
//        List<user> users = userDao.queryUser();
//        assertEquals(1, users.size());
    }

    @Test
    void updateUser() {
        //创建一个区域对象
        user user = new user();
        user.setNickName("阿锐呀");
        user.setOpenid("oPY2I5AIoJW_aq7oyAkL-Z49jj8M");
        user.setSession_key("55555555+qQry0QSCog==");
        //将该对象实例添加入库
        int effectedNum =userDao.updateUser(user);
        //检测影响行数
        assertEquals(1, effectedNum);
    }
}