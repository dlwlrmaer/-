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
     * 完成注册的业务接口
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
     *  检验用户名是否被占用的业务接口实现
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
     * 处理登录表单提交的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  接收用户名和密码
        /*{
            "username":"zhangsan", //用户名
            "userPwd":"123456"     //明文密码
          }*/
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);

        //  调用服务处方法 实现登录
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

        //  向客户端响应登录验证信息
        WebUtil.writeJson(resp,result);
    }
}