package com.atguigu.headline.service.impl;
import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.dao.impl.NewsUserDaoImpl;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.NewsUserService;
public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao newsUserDao = new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {
        return newsUserDao.findByUsername(username);
    }
}
