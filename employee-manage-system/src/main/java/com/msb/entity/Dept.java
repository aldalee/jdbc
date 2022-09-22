package com.msb.entity;

import java.io.Serializable;

/**
 * Dept实体类
 * @author HuanyuLee
 * @date 2022/3/8
 */
public class Dept implements Serializable {
    private Integer deptno;
    private String dname;
    private String loc;

    public Dept() {
    }

    public Dept(Integer deptno, String dename, String loc) {
        this.deptno = deptno;
        this.dname = dename;
        this.loc = loc;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dename='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
