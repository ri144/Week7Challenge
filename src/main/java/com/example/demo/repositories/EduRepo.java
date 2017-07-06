package com.example.demo.repositories;

import com.example.demo.models.Edu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by student on 6/28/17.
 */
public interface EduRepo extends CrudRepository<Edu, Integer>{
    public List<Edu> findAllByPersonid(Long personid);
    public List<Edu> findAllBySchool(String school);
    public List<Edu> findAllByEduid(Integer eduid);
}
