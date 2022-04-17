package com.dao.impl;

import com.dao.StationDao;
import com.entity.Station;
import com.entity.vo.StaKepInfo;
import com.entity.vo.StaParInfo;
import com.utils.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class StationDaoImpl implements StationDao {
    QueryRunner qr = new QueryRunner(JDBCUtil.getDataSource());

    @Override
    public List<Station> finAll() throws SQLException {
        String sql = "select * from station";
        List<Station> list = qr.query(sql, new BeanListHandler<Station>(Station.class));
        return list;
    }

    @Override
    public Station findByStaId(String staId) throws SQLException {
        String sql = "select * from station where staId = '"+ staId + "';";

        return  qr.query(sql, new BeanHandler<Station>(Station.class));


    }

    @Override
    public Station findByStaLoc(String staLoc) throws SQLException {
        String sql = "select * from station where staLoc = '"+ staLoc + "';";

        return  qr.query(sql, new BeanHandler<Station>(Station.class));

    }

    @Override
    public int insert(Station station) throws SQLException {
        String sql = "insert into station (staId,staLoc,keeperId) values (?,?,?)";
        return qr.update(sql, station.getStaId(), station.getStaLoc(), station.getKeeperId());
    }
    @Override
    public int update(Station station) throws SQLException {
            String sql = "update station set staLoc = ? ,keeperId = ? where staId = ?";
            return qr.update(sql,station.getStaLoc(),station.getKeeperId(),station.getStaId());
    }


    @Override
    public int delete(String staId) throws SQLException {
        String sql = "delete from station where staId =?";
        return qr.update(sql,staId);
    }

    @Override
    public List<StaParInfo> finParInfo(String staId) throws SQLException {
        String sql = "SELECT parId,parNum,parStart,comName,cusId\n" +
                "FROM  (select parId,station.staId,parNum,parStart,comName,cusId\n" +
                "FROM station, parcle,company\n" +
                "where station.staId = parcle.staId\n" +
                "and parcle.comId = company.comId) as a \n" +
                "where staId = '" + staId + "';";


        return  qr.query(sql,new BeanListHandler<StaParInfo>(StaParInfo.class));
    }

    @Override
    public StaKepInfo finKepInfo(String staId) throws SQLException {
        String sql = "SELECT *\n" +
                "FROM (SELECT station.staId, station.keeperId, keeperName, keeperSex, keeperTel\n" +
                "FROM station, keeper\n" +
                "where station.keeperId = keeper.keeperId)as a \n" +
                "WHERE staId = '" + staId + "';";
        return qr.query(sql,new BeanHandler<StaKepInfo>(StaKepInfo.class));
    }




//test
   /* public static void main(String[] args) throws SQLException {
        StationDao stationDao =new StationDaoImpl();
       *//* Station station = new Station();
        station.setKeeperId("K00003");
        station.setStaId("S009");
        station.setStaLoc("f55555g");*//*
        //System.out.println(stationDao.findByStaId("S00003"));
        //System.out.println(stationDao.findByStaLoc("xiamen"));
        //System.out.println(stationDao.finKepInfo("S00003"));
        //System.out.println(stationDao.finParInfo("S00003"));
       //stationDao.insert(station);

        //stationDao.update(station);
        stationDao.delete("S00099");
    }*/


}
