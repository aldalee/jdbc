package com.msb.dao;

import com.msb.entity.Dept;

import java.util.List;

/**
 * @author HuanyuLee
 * @date 2022/3/8
 */
public interface DeptDao {
    /**
     * 查询全部门的方法
     * @return List<Dept> Dept对象封装的List集合
     */
    List<Dept> findAll();

    /**
     * 向数据库dept表中增加一条数据的方法
     * @param dept 要增加的数据封装成的Dept类的对象
     * @return int 增加成功返回大于 0的整数;增加失败返回 0
     */
    int addDept(Dept dept);
}
