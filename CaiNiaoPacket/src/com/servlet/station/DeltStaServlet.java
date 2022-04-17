package com.servlet.station;
/**
 * delete station by staId (staId should  be existed)
 */

import com.dao.StationDao;
import com.dao.impl.StationDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/station/DeltStaServlet")
public class DeltStaServlet extends HttpServlet {
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

        String Id=request.getParameter("staId");
           //  int ID=Integer.parseInt(id);
           // System.out.println(Id);
        try {
            stationdao.delete(Id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* //允许跨域的主机地址
        response.setHeader("Access-Control-Allow-Origin","*");
        //允许跨域的请求方法GET,POST,HEAD等
        response.setHeader("Access-Control-Allow-Methods","*");
        //重新预检验跨域的缓存时间（s）
        response.setHeader("Access-Control-Max-Age","3600");
        //允许跨域的请求头
        response.setHeader("Access-Control-Allow-Headers","*");
        //是否携带cookie
        response.setHeader("Access-Control-Allow-Credentials","true");

        String Id=request.getParameter("staId");
            //test
           // System.out.println(Id);
        try {
            stationdao.delete(Id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
*/



    }
}
