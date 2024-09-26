package com.atguigu.headline.service.impl;
import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.dao.impl.NewsUserDaoImpl;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.NewsUserService;
import com.atguigu.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao newsUserDao = new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {
        return newsUserDao.findByUsername(username);
    }

    @Override
    public Integer registUser(NewsUser registUser) {
        registUser.setUserPwd(MD5Util.encrypt(registUser.getUserPwd()));
        return newsUserDao.insertNewsUser(registUser);
    }

    @Override
    public NewsUser findByUid(Integer uid) {
        return newsUserDao.findByUid(uid);
    }
}
