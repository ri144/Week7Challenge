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
public class Edu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eduid;
    @Size(min = 0, max = 10)
    private String degreelevel;
    @Size(max = 25)
    private String major;
    @Size(max = 25)
    private String school;
    private int year;
    private long personid;

    public Edu(String deg, String maj, String scho, Integer y, Long pid){
        degreelevel = deg;
        major = maj;
        school = scho;
        year = y;
        personid = pid;
    }
    public Edu(){}

    public int getEduid() {
        return eduid;
    }

    public void setEduid(int eduid) {
        this.eduid = eduid;
    }

    public String getDegreelevel() {
        return degreelevel;
    }

    public void setDegreelevel(String degreelevel) {
        this.degreelevel = degreelevel;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }
}
