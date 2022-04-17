package com.dao;


import com.entity.Customer;
import com.entity.vo.CusParInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * 1.实现客户信息的增删改查
 * 2.新增方法：顾客根据id或者姓名 查询包裹情况
 *
 */

public interface CustomerDao {

    /**
     *
     * 查询顾客customer的所有信息
     *
     * @return list<> 即Customer类型的List
     */
    public List<Customer> finAll() throws SQLException;

    /**
     *根据customer Id  查询顾客信息
     * @param cusId
     * @return Empno行记录
     */
    public Customer findByCusId(String cusId) throws SQLException;


    /**
     * 按姓名查询
     * @param cusName
     * @return
     * @throws SQLException
     */
    public Customer findByCusName(String cusName) throws SQLException;


    /**
     * 对customer的信息进行增加
     * @param customer
     * @return 受影响的行数
     * @throws SQLException
     */
    public int insert(Customer customer) throws SQLException;

    /**
     * 更新客户信息
     * @param customer
     * @return受影响函数
     * @throws SQLException
     */
    public int update(Customer customer) throws SQLException;

    /**
     * 根据cusId对客户信息进行删除
     * @param cusId
     * @return
     * @throws SQLException
     */
    public int deleteByCusId(String cusId) throws SQLException;

    /**
     * 通过cusId 查询该客户的包裹信息
     * @param cusId
     * @return 顾客的包裹信息表
     * @throws SQLException
     */
    public List<CusParInfo> finParById(String cusId) throws SQLException;

    /**
     * 通过姓名cusName 查询该客户的包裹信息
     * @param cusName
     * @return 顾客的包裹信息表
     * @throws SQLException
     */
    public List<CusParInfo> finParByName(String cusName) throws SQLException;





}

