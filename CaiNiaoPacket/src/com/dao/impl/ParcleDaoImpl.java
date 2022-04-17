package com.dao.impl;

import com.dao.ParcleDao;
import com.entity.Parcle;
import com.entity.vo.ParDistrInfo;
import com.entity.vo.ParStaInfo;
import com.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ParcleDaoImpl implements ParcleDao {
    QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());
    @Override
    public List<Parcle> finAll() throws SQLException {
        String sql = "select* from parcle";
        return  qr.query(sql,new BeanListHandler<Parcle>(Parcle.class));
    }

    @Override
    public Parcle findByParId(String parId) throws SQLException {
        String sql = "select * from parcle where parId = '" + parId + "';";
        return qr.query(sql,new BeanHandler<Parcle>(Parcle.class));
    }

    @Override
    public int insert(Parcle parcle) throws SQLException {
       String sql = "insert into parcle (parId,parNum,parSum,chaId,staId,comId,parStart,cusId)values(?,?,?,?,?,?,?,?)";
       return qr.update(sql, parcle.getParId(), parcle.getParNum(), parcle.getParSum(), parcle.getChaId(), parcle.getStaId(), parcle.getComId(), parcle.getParStart(),parcle.getCusId());
    }

    @Override
    public int update(Parcle parcle) throws SQLException {
        String sql = "update parcle set parNum = ? , parSum = ?,chaId = ? , staId = ? , comId = ? , parStart = ? , cusId = ? where parId = ?";
        return qr.update(sql, parcle.getParNum(), parcle.getParSum(), parcle.getChaId(), parcle.getStaId(), parcle.getComId(), parcle.getParStart(), parcle.getCusId(),parcle.getParId());
    }

    @Override
    public int delete(String parId) throws SQLException {
        String sql = "delete from parcle where parId = ?";
        return qr.update(sql,parId);
    }

    @Override
    public ParDistrInfo finDistrById(String parId) throws SQLException {
        String sql = "select*\n" +
                "FROM(SELECT parId, parStart,staLoc,comName,chaStyle\n" +
                "FROM parcle, station,company,charges\n" +
                "where parcle.staId = station.staId\n" +
                "and parcle.comId = company.comId\n" +
                "and parcle.chaId = charges.chaId) as a\n" +
                "WHERE parId = '" + parId +"';";
        return qr.query(sql,new BeanHandler<ParDistrInfo>(ParDistrInfo.class));
    }

    @Override
    public ParStaInfo finStaInfo(String parId) throws SQLException {
       String sql = "select*\n" +
               "from (select parId,parcle.staId,staLoc,keeperId\n" +
               "from parcle,station\n" +
               "where parcle.staId = station.staId)as a\n" +
               "where parId = '" + parId + "';";
        return qr.query(sql,new BeanHandler<ParStaInfo>(ParStaInfo.class));
    }
//test
   /* public static void main(String[] args) throws SQLException {
        ParcleDao parcleDao = new ParcleDaoImpl();
       // System.out.println(parcleDao.findByParId("P00001"));
        //System.out.println(parcleDao.finDistrById("P00001"));
        //System.out.println(parcleDao.finStaInfo("P00001"));
       *//* //System.out.println(parcleDao.finAll());
        Parcle parcle = new Parcle();
        parcle.setChaId(1);
        parcle.setComId(3);
        parcle.setCusId("C00003");
        parcle.setParId("664");
        parcle.setParNum(12);
        parcle.setStaId("S00001");
        parcle.setParStart("sg");
        parcle.setParSum(44445);
        //parcleDao.insert(parcle);
        parcleDao.update(parcle);*//*
       parcleDao.delete("664");
    }*/
}
