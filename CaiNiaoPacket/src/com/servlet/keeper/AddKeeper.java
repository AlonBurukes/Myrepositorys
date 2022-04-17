package com.servlet.keeper;

import com.dao.KeeperDao;
import com.dao.impl.KeeperDaoImpl;
import com.entity.Keeper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/keeper/AddKeeper")
public class AddKeeper extends HttpServlet {
    private KeeperDao keeperDao = new KeeperDaoImpl();
    private Keeper keeper = new Keeper();
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

        String keeperId = request.getParameter("keeperId");
        String keeperName = request.getParameter ("keeperName");
        String keeperPassword = request.getParameter ("keeperPassword");
        String keeperTel = request.getParameter("keeperTel");
        String keeperSex = request.getParameter ("keeperSex");

       keeper.setKeeperId(keeperId);
       keeper.setKeeperName(keeperName);
       keeper.setKeeperPassword(keeperPassword);
       keeper.setKeeperTel(keeperTel);
       keeper.setKeeperSex(keeperSex);

        try {
            keeperDao.insert(keeper);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
