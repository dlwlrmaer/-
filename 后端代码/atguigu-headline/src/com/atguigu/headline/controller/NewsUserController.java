package com.atguigu.headline.controller;
import com.atguigu.headline.common.Result;
import com.atguigu.headline.common.ResultCodeEnum;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.service.NewsHeadlineService;
import com.atguigu.headline.service.NewsTypeService;
import com.atguigu.headline.service.NewsUserService;
import com.atguigu.headline.service.impl.NewsHeadlineServiceImpl;
import com.atguigu.headline.service.impl.NewsTypeServiceImpl;
import com.atguigu.headline.service.impl.NewsUserServiceImpl;
import com.atguigu.headline.util.JwtHelper;
import com.atguigu.headline.util.MD5Util;
import com.atguigu.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController{
    private NewsUserService userService= new NewsUserServiceImpl();
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();
    private NewsTypeService newsTypeService = new NewsTypeServiceImpl();
    /**
     * ���ע���ҵ��ӿ�
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser registUser = WebUtil.readJson(req, NewsUser.class);

        NewsUser usedUser = userService.findByUsername(registUser.getUsername());

        Result result = null;

        if (null == usedUser){
            userService.registUser(registUser);
            result = Result.ok(null);
        }else {
            result = Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     *  �����û����Ƿ�ռ�õ�ҵ��ӿ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        NewsUser newsUser = userService.findByUsername(username);
        Result result = Result.ok(null);
        if (null!= newsUser){
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }else{
            result = Result.build(null,ResultCodeEnum.SUCCESS);
        }
        WebUtil.writeJson(resp,result);
    }

    /**
     * �����¼���ύ��ҵ��ӿڵ�ʵ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  �����û���������
        /*{
            "username":"zhangsan", //�û���
            "userPwd":"123456"     //��������
          }*/
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);

        //  ���÷��񴦷��� ʵ�ֵ�¼
        NewsUser loginUser = userService.findByUsername(paramUser.getUsername());
        Result result = null;
        if (null != loginUser){
            if(MD5Util.encrypt(paramUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())){
                Integer uid = loginUser.getUid();
                String token = JwtHelper.createToken(uid.longValue());
                Map data = new HashMap();
                data.put("token",token);
                result = Result.ok(data);
            }else{
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }

        }else {
            result = Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }

        //  ��ͻ�����Ӧ��¼��֤��Ϣ
        WebUtil.writeJson(resp,result);
    }

    /**
     * ����token������token��ѯ�����û���Ϣ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if (null != token){
            if (!JwtHelper.isExpiration(token)){
                Integer uid = JwtHelper.getUserId(token).intValue();
                NewsUser newsUser = userService.findByUid(uid);
                newsUser.setUserPwd("");
                Map<String,Object> data = new HashMap<>();
                data.put("loginUser",newsUser);
                result = Result.ok(data);
            }
        }
        WebUtil.writeJson(resp,result);
    }


    /**
     * ͨ��token�����û���¼�Ƿ����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);

        if (null != token){
            if(!JwtHelper.isExpiration(token)){
                result = Result.ok(null);
            }
        }
        WebUtil.writeJson(resp,result);
    }
}