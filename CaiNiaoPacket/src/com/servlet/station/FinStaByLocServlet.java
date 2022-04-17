package com.servlet.station;
/**
 * find station by staLoc
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

@WebServlet("/station/FinStaByLocServlet")
public class FinStaByLocServlet extends HttpServlet {
    private StationDao stationdao= new StationDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin","*");
        //允许跨域的请求方法GET,POST,HEAD等
        response.setHeader("Access-Control-Allow-Methods","*");
        //重新预检验跨域的缓存时间（s）
        response.setHeader("Access-Control-Max-Age","3600");
        //允许跨域的请求头
        response.setHeader("Access-Control-Allow-Headers","*");
        //是否携带cookie
        response.setHeader("Access-Control-Allow-Credentials","true");

        String staLoc =request.getParameter("staLoc");
        Station station =new Station();
        try {
            station =  stationdao.findByStaLoc(staLoc);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String jsonStr= JSON.toJSONString(station);
        PrintWriter out=response.getWriter();//响应对象的输出流

        out.print(jsonStr);//jsonStr通过管道发送出去
        out.flush();
        out.close();//一定记得关掉流

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin","*");
        //允许跨域的请求方法GET,POST,HEAD等
        response.setHeader("Access-Control-Allow-Methods","*");
        //重新预检验跨域的缓存时间（s）
        response.setHeader("Access-Control-Max-Age","3600");
        //允许跨域的请求头
        response.setHeader("Access-Control-Allow-Headers","*");
        //是否携带cookie
        response.setHeader("Access-Control-Allow-Credentials","true");

        String staLoc =request.getParameter("staLoc");
        Station station =new Station();
        try {
            station =  stationdao.findByStaLoc(staLoc);

            String jsonStr= JSON.toJSONString(station);
            PrintWriter out=response.getWriter();//响应对象的输出流

            out.print(jsonStr);//jsonStr通过管道发送出去
            out.flush();
            out.close();//一定记得关掉流
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
