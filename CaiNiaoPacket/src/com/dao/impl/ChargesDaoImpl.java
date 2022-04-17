package com.dao.impl;

import com.dao.ChargesDao;
import com.entity.Charges;
import com.entity.Company;
import com.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ChargesDaoImpl implements ChargesDao {
    QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());

    @Override
    public List<Charges> finAll() throws SQLException {
        String sql = "select* from charges";
        return qr.query(sql,new BeanListHandler<Charges>(Charges.class)) ;
    }

    @Override
    public Charges findByChaId(int chaId) throws SQLException {
        String sql = "select* from charges where chaId = " + chaId;
        return qr.query(sql,new BeanHandler<Charges>(Charges.class));
    }

    @Override
    public Charges findByChaStyle(String chaStyle) throws SQLException {
        String sql = "select* from charges where chaStyle = '" + chaStyle + "';";
        return qr.query(sql,new BeanHandler<Charges>(Charges.class));
    }

    @Override
    public int insert(Charges charges) throws SQLException {
        String sql = "insert into charges(chaId,chaStyle,chaFees)values(?,?,?)";
        return qr.update(sql,charges.getChaId(),charges.getChaStyle(),charges.getChaFees());
    }

    @Override
    public int update(Charges charges) throws SQLException {
        String sql = "update charges set chaStyle = ?,chaFees = ? where chaId = ? ";
        return qr.update(sql,charges.getChaStyle(),charges.getChaFees(),charges.getChaId());
    }

    @Override
    public int deleteByChaId(int chaId) throws SQLException {
        String sql  = "delete from charges where chaId = ? ";
        return qr.update(sql,chaId);
    }
//test
  /*  public static void main(String[] args) throws SQLException {
        ChargesDao chargesDao = new ChargesDaoImpl();
       // System.out.println(chargesDao.findByChaId(1));
        //System.out.println(chargesDao.findByChaStyle("simple"));
        Charges charges = new Charges();
        charges.setChaFees(44);
        charges.setChaStyle("sfa");
        charges.setChaId(5);
       // chargesDao.insert(charges);
        //chargesDao.update(charges);
        chargesDao.deleteByChaId(5);
    }*/
}
