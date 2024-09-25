package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsUser;

/**
 * ClassName:NewsUserService
 * Package:com.atguigu.headline.service
 * Description:
 *
 * @Author UESTC-史杰灵
 * @Create 2024/9/25 0:24
 * @Version 1.0
 */
public interface NewsUserService {
    /**
     * 根据用户登录的账号找用户新的方法
     * @param username  用户输入的账户
     * @return  找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUsername(String username);

    Integer registUser(NewsUser registUser);
}
