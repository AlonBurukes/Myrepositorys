package com.dao;

import com.entity.Charges;

import java.sql.SQLException;
import java.util.List;

public interface ChargesDao {
    public List<Charges> finAll() throws SQLException;

    public Charges findByChaId( int chaId) throws SQLException;

    public Charges findByChaStyle(String chaStyle) throws SQLException;

    public int insert(Charges charges) throws SQLException;
    //Update的时候要保证chaId是已经存在的才有效
    public int update(Charges charges) throws SQLException;

    public int deleteByChaId(int chaId) throws SQLException;
}
