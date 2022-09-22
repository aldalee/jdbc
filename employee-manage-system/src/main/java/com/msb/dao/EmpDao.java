package com.msb.dao;

import com.msb.entity.Emp;

import java.util.List;

/**
 *
 * @author HuanyuLee
 * @date 2022/3/8
 */
public interface EmpDao {

    /**
     * 向数据库emp表中增加一条数据的方法
     * @param emp 要增加的数据封装成的Emp类的对象
     * @return int 增加成功返回大于 0的整数;增加失败返回 0
     */
    int addEmp(Emp emp);

    /**
     * 根据员工编号删除员工信息的方法
     * @param empno 要删除的员工编号
     * @return int 删除成功返回大于 0的整数;增加失败返回 0
     */
    int deleteByEmpno(int empno);

    /**
     * 根据员工编号修改员工其他所有字段的方法
     * @param emp 员工编号和其他7个字段封装的一个Emp类对象
     * @return int 修改成功返回大于0的整数,失败返回0
     */
    int updateEmp(Emp emp);

    /**
     * 查看数据库表格中所有的员工信息
     * @return 所有员工信息封装的一个List<Emp>集合
     */
    List<Emp> findAll();
}
