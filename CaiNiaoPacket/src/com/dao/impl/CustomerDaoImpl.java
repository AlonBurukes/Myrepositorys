package com.dao.impl;

import com.dao.CustomerDao;
import com.dao.KeeperDao;
import com.entity.Customer;

import com.entity.vo.CusParInfo;
import com.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());

    @Override
    public List<Customer> finAll() throws SQLException {
        String sql = "SELECT * FROM customer";
        List<Customer> list = qr.query(sql, new BeanListHandler<Customer>(Customer.class));
        return list;
    }

    @Override
    public Customer findByCusId(String cusId) throws SQLException {
        String sql = "SELECT * FROM `customer`\n" +
                "where cusId = '" + cusId + "';";

        return  qr.query(sql, new BeanHandler<Customer>(Customer.class));


    }

    @Override
    public Customer findByCusName(String cusName) throws SQLException {
        String sql = "SELECT * FROM `customer`\n" +
                "where cusName = '" + cusName + "';";

        return  qr.query(sql, new BeanHandler<Customer>(Customer.class));

    }

    @Override
    public int insert(Customer customer) throws SQLException {
        String sql = "insert into customer (cusId,cusName,cusPassword,cusLoc,cusTel,cunDate) values (?,?,?,?,?,?)";
        return qr.update(sql,customer.getCusId(),customer.getCusName(),customer.getCusPassWord(),customer.getCusLoc(),customer.getCusTel(),customer.getCunDate());
    }

    @Override
    public int update(Customer customer) throws SQLException {
        String sql = "update customer set cusName= ? ,cusPassword= ? ,cusLoc= ? ,cusTel= ? ,cunDate =? where cusId = ?";
        return qr.update(sql,customer.getCusName(),customer.getCusPassWord(),customer.getCusLoc(),customer.getCusTel(),customer.getCunDate(),customer.getCusId());
    }



    @Override
    public int deleteByCusId(String cusId) throws SQLException {
        String sql = "delete from customer where cusId =?";
        return qr.update(sql,cusId);
    }

    @Override
    public List<CusParInfo> finParById(String cusId) throws SQLException {
        String sql = "SELECT *\n" +
                "FROM\n" +
                "(select customer.cusId,cusName,parNum,parSum,parStart,staLoc,chaStyle,comName,keeperName,keeperTel\n" +
                "From customer,parcle,charges,company,keeper,station\n" +
                "where parcle.staId = station.staId\n" +
                "and station.keeperId = keeper.keeperId\n" +
                "and parcle.cusId = customer.cusId\n" +
                "and parcle.comId = company.comId\n" +
                "and parcle.chaId = charges.chaId) as a\n" +
                "where cusId = '" + cusId + "';";

        return  qr.query(sql, new BeanListHandler<CusParInfo>(CusParInfo.class));

    }

    @Override
    public List<CusParInfo> finParByName(String cusName) throws SQLException {
        String sql = "SELECT *\n" +
                "FROM\n" +
                "(select customer.cusId,cusName,parNum,parSum,parStart,staLoc,chaStyle,comName,keeperName,keeperTel\n" +
                "From customer,parcle,charges,company,keeper,station\n" +
                "where parcle.staId = station.staId\n" +
                "and station.keeperId = keeper.keeperId\n" +
                "and parcle.cusId = customer.cusId\n" +
                "and parcle.comId = company.comId\n" +
                "and parcle.chaId = charges.chaId) as a\n" +
                "where cusName = '" + cusName + "';";
        return  qr.query(sql, new BeanListHandler<CusParInfo>(CusParInfo.class));

    }
//test
   /* public static void main(String[] args) throws SQLException {
        CustomerDao customerDao = new CustomerDaoImpl();
       // System.out.println(customerDao.finParByName("Smith"));
        //System.out.println(customerDao.finParById("C00003"));
        //System.out.println(customerDao.findByCusId("C00003"));
       // System.out.println(customerDao.findByCusName("Smith"));
       // System.out.println(customerDao.finAll());
      *//*  Customer customer = new Customer();
        customer.setCunDate(100);
        customer.setCusId("466");
        customer.setCusLoc("agga");
        customer.setCusName("aghsh");
        customer.setCusPassWord("agggg");
        customer.setCusTel("14653");
        //customerDao.insert(customer);
        customerDao.update(customer);*//*
      customerDao.deleteByCusId("466");
    }*/
}
