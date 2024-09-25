package com.atguigu.headline.controller;
import com.atguigu.headline.common.Result;
import com.atguigu.headline.pojo.NewsType;
import com.atguigu.headline.service.NewsTypeService;
import com.atguigu.headline.service.impl.NewsTypeServiceImpl;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/portal/*")
public class PortalController extends BaseController{
    private NewsTypeService typeService = new NewsTypeServiceImpl();

    /**
     * ��ѯ����ͷ�����͵�ҵ��ӿ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  ��ѯ���е��������ͣ�װ��Result��Ӧ���ͻ���
        List<NewsType> newsTypeList = typeService.findAll();

        WebUtil.writeJson(resp,Result.ok(newsTypeList));
    }
}