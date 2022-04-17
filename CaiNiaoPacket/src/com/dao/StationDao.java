package com.dao;

import com.entity.Station;
import com.entity.vo.StaKepInfo;
import com.entity.vo.StaParInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * 驿站信息的增删改查 查询包括由  驿站id/驿站地点 查驿站信息
 * 新增方法：通过驿站id : 实现对该驿站包裹信息的查询
 *                      实现对该驿站管理员的详细信息查询
 *
 */
public interface StationDao {

    /**
     * 查询所有驿站
     * @return 驿站表
     * @throws SQLException
     */
    public List<Station> finAll() throws SQLException;

    /**
     * 通过驿站id查驿站信息
     * @param staId
     * @return
     * @throws SQLException
     */

    /**
     * 通过驿站id查询驿站
     * @param staId
     * @return
     * @throws SQLException
     */
    public Station findByStaId(String staId) throws SQLException;

    /**
     * 通过驿站地点查询驿站
     * @param staLoc
     * @return
     * @throws SQLException
     */
    public Station findByStaLoc(String staLoc) throws SQLException;

    /**
     * insert的时候要注意！！！ station表里的keeperId和keeper表里的keeperId外键关联了，
     *                  所以，插入时必须保证插入的keeperId是keeper表里已经存在的keeperId，不然会报错
     *                  staId :station Id  要保证唯一性， 与数据库中已存在的一样，那么会报错
     *          建议：插入时设置选择框从已有keepId里选择
     * @param station
     * @return
     * @throws SQLException
     */
    public int insert(Station station) throws SQLException;

    /**
     * 注意！！update时只有staId是匹配的，才是有效更新
     * 建议：或许可以给出station Id是选择 但不必须，因为station Id不一样 也可以顺利执行方法，虽然没结果
     * @param station
     * @return
     * @throws SQLException
     */
    public int update(Station station) throws SQLException;

    public int delete(String staId) throws SQLException;

    /**
     * 通过驿站的id查询驿站有什么包裹
     * @param staId
     * @return 该驿站的包裹信息表
     * @throws SQLException
     */
    public List<StaParInfo> finParInfo(String staId) throws SQLException;

    /**
     * 通过驿站的id查询驿站管理员的详细信息
     * @param staId
     * @return
     * @throws SQLException
     */
    public StaKepInfo finKepInfo(String staId) throws SQLException;






}
