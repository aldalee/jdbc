package com.msb.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * emp实体类<br>
 * 实体类: 与数据库表格名称和字段一一对应的类<br>
 * 用处: 存储从数据库中查询到的数据<br>
 * 要求: <br>1. 类名和表名保持一致<br>
 *      2. 属性个数和数据库表的字段保持一致<br>
 *      3. 属性的数据类型和字段的数据类型保持一致<br>
 *      4. 属性名和字段名保持一致<br>
 *      5. 所有的属性必须是私有的(安全性)<br>
 *      6. 实体类的属性推荐写成包装类<br>
 *      7. 日期类推荐导入 java.util.Date<br>
 *      8. 所有的属性都要有set 和 get方法<br>
 *      9. 必须有空参构造器<br>
 *      10. 实体类应实现序列化接口 (mybatis缓存、分布式数据等等 有需要……)<br>
 *      11. 实体类中其他构造方法可选，非必须<br>
 * @author HuanyuLee
 * @date 2022/3/6
 */
public class Emp implements Serializable {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Double sal;
    private Double comm;
    private Integer deptno;

    public Emp() {
    }

    public Emp(Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }
}
