package com.msb.dao.impl;

import com.msb.dao.BaseDao;
import com.msb.dao.EmpDao;
import com.msb.entity.Emp;

import java.util.List;

/**
 * @author HuanyuLee
 * @date 2022/3/8
 */
public class EmpDaoImpl extends BaseDao implements EmpDao {

    @Override
    public int addEmp(Emp emp) {
        String sql = "insert into emp values(default,?,?,?,?,?,?,?)";
        return baseUpdate(sql,emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getComm(),emp.getDeptno());
    }

    @Override
    public int deleteByEmpno(int empno) {
        String sql = "delete from emp where empno = ?";
        return baseUpdate(sql,empno);
    }

    @Override
    public int updateEmp(Emp emp) {
        String sql="update emp set ename = ?,job = ?,mgr = ?,hiredate = ?,sal = ?,comm = ?,deptno = ? where empno = ?";
        return baseUpdate(sql, emp.getEname(),emp.getJob(),emp.getMgr(),emp.getHiredate(),emp.getSal(),emp.getComm(),emp.getDeptno(),emp.getEmpno());
    }

    @Override
    public List<Emp> findAll() {
        String sql = "select * from emp";
        return baseQuery(Emp.class,sql);
    }

}
