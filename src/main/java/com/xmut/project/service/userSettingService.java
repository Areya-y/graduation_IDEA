package com.xmut.project.service;

import com.xmut.project.entity.userSetting;

public interface userSettingService {
    /**
     * 根据userid列出用户具体设置信息
     *
     * @return user
     */
    userSetting queryUserSettingById(Integer userID);

    /**
     * 插入用户设置信息
     *
     * @param userID
     * @return
     */
    boolean insertUserSetting(Integer userID);

    /**
     * 更新用户设置
     *
     * @param userSetting
     * @return
     */
    boolean updateUserSetting(userSetting userSetting);
}
