package com.atguigu.headline.controller;
import com.atguigu.headline.common.Result;
import com.atguigu.headline.pojo.NewsType;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import com.atguigu.headline.service.NewsHeadlineService;
import com.atguigu.headline.service.NewsTypeService;
import com.atguigu.headline.service.impl.NewsHeadlineServiceImpl;
import com.atguigu.headline.service.impl.NewsTypeServiceImpl;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/portal/*")
public class PortalController extends BaseController{
    private NewsTypeService typeService = new NewsTypeServiceImpl();
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();
    /**
     * 分页查询头条信息的接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  接收请求的参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);

        //  将参数传递给服务层 进行分页查询
        Map pageInfo = headlineService.findPage(headlineQueryVo);
        Map data = new HashMap();
        data.put("pageInfo", pageInfo);

        //  将分页查询的结果转换为json响应给客户端
        WebUtil.writeJson(resp,Result.ok(data));
    }

    /**
     * 查询所有头条类型的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  查询所有的新闻类型，装入Result响应给客户端
        List<NewsType> newsTypeList = typeService.findAll();

        WebUtil.writeJson(resp,Result.ok(newsTypeList));
    }
}