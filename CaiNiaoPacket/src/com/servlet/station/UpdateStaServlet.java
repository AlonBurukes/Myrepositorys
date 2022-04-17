package com.servlet.station;
/**
 * update station     必须保证staId 唯一  keeperId是keeper表里的
 */

import com.dao.StationDao;
import com.dao.impl.StationDaoImpl;
import com.entity.Station;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet( "/station/UpdateStaServlet")
public class UpdateStaServlet extends HttpServlet {
    private StationDao stationdao= new StationDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        String staId = request.getParameter("staId");
        String staLoc=request.getParameter("staLoc");
        String keeperId =request.getParameter("keeperId");
        //int ID=Integer.parseInt(id);

        Station station=new Station();
        station.setStaId(staId);
        station.setStaLoc(staLoc);
        station.setKeeperId(keeperId);

        try {
            stationdao.update(station);
        } catch (SQLException throwables) {
            throwables.printStackTrace(); }

    }




    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        String staId = request.getParameter("staId");
        String staLoc=request.getParameter("staLoc");
        String keeperId =request.getParameter("keeperId");
        //int ID=Integer.parseInt(id);

        Station station=new Station();
        station.setStaId(staId);
        station.setStaLoc(staLoc);
        station.setKeeperId(keeperId);

        try {
            stationdao.update(station);
        } catch (SQLException throwables) {
            throwables.printStackTrace(); }


    }
}
