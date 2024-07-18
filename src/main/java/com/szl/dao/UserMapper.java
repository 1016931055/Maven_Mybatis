package com.szl.dao;

import com.szl.pojo.User;

import java.util.List;

//dao层!!!
public interface UserMapper {
    /**
     * 功能： 查询所有的用户数据
     * @return
     */
    public List<User> findAllUser();

    /**
     * 根据给定的id值, 查询用户数据
     * @param id
     * @return
     */
    public User findUserById(int id);

    /**
     * 插入数据，返回受影响行数
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 修改用户数据
     * @param user
     * @return
     */
    public int updateUser(User user);

}
