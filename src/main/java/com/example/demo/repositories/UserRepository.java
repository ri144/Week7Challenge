package com.example.demo.repositories;

/**
 * Created by student on 7/5/17.
 */
import com.example.demo.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
    List<User> findAllByFirstNameAndMidinitAndLastName(String firstname, String midinit, String lastname);
    User findById(Long id);

}