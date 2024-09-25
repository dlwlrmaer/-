package com.atguigu.headline.service.impl;
import com.atguigu.headline.dao.NewsHeadLineDao;
import com.atguigu.headline.dao.impl.NewsHeadlineDaoImpl;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import com.atguigu.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl  implements NewsHeadlineService {
    private NewsHeadLineDao headLineDao = new NewsHeadlineDaoImpl();
    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum = headlineQueryVo.getPageNum();
        int pageSize = headlineQueryVo.getPageSize();

        List<HeadlinePageVo> pageData = headLineDao.findPageList(headlineQueryVo);
        int totalSize = headLineDao.findPageCount(headlineQueryVo);

        int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;

        Map pageInfo = new HashMap();

        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalSize",totalSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("pageData",pageData);

        return pageInfo;
    }
}
