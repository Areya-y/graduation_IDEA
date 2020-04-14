package com.xmut.project.service.serviceImpl;

import com.xmut.project.dao.userDao;
import com.xmut.project.entity.user;
import com.xmut.project.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userDao userDao;

    @Override
    public List<user> queryUser() {
        List<user> userList=userDao.queryUser();
        return userList;
    }

    @Override
    public user queryUserById(String openid) {
        user user=userDao.queryUserById(openid);
        return user;
    }

    @Transient
    @Override
    public boolean insertUser(user user) {
        if (user.getNickName()==null&&"".equals(user.getNickName())&&user.getOpenid()==null&&"".equals(user.getOpenid())&&user.getSession_key()==null&&"".equals(user.getSession_key())){
            throw new RuntimeException("插入失败：插入不能为空");
        }
        else {
            try {
                int num=userDao.insertUser(user);
                if (num>0){
                    return true;
                }else {
                    throw new RuntimeException("插入失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("插入失败："+e.getMessage());
            }
        }
    }

    @Override
    public boolean updateUser(user user) {
        if (user.getNickName()==null&&"".equals(user.getNickName())&&user.getOpenid()==null&&"".equals(user.getOpenid())&&user.getSession_key()==null&&"".equals(user.getSession_key())){
            throw new RuntimeException("更新失败：更新不能为空");
        }
        else {
            try {
                int num=userDao.updateUser(user);
                if (num>0){
                    return true;
                }else {
                    throw new RuntimeException("更新失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("更新失败："+e.getMessage());
            }
        }
    }
}
