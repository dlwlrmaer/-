package com.atguigu.headline.dao.impl;
import com.atguigu.headline.dao.BaseDao;
import com.atguigu.headline.dao.NewsHeadLineDao;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadLineDao{

    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql = """
                select
                    hidm,
                    title,
                    type,
                    page_views pageViews,
                    TIMESTAMPDIFF(HOUR,create_time,now()) pastHours,
                    publisher
                from 
                    news_headline
                where
                    is_deleted = 0
                   
                """;
        if (headlineQueryVo.getType() != 0){
            sql = sql.concat(" and type = ? ");
            params.add(headlineQueryVo.getType());
        }
        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().equals("")){
            sql = sql.concat(" and title like ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");
        }

        sql = sql.concat(" order by pastHours ASC , page_views DESC");
        
        return null;
    }
}
