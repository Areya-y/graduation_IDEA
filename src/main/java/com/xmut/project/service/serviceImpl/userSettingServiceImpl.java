package com.xmut.project.service.serviceImpl;

import com.xmut.project.dao.userSettingDao;
import com.xmut.project.entity.userSetting;
import com.xmut.project.service.userSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class userSettingServiceImpl implements userSettingService {
    @Autowired
    private userSettingDao userSettingDao;

    @Override
    public userSetting queryUserSettingById(Integer userID) {
        return userSettingDao.queryUserSettingById(userID);
    }

    @Transient
    @Override
    public boolean insertUserSetting(Integer userID) {
        if(userID!=null&&userID!=0){
            try {
                int num=userSettingDao.insertUserSetting(userID);
                if(num>0){
                    return true;
                }else {
                    throw new RuntimeException("插入用户设置表失败");
                }
            }catch (Exception e){
                throw new RuntimeException("插入用户设置表失败："+e.getMessage());
            }
        }else{
            throw new RuntimeException("插入用户设置不能为空");
        }

    }

    @Override
    public boolean updateUserSetting(userSetting userSetting) {
        if(userSetting.getUserID()==null&&userSetting.getUserID()==0&&userSetting.getWordBook()==null&&userSetting.getWordBook()==0&&userSetting.getRemind_time()==null&&"".equals(userSetting.getRemind_time())&&userSetting.getWordsNumPer()==null&&userSetting.getWordsNumPer()==0){
            throw new RuntimeException("更新用户设置不能为空");
        }else{
            try {
                int num=userSettingDao.updateUserSetting(userSetting);
                if(num>0){
                    return true;
                }else {
                    throw new RuntimeException("更新用户设置表失败");
                }
            }catch (Exception e){
                throw new RuntimeException("更新用户设置表失败："+e.getMessage());
            }
        }
    }
}
