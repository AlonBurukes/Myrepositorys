
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

@WebServlet( "/parcle/ParcleFindById")
public class ParcleFindById extends HttpServlet {
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
        String parId = request.getParameter("parId");

        try {
            Parcle parcle= parclerdao.findByParId(parId);
            String jsonStr = JSON.toJSONString(parcle);
            PrintWriter out = response.getWriter();//响应对象的输出流:管道
            out.print(jsonStr);//jsonStr通过管道发送出去
            out.flush();

//
//            System.out.println("ParcleFindById确认");
//            System.out.println(jsonStr);
//            System.out.println(parcle);

        } catch (Exception e) {
            e.printStackTrace();
           // System.out.println("异常");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String parId = request.getParameter("parId");

        try {
            Parcle parcle= parclerdao.findByParId(parId);
            String jsonStr = JSON.toJSONString(parcle);
            PrintWriter out = response.getWriter();//响应对象的输出流:管道
            out.print(jsonStr);//jsonStr通过管道发送出去
            out.flush();

//
//            System.out.println("ParcleFindById确认");
//            System.out.println(jsonStr);
//            System.out.println(parcle);

        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("异常");
        }
    }
}

