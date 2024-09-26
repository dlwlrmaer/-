package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;
import java.util.Map;

public interface NewsHeadLineDao {

    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    int findPageCount(HeadlineQueryVo headlineQueryVo);

    int increasePageViews(Integer hid);

    HeadlineDetailVo findHeadlineDetail(Integer hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    NewsHeadline findHeadlineByHid(Integer hid);

    int updateNewsHeadline(NewsHeadline newsHeadline);

    int removeByHid(Integer hid);
}
