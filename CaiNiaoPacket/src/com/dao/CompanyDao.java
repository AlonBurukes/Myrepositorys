package com.dao;

import com.entity.Company;
import com.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDao {
    public List<Company> finAll() throws SQLException;

    public Company findByComId( int comId) throws SQLException;

    public Company findByComName(String comName) throws SQLException;

    public int insert(Company company) throws SQLException;

    public int update(Company company) throws SQLException;

    public int deleteByComId(int comId) throws SQLException;

}
