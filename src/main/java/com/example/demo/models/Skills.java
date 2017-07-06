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
public class Skills {

    @Size(max=25)
    private String skill;
    @Size(max=25)
    private String proficiency;

    private int jobid;

    private long personid;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int skid;

    public Skills(String sk, String pro, Long pid){
        skill = sk;
        proficiency = pro;
        personid = pid;
    }

    public Skills(){}

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

    public int getSkid() {
        return skid;
    }

    public void setSkid(int skid) {
        this.skid = skid;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }
}
