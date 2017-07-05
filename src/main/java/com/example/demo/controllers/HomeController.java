package com.example.demo.controllers;

/**
 * Created by student on 7/5/17.
 */
import com.example.demo.models.Exper;
import com.example.demo.models.*;
import com.example.demo.repositories.*;
import com.example.demo.services.UserService;
import com.example.demo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private SkillsRepo skillsRepo;

    @Autowired
    private EduRepo eduRepo;

    @Autowired
    private ExperRepo experRepo;

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;
    @Autowired
    RoleRepository roleRepository;


    @RequestMapping("/")
    public String myprofile(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        if (roleRepository.findByRole("USER").getUsers().contains(user)){
            // assume its a job seeker
            model.addAttribute("person", user);
            model = getPersonDataById( user.getId(), model, true);
            return "person";
        }
        else{
            //must be a recruiter if they dont have a corresponding profile
            model.addAttribute("person", new User());
            model.addAttribute("exp", new Exper());
            model.addAttribute("edu", new Edu());
            model.addAttribute("skill", new Skills());
            return "search";
        }
    }

    @RequestMapping("/s")
    public String index(Model model){
        model.addAttribute("person", new User());
        model.addAttribute("exp", new Exper());
        model.addAttribute("edu", new Edu());
        model.addAttribute("skill", new Skills());
        return "search";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
        model.addAttribute("user", user);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "login";
    }
    public UserValidator getUserValidator() {
        return userValidator;
    }
    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @RequestMapping("/search")
    public String search(@ModelAttribute User user, Model model) {
        List<User> peopleList = userService.findAllByName(user.getFirstName(), user.getMidinit(), user.getLastName());
        model.addAttribute("people", peopleList);
        model.addAttribute("title", "User Name");
        return "results";
    }






    @RequestMapping("/person/{id}")
    public String viewPerson(@PathVariable("id") Long id, Model model){
        User people = userService.findById(id);
        model.addAttribute("person", people);
        model = getPersonDataById(id, model, false);
        return "person";
    }

    private Model getPersonDataById(Long id, Model model, boolean profile){
        List<Edu> eduList = eduRepo.findAllByPersonid(id);
        List<Exper> expList = experRepo.findAllByPersonid(id);
        List<Skills> skills = skillsRepo.findAllByPersonid(id);
        model.addAttribute("edu", eduList);
        model.addAttribute("exp", expList);
        model.addAttribute("skill", skills);
        if(profile) {
            model.addAttribute("edit", "Edit your profile.");
        }
        return model;
    }

}