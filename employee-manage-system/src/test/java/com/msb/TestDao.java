package com.msb;

import com.msb.entity.Emp;
import com.msb.dao.impl.EmpDaoImpl;

import java.util.Date;

/**
 * @author HuanyuLee
 * @date 2022/3/8
 * @description
 */
public class TestDao {
    public static void main(String[] args) {
        Emp emp = new Emp(null,"Jack","Manger",7839,new Date(),3000.12,1000.31,30);
        EmpDaoImpl empDao = new EmpDaoImpl();
        final int rows = empDao.addEmp(emp);
        System.out.println("rows = " + rows);
    }
}
