package com.example.demo.repositories;

import com.example.demo.models.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by student on 7/6/17.
 */
public interface JobRepo extends CrudRepository<Job, Integer> {
    List<Job> findAllByPersonid(Long id);
    Job findById(Integer id);
    List<Job> findAllByTitle(String title);
}
