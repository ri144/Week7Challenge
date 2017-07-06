package com.example.demo.models;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by student on 7/6/17.
 */
@Entity
public class Job {

    private long personid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String employer;

    private String description;

    private double salaryLow;

    private double salaryHigh;

   /* @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "job_personid"),inverseJoinColumns = @JoinColumn(name = "skills_personid"))
    private Collection<Skills> skillsCollection;*/

   private String skillData;

    public long getPersonid() {
        return personid;
    }

    @Column(unique = true)
    public void setPersonid(long personid) {
        this.personid = personid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalaryLow() {
        return salaryLow;
    }

    public void setSalaryLow(double salaryLow) {
        this.salaryLow = salaryLow;
    }

    public double getSalaryHigh() {
        return salaryHigh;
    }

    public void setSalaryHigh(double salaryHigh) {
        this.salaryHigh = salaryHigh;
    }

    public String getSkillData() {
        return skillData;
    }

    public void setSkillData(String skillData) {
        this.skillData = skillData;
    }

   /* public Collection<Skills> getSkillsCollection() {
        return skillsCollection;
    }

    public void setSkillsCollection(Collection<Skills> skillsCollection) {
        this.skillsCollection = skillsCollection;
    }*/
}
