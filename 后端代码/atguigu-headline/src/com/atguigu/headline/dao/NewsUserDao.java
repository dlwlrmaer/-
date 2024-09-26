package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsUser;

public interface NewsUserDao {

    NewsUser findByUsername(String username);

    NewsUser findByUid(Integer uid);

    Integer insertNewsUser(NewsUser registUser);
}
