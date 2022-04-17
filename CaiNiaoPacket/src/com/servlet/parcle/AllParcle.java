
package com.servlet.parcle;

import com.alibaba.fastjson.JSON;
import com.dao.ParcleDao;
import com.dao.impl.ParcleDaoImpl;
import com.entity.Parcle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet( "/parcle/AllParcle")
public class AllParcle extends HttpServlet {
    //找dao要数据
    private ParcleDao parclerdao = new ParcleDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        response.setHeader("Access-Control-Allow-Credentials", "true");


        //postman
        try {
            //调用dao获得数据
            List<Parcle> list = parclerdao.finAll();

            //把JSON数据作为流返回给界面
            String jsonStr = JSON.toJSONString(list);
            PrintWriter out = response.getWriter();//响应对象的输出流:管道
            out.print(jsonStr);//jsonStr通过管道发送出去
            //System.out.println("AllParcle确认");
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text\\html;charset=UTF-8");
        response.setContentType("text\\html,charset=UTF-8");

        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        response.setHeader("Access-Control-Allow-Credentials", "true");


        //postman
        try {
            //调用dao获得数据
            List<Parcle> list = parclerdao.finAll();

            //把JSON数据作为流返回给界面
            String jsonStr = JSON.toJSONString(list);
            PrintWriter out = response.getWriter();//响应对象的输出流:管道
            out.print(jsonStr);//jsonStr通过管道发送出去
            //System.out.println("AllParcle确认");
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

