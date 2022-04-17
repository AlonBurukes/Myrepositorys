package com.servlet.station;
/**
 * show all station
 */

import com.alibaba.fastjson.JSON;
import com.dao.StationDao;
import com.dao.impl.StationDaoImpl;
import com.entity.Station;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet( "/station/ShowStaServlet")
public class StationServlet extends HttpServlet {
    private StationDao stationdao= new StationDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type","text\\html;charset=UTF-8");
        response.setContentType("text\\html,charset=UTF-8");

        //允许跨域的主机地址
        response.setHeader("Access-Control-Allow-Origin","*");
        //允许跨域的请求方法GET,POST,HEAD等
        response.setHeader("Access-Control-Allow-Methods","*");
        //重新预检验跨域的缓存时间（s）
        response.setHeader("Access-Control-Max-Age","3600");
        //允许跨域的请求头
        response.setHeader("Access-Control-Allow-Headers","*");
        //是否携带cookie
        response.setHeader("Access-Control-Allow-Credentials","true");

        List<Station> list= null;
        try {
            list = stationdao.finAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
                        //System.out.println(list);
        String jsonStr= JSON.toJSONString(list);
        PrintWriter out=response.getWriter();//响应对象的输出流

        out.print(jsonStr);//jsonStr通过管道发送出去
        out.flush();
        out.close();//一定记得关掉流
    }

}
