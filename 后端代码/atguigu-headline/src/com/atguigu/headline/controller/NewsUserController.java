package com.atguigu.headline.controller;
import com.atguigu.headline.common.Result;
import com.atguigu.headline.common.ResultCodeEnum;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.NewsUserService;
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

    /**
     * ���ע���ҵ��ӿ�
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser registUser = WebUtil.readJson(req, NewsUser.class);

        Integer rows = userService.registUser(registUser);


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
}