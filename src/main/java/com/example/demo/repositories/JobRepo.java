package com.example.demo.repositories;

import com.example.demo.models.Job;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by student on 7/6/17.
 */
public interface JobRepo extends CrudRepository<Job, Integer> {
}
