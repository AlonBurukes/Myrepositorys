package com.servlet.parcle;

import com.dao.ParcleDao;
import com.dao.impl.ParcleDaoImpl;
import com.entity.Parcle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/parcle/AddParcle")
public class AddParcle extends HttpServlet {
    private ParcleDao parcleDao = new ParcleDaoImpl();
    private Parcle parcle = new Parcle();
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

        String parId = request.getParameter("parId");
        int parNum = Integer.parseInt(request.getParameter("parNum"));
        double parSum = Double.parseDouble(request.getParameter ("parSum"));
        int chaId = Integer.parseInt(request.getParameter("chaId"));
        String staId = request.getParameter("staId");
        int comId = Integer.parseInt(request.getParameter("comId"));
        String parStart = request.getParameter("parStart");
        String cusId = request.getParameter("cusId");

        parcle.setParId(parId);
        parcle.setParNum(parNum);
        parcle.setParSum(parSum);
        parcle.setChaId(chaId);
        parcle.setStaId(staId);
        parcle.setComId(comId);
        parcle.setParStart(parStart);
        parcle.setCusId(cusId);



        try {
            parcleDao.insert(parcle);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
