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
        // ׼��һ��map,����װ��ҳ����������
        Map<String,Object> pageInfo =new HashMap<>();
        // ��ҳ��ѯ��ҳ����
        List<HeadlinePageVo>  pageData = headLineDao.findPageList(headLineQueryVo);
        // ��ҳ��ѯ�����¼����������
        int totalSize = headLineDao.findPageCount(headLineQueryVo);
        // ҳ��С
        int pageSize =headLineQueryVo.getPageSize();
        // ��ҳ����
        int totalPage=totalSize%pageSize == 0 ?  totalSize/pageSize  : totalSize/pageSize+1;
        // ��ǰҳ����
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
