package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsType;

import java.util.List;

/**
 * ClassName:NewsTypeService
 * Package:com.atguigu.headline.service
 * Description:
 *
 * @Author UESTC-ʷ����
 * @Create 2024/9/25 0:23
 * @Version 1.0
 */
public interface NewsTypeService {
    /**
     * ��ѯ����ͷ�����͵ķ���
     * @return  ���ͷ��������List
     */
    List<NewsType> findAll();
}
