package com.dao.impl;

import com.dao.KeeperDao;
import com.entity.Keeper;
import com.entity.vo.KepStaInfo;
import com.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class KeeperDaoImpl implements KeeperDao {
    QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());


    @Override
    public List<Keeper> finAll() throws SQLException {
        String sql = "select * from keeper";
        List<Keeper> list = qr.query(sql, new BeanListHandler<Keeper>(Keeper.class));
        return list;
    }

    @Override
    public Keeper findByKeeperId(String keeperId) throws SQLException {
        String sql = "select * from keeper where keeperId = '" + keeperId + "';";

        return  qr.query(sql, new BeanHandler<Keeper>(Keeper.class));
    }


    @Override
    public int insert(Keeper keeper) throws SQLException {
        String sql = "insert into keeper (keeperId,keeperName,keeperPassword,keeperTel,keeperSex) values (?,?,?,?,?)";
        return qr.update(sql,keeper.getKeeperId(),keeper.getKeeperName(),keeper.getKeeperPassword(),keeper.getKeeperTel(),keeper.getKeeperSex());

    }

    @Override
    public int update(Keeper keeper) throws SQLException {
        String sql = "update keeper set keeperName = ? , keeperPassword = ? , keeperTel = ? , keeperSex = ?  where keeperId = ?";
        return qr.update(sql,keeper.getKeeperName(),keeper.getKeeperPassword(),keeper.getKeeperTel(),keeper.getKeeperSex(),keeper.getKeeperId());
    }

    @Override
    public int deleteById(String keeperId) throws SQLException {
        String sql = "delete from keeper where keeperId =?";
        return qr.update(sql,keeperId);
    }

    @Override
    public List<KepStaInfo> finStaInfo(String keeperId) throws SQLException {
       String sql ="SELECT *\n" +
               "FROM\n" +
               "(SELECT keeper.keeperId, staId, staLoc\n" +
               "FROM keeper, station \n" +
               "WHERE keeper.keeperId = station.keeperId)as a \n" +
               "WHERE keeperId ='" + keeperId + "';";
        return qr.query(sql,new BeanListHandler<KepStaInfo>(KepStaInfo.class));
    }
    //test
   /* public static void main(String[] args) throws SQLException {
        KeeperDao keeperDao = new KeeperDaoImpl();
        //System.out.println(keeperDao.findByKeeperId("K00001"));
        //System.out.println(keeperDao.finAll());
        //System.out.println(keeperDao.finStaInfo("K00006"));
       *//* Keeper keeper = new Keeper();
        keeper.setKeeperId("K59456");
        keeper.setKeeperName("afg");
        keeper.setKeeperPassword("afgag");
        keeper.setKeeperSex("af");
        keeper.setKeeperTel("2");
       // keeperDao.insert(keeper);
        keeperDao.update(keeper);*//*
     //  keeperDao.deleteById("K59456");


    }*/
}
