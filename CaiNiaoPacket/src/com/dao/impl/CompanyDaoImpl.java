package com.dao.impl;

import com.dao.CompanyDao;
import com.entity.Company;
import com.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());

    @Override
    public List<Company> finAll() throws SQLException {
        String sql = "select* from company";
        return qr.query(sql,new BeanListHandler<Company>(Company.class)) ;
    }

    @Override
    public Company findByComId(int comId) throws SQLException {
        String sql = "select* from company where comId = " + comId;
        return qr.query(sql,new BeanHandler<Company>(Company.class));
    }

    @Override
    public Company findByComName(String comName) throws SQLException {
        String sql = "select* from company where comName = '" + comName + "';";
        return qr.query(sql,new BeanHandler<Company>(Company.class));
    }

    @Override
    public int insert(Company company) throws SQLException {
        String sql = "insert into company(comId,comName)values(?,?)";
        return qr.update(sql,company.getComId(),company.getComName());
    }

    @Override
    public int update(Company company) throws SQLException {
       String sql = "update company set comName = ? where comId = ? ";
        return qr.update(sql,company.getComName(),company.getComId());
    }

    @Override
    public int deleteByComId(int comId) throws SQLException {
       String sql  = "delete from company where comId = ? ";
        return qr.update(sql,comId);
    }
//test
    /*public static void main(String[] args) throws SQLException {
        CompanyDao companyDao= new CompanyDaoImpl();
       // System.out.println(companyDao.finAll());
        //System.out.println(companyDao.findByComId(1));
        //System.out.println(companyDao.findByComName("shunfeng"));
       *//* Company company = new Company();
        company.setComId(100);
        company.setComName("agah");
       // companyDao.insert(company);
        companyDao.update(company);*//*
       companyDao.deleteByComId(100);
    }*/
}
