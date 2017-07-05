package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by student on 6/28/17.
 */
@Entity
public class Exper {

    @Size(max=25)
    private String position;
    @Size(max = 25)
    private String company;
    @Size(max = 15)
    private String startmonth;
    private int startyear;
    @Size(max = 15)
    private String endmonth;
    private int endyear;
    @Size(max = 50)
    private String duty1;
    @Size(max = 50)
    private String duty2;
    private long personid;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int expid;

    public Exper(String pos, String com, String sm, String em, Integer sy, Integer ey, String d1, String d2, Long pid){
        position = pos;
        company = com;
        startmonth = sm;
        endmonth = em;
        startyear = sy;
        endyear = ey;
        duty1 = d1;
        duty2 = d2;
        personid = pid;
    }

    public Exper(){}

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartmonth() {
        return startmonth;
    }

    public void setStartmonth(String startmonth) {
        this.startmonth = startmonth;
    }

    public int getStartyear() {
        return startyear;
    }

    public void setStartyear(int startyear) {
        this.startyear = startyear;
    }

    public String getEndmonth() {
        return endmonth;
    }

    public void setEndmonth(String endmonth) {
        this.endmonth = endmonth;
    }

    public int getEndyear() {
        return endyear;
    }

    public void setEndyear(int endyear) {
        this.endyear = endyear;
    }

    public String getDuty1() {
        return duty1;
    }

    public void setDuty1(String duty1) {
        this.duty1 = duty1;
    }

    public String getDuty2() {
        return duty2;
    }

    public void setDuty2(String duty2) {
        this.duty2 = duty2;
    }

    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

    public int getExpid() {
        return expid;
    }

    public void setExpid(int expid) {
        this.expid = expid;
    }
}
