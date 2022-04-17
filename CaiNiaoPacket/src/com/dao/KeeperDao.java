package com.dao;

import com.entity.Keeper;
import com.entity.vo.KepStaInfo;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * 实现店主，即驿站管理者 信息的增删改查:驿站管理者的个人信息
 * 查询该驿站管理员所管辖的驿站
 *
 */
public interface KeeperDao {
    /**
     * 查询所有驿站管理者的信息
     * @return
     * @throws SQLException
     */
    public List<Keeper> finAll() throws SQLException;

    /**
     * 通过keeprId查找驿站管理者信息
     * @param keeperId
     * @return
     * @throws SQLException
     */
    public Keeper findByKeeperId(String keeperId) throws SQLException;

    //插入信息
    public int insert(Keeper keeper) throws SQLException;
    //更新信息
    public int update(Keeper keeper) throws SQLException;

    /**
     * 删除指定 keeper Id 的驿站管理者信息
     * @param keeperId
     * @return
     * @throws SQLException
     */
    public int deleteById(String keeperId) throws SQLException;

    /**
     * 查找该keeper管理的驿站信息
     * @param keeperId
     * @return
     * @throws SQLException
     */
    public List<KepStaInfo> finStaInfo(String keeperId)throws SQLException;


}
