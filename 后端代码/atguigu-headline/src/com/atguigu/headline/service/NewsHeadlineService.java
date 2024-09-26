package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

/**
 * ClassName:NewsHeadlineService
 * Package:com.atguigu.headline.service
 * Description:
 *
 * @Author UESTC- ∑Ω‹¡È
 * @Create 2024/9/25 0:23
 * @Version 1.0
 */
public interface NewsHeadlineService {

    Map<String,Object> findPage(HeadlineQueryVo headlineQueryVo);

    HeadlineDetailVo findHeadlineDetail(Integer hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    NewsHeadline findHeadlineByHid(Integer hid);

    int updateNewsHeadline(NewsHeadline newsHeadline);

    int removeByHid(Integer hid);
}
