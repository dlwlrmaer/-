package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsType;

import java.util.List;

/**
 * ClassName:NewsTypeService
 * Package:com.atguigu.headline.service
 * Description:
 *
 * @Author UESTC-史杰灵
 * @Create 2024/9/25 0:23
 * @Version 1.0
 */
public interface NewsTypeService {
    /**
     * 查询所有头条类型的方法
     * @return  多个头条类型以List
     */
    List<NewsType> findAll();
}
