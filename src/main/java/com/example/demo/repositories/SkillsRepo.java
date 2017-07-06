package com.example.demo.repositories;

import com.example.demo.models.Skills;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by student on 6/28/17.
 */
public interface SkillsRepo extends CrudRepository<Skills,Integer>{
    public List<Skills> findAllByPersonid(Long personid);
    public List<Skills> findAllBySkill(String skill);
    public List<Skills> findAllBySkid(Integer skid);
    public List<Skills> findAllByJobid(Integer jobid);
}
