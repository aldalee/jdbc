package com.msb.view;


import com.msb.dao.DeptDao;
import com.msb.dao.EmpDao;
import com.msb.dao.impl.DeptDaoImpl;
import com.msb.dao.impl.EmpDaoImpl;
import com.msb.entity.Dept;
import com.msb.entity.Emp;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * 员工管理系统主方法
 * @author HuanyuLee
 * @date 2022/3/9
 */
public class EmpManageSystem {
    private static Scanner sc = new Scanner(System.in);
    private static EmpDao empDao = new EmpDaoImpl();
    private static DeptDao deptDao = new DeptDaoImpl();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");;
    
    public static void main(String[] args) {
        o: while (true){
            showMenu();
            System.out.print("请录入选项: ");
            int option = sc.nextInt();
            switch (option){
                case 1:
                    deptInfo();break;
                case 2:
                    empInfo();break;
                case 3:
                    deleteEmpByEmpNo();break;
                case 4:
                    updateEmpByEmpNo();break;
                case 5:
                    addEmp();break;
                case 6:
                    addDept();break;
                case 7:
                    System.out.println("Bye!");break o;
                default:
                    System.out.println("请输入正确选项......");
            }
        }
        sc.close();
    }

    private static void deptInfo(){
        List<Dept> depts = deptDao.findAll();
        depts.forEach(System.out::println);
    }
    private static void empInfo(){
        List<Emp> emps = empDao.findAll();
        emps.forEach(System.out::println);
    }
    private static void deleteEmpByEmpNo(){
        System.out.print("请输入要删除的员工编号: ");
        empDao.deleteByEmpno(sc.nextInt());
    }
    private static void updateEmpByEmpNo(){
        System.out.print("请输入员工编号: ");
        Emp emp = scBase(sc.nextInt());
        empDao.updateEmp(emp);
    }
    private static void addEmp(){
        Emp emp = scBase(null);
        empDao.addEmp(emp);
    }
    private static Emp scBase(Integer empno){
        System.out.print("请输入员工姓名: ");
        String ename =sc.next();
        System.out.print("请输入员工职位: ");
        String job =sc.next();
        System.out.print("请输入员工上级: ");
        int mgr =sc.nextInt();
        System.out.print("请输入员工入职日期,格式为yyyy-MM-dd : ");
        Date hiredate = null;
        try {
            hiredate = simpleDateFormat.parse(sc.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print("请输入员工工资: ");
        double sal = sc.nextDouble();
        System.out.print("请输入员工补助: ");
        double comm = sc.nextDouble();
        System.out.print("请输入员工部门号: ");
        int deptno = sc.nextInt();
        return new Emp(empno, ename, job, mgr, hiredate, sal, comm,deptno);
    }
    private static void addDept(){
        System.out.print("请录入部门号: ");
        int deptno =sc.nextInt();
        System.out.print("请录入部门名称: ");
        String dname =sc.next();
        System.out.print("请录入部门位置: ");
        String loc =sc.next();
        Dept dept =new Dept(deptno,dname,loc);
        deptDao.addDept(dept);
    }
    private static void showMenu() {
        System.out.println("************************************");
        System.out.println("*         1 查看所有部门信息          *");
        System.out.println("*         2 查看所有员工信息          *");
        System.out.println("*         3 由工号删除员工信息        *");
        System.out.println("*         4 由工号修改员工信息        *");
        System.out.println("*         5 增加员工信息             *");
        System.out.println("*         6 增加部门信息             *");
        System.out.println("*         7 退出管理系统             *");
        System.out.println("************************************");
    }
}
