package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsUser;

/**
 * ClassName:NewsUserService
 * Package:com.atguigu.headline.service
 * Description:
 *
 * @Author UESTC-ʷ����
 * @Create 2024/9/25 0:24
 * @Version 1.0
 */
public interface NewsUserService {
    /**
     * �����û���¼���˺����û��µķ���
     * @param username  �û�������˻�
     * @return  �ҵ�����NewsUser�����Ҳ�������null
     */
    NewsUser findByUsername(String username);

    Integer registUser(NewsUser registUser);
}
