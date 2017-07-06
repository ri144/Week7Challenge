package com.example.demo.configs;

/**
 * Created by student on 7/5/17.
 */
import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    EduRepo eduRepo;
    @Autowired
    ExperRepo experRepo;
    @Autowired
    SkillsRepo skillsRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
       /* Edu edu;
        Skills s;
        Exper exp;
        System.out.println("Loading data . . .");
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");
        User user = new User("bob@bob.com","bob","Bob","b", "Bobberson", true, "bob");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);
        long id = user.getId();
        exp = new Exper("intern","leidos","June","August", 2016, 2017, "fetch coffee", "paperwight", id);
        experRepo.save(exp);
        edu = new Edu("BS", "ECE", "RU", 2017, id);
        eduRepo.save(edu);
        edu = new Edu("PhD", "CSE", "OSU", 2022, id);
        eduRepo.save(edu);
        s = new Skills("talking", "okay", id);
        skillsRepo.save(s);

        user = new User("jim@jim.com","jim","Jim","j", "Jimmerson", true, "jim");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);
        id = user.getId();
        exp = new Exper("developer","google","June","July", 2017, 2017, "code", "think", id);
        experRepo.save(exp);
        edu = new Edu("BS", "CS", "CMU", 2017, id);
        eduRepo.save(edu);
        s = new Skills("coding", "good", id);
        skillsRepo.save(s);

        user = new User("admin@secure.com","password","Admin","a", "User", true, "admin");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);
        user = new User("sam@every.com","password","Sam","s", "Everyman", true, "everyman");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);*/
    }
}
