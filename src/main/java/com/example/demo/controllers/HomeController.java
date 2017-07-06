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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
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

    @RequestMapping("/searchC")
    public String searchC(@ModelAttribute Exper exper, Model model) {
        List<Exper> expList = experRepo.findAllByCompany(exper.getCompany());
        List<User> userList = new ArrayList<User>();
        for (Exper e : expList){
            User user = userService.findById(e.getPersonid());
            if(!userList.contains(user)){
                userList.add(user);
            }
        }
        model.addAttribute("people", userList);
        model.addAttribute("title", "Company");
        return "results";
    }

    @RequestMapping("/searchS")
    public String searchS(@ModelAttribute Edu edu, Model model) {
        List<Edu> eduList = eduRepo.findAllBySchool(edu.getSchool());
        List<User> userList = new ArrayList<User>();
        for (Edu e : eduList){
            User user = userService.findById(e.getPersonid());
            if(!userList.contains(user)){
                userList.add(user);
            }
        }
        model.addAttribute("people", userList);
        model.addAttribute("title", "School");
        return "results";
    }

    @RequestMapping("/searchSk")
    public String searchSk(@ModelAttribute Skills skills, Model model) {
        List<Skills> skillList = skillsRepo.findAllBySkill(skills.getSkill());
        List<User> peopleList = new ArrayList<User>();
        for (Skills s : skillList){
            User p = userService.findById(s.getPersonid());
            if(!peopleList.contains(p)){
                peopleList.add(p);
            }
        }
        model.addAttribute("people", peopleList);
        model.addAttribute("title", "Skills");
        return "results";
    }

    @RequestMapping("/person/{id}")
    public String viewPerson(@PathVariable("id") Long id, Model model){
        User people = userService.findById(id);
        model.addAttribute("person", people);
        model = getPersonDataById(id, model, false);
        return "person";
    }

    /*
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
     */
    @RequestMapping("/edit/{id}")
    public String editInfo(Model model, @PathVariable("id") Long id){
        User user = userService.findById(id);
        model.addAttribute("p", user);
        model = getPersonDataById(id, model, true);
        return "recommend";
    }

    @PostMapping("/editProf/{id}")
    public String persEdit(@ModelAttribute User user, Model model, @PathVariable("id") Long id){
        User u = userService.findById(id);
        user.setEnabled(u.isEnabled());
        user.setPassword(u.getPassword());
        user.setUsername(u.getUsername());
        user.setRoles(u.getRoles());
        userService.saveUser(user);
        model.addAttribute("person", user);
        model = getPersonDataById(user.getId(), model, true);
        return "person";
    }

    @RequestMapping("/editEdu/{id}")
    public String eduEdit(@PathVariable("id") Integer id, Model model){
        Edu ed = eduRepo.findAllByEduid(id).get(0);
        model.addAttribute("ed", ed);
        return "editedu";
    }

    @RequestMapping("/eduResult/{id}/{eduid}")
    public String eduEdit(@ModelAttribute Edu edu, Model model, @PathVariable("id") Long id, @PathVariable("eduid") Integer eduid){
        edu.setPersonid(id);
        edu.setEduid(eduid);
        eduRepo.save(edu);
        User people = userService.findById(id);
        model.addAttribute("person", people);
        model = getPersonDataById(id, model, true);
        return "person";
    }

    @RequestMapping("/editExp/{id}")
    public String expEdit(@PathVariable("id") Integer id, Model model){
        Exper exp = experRepo.findAllByExpid(id).get(0);
        model.addAttribute("exp", exp);
        return "editexp";
    }

    @RequestMapping("/expResult/{id}/{expid}")
    public String eduEdit(@ModelAttribute Exper exp, Model model, @PathVariable("id") Long id, @PathVariable("expid") Integer expid){
        exp.setPersonid(id);
        exp.setExpid(expid);
        experRepo.save(exp);
        User people = userService.findById(id);
        model.addAttribute("person", people);
        model = getPersonDataById(id, model, true);
        return "person";
    }

    @RequestMapping("/editSkill/{id}")
    public String skillEdit(@PathVariable("id") Integer id, Model model){
        Skills skill = skillsRepo.findAllBySkid(id).get(0);
        model.addAttribute("skill", skill);
        return "editskill";
    }

    @RequestMapping("/skillResult/{id}/{skid}")
    public String eduEdit(@ModelAttribute Skills skills, Model model, @PathVariable("id") Long id, @PathVariable("skid") Integer skid){
        skills.setPersonid(id);
        skills.setSkid(skid);
        skillsRepo.save(skills);
        User people = userService.findById(id);
        model.addAttribute("person", people);
        model = getPersonDataById(id, model, true);
        return "person";
    }

    @RequestMapping("/addEdu/{id}")
    public String addEdu(@PathVariable("id") Long id, Model model){
        Edu ed = new Edu();
        ed.setPersonid(id);
        model.addAttribute("ed", ed);
        return "editedu";
    }

    @RequestMapping("/addWork/{id}")
    public String addWork(@PathVariable("id") Long id, Model model){
        Exper exper = new Exper();
        exper.setPersonid(id);
        model.addAttribute("exp", exper);
        return "editexp";
    }

    @RequestMapping("/addSkill/{id}")
    public String addSkill(@PathVariable("id") Long id, Model model) {
        Skills skills = new Skills();
        skills.setPersonid(id);
        model.addAttribute("skill", skills);
        return "editskill";
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