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

}
