package com.example.demo.repositories;

import com.example.demo.models.Exper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by student on 6/28/17.
 */
public interface ExperRepo extends CrudRepository<Exper, Integer>{
    public List<Exper> findAllByPersonid(Long personid);
    public List<Exper> findAllByCompany(String company);
    public List<Exper> findAllByExpid(Long expid);
}
