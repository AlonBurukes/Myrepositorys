package com.dao;

import com.entity.Parcle;
import com.entity.vo.ParDistrInfo;
import com.entity.vo.ParStaInfo;

import java.sql.SQLException;
import java.util.List;

/**包裹管理：
 *   1.对包裹信息的增删改查
 *   2.新增查询:通过包裹Id
 *          查包裹的物流信息/驿站信息
 *
 */
public interface ParcleDao {
    /**
     * 查询所有包裹
     * @return
     * @throws SQLException
     */
    public List<Parcle> finAll() throws SQLException;

    /**
     * 通过包裹id查包裹信息
     * @param parId
     * @return
     * @throws SQLException
     */
    public Parcle findByParId(String parId) throws SQLException;

    /**
     * 注意！！外键原因，insert/update 时 （插入 /更新)时，
     *          必须保证  chaId:收费id，comId：物流公司Id,  cusId:客户Id, staId:驿站Id
     *          与数据库中的charges表，company表，customer表， station表相对应的一致
     *  且需明确parId 具有唯一性
     *
     *  update时是以id作为参照，未使有效，需更新的数据中parId是已存在的
     * @param parcle
     * @return
     * @throws SQLException
     */
    public int insert(Parcle parcle) throws SQLException;
    public int update(Parcle parcle) throws SQLException;

    /**
     * 通过传入parId删除记录
     * @param parId
     * @return
     * @throws SQLException
     */
    public int delete(String parId) throws SQLException;

    /**
     * 查询该包裹的物流信息
     * @param parId
     * @return
     * @throws SQLException
     */
    public ParDistrInfo finDistrById(String parId) throws SQLException;

    /**
     * 查询包裹所在驿站的详细信息
     * @param parId
     * @return
     * @throws SQLException
     */
    public ParStaInfo finStaInfo(String parId)throws SQLException;

}
