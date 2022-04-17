package com.servlet.customer;

import com.dao.CustomerDao;
import com.dao.impl.CustomerDaoImpl;
import com.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/customer/AddCusServlet")
public class AddServlet extends HttpServlet {
    private CustomerDao customerDao = new CustomerDaoImpl();
    Customer customer = new Customer();

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

        String cusId = request.getParameter("cusId");
        String cusName = request.getParameter ("cusName");
        String cusPassword = request.getParameter ("cusPassword");
        String cusLoc = request.getParameter("cusLoc");
        String cusTel = request.getParameter("cusTel");
        int cunDate = Integer.parseInt(request.getParameter("cunDate"));

        customer.setCusId(cusId);
        customer.setCusName(cusName);
        customer.setCusPassWord(cusPassword);
        customer.setCusLoc(cusLoc);;
        customer.setCusTel(cusTel);
        customer.setCunDate(cunDate);


        try {
            customerDao.insert(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
