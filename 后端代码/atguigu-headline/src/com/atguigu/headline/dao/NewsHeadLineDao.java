package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadLineDao {

    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);
}
