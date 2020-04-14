package com.xmut.project.dao;

import com.xmut.project.entity.user;
import java.util.List;

public interface userDao {
    /**
     * 列出用户列表
     *
     * @return userList
     */
    List<user> queryUser();

    /**
     * 根据openid列出用户具体信息
     *
     * @return user
     */
    user queryUserById(String openid);

    /**
     * 插入用户信息
     *
     * @param user
     * @return
     */
    int insertUser(user user);

    /**
     * 更新用户昵称
     *
     * @param user
     * @return
     */
    int updateUser(user user);
}
