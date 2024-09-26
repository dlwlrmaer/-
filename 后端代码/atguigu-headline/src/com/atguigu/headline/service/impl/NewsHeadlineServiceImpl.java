package com.atguigu.headline.service.impl;
import com.atguigu.headline.dao.NewsHeadLineDao;
import com.atguigu.headline.dao.impl.NewsHeadlineDaoImpl;
import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import com.atguigu.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl  implements NewsHeadlineService {
    private NewsHeadLineDao headLineDao = new NewsHeadlineDaoImpl();
    @Override
    public Map<String, Object> findPage(HeadlineQueryVo headLineQueryVo) {
        // 准备一个map,用于装分页的五项数据
        Map<String,Object> pageInfo =new HashMap<>();
        // 分页查询本页数据
        List<HeadlinePageVo>  pageData = headLineDao.findPageList(headLineQueryVo);
        // 分页查询满足记录的总数据量
        int totalSize = headLineDao.findPageCount(headLineQueryVo);
        // 页大小
        int pageSize =headLineQueryVo.getPageSize();
        // 总页码数
        int totalPage=totalSize%pageSize == 0 ?  totalSize/pageSize  : totalSize/pageSize+1;
        // 当前页码数
        int pageNum= headLineQueryVo.getPageNum();
        pageInfo.put("pageData",pageData);
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("totalSize",totalSize);


        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        headLineDao.increasePageViews(hid);
        return headLineDao.findHeadlineDetail(hid);
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        return headLineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public NewsHeadline findHeadlineByHid(Integer hid) {
        return headLineDao.findHeadlineByHid(hid);
    }

    @Override
    public int updateNewsHeadline(NewsHeadline newsHeadline) {
        return headLineDao.updateNewsHeadline(newsHeadline);
    }

    @Override
    public int removeByHid(Integer hid) {
        return headLineDao.removeByHid(hid);
    }
}
