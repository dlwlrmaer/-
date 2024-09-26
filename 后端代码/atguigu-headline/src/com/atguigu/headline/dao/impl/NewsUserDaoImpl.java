package com.atguigu.headline.dao.impl;
import com.atguigu.headline.dao.BaseDao;
import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao{

    @Override
    public NewsUser findByUsername(String username) {
        // ׼��SQL
        String sql ="select uid,username,user_pwd userPwd ,nick_name nickName from news_user where username = ?";
        // ����BaseDao������ѯ����
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, username);
        // ����ҵ�,���ؼ����еĵ�һ������(��ʵ��һ��)
        if (null != newsUserList && newsUserList.size()>0){
            return  newsUserList.get(0);
        }
        return null;
    }

    @Override
    public NewsUser findByUid(Integer uid) {
        String sql = """
                select
                    uid,
                    username,
                    user_pwd userPwd,
                    nick_name nickName
                from
                    news_user
                where
                    uid = ?
                """;
        List<NewsUser> newsUserList = baseQuery(NewsUser.class,sql,uid);
        if (null != newsUserList && newsUserList.size() > 0){
            return  newsUserList.get(0);
        }
        return null;
    }

    @Override
    public Integer insertNewsUser(NewsUser newsUser) {
        String sql ="insert into news_user values(DEFAULT,?,?,?)";
        return baseUpdate(sql,newsUser.getUsername(),newsUser.getUserPwd(),newsUser.getNickName());
    }
}
