package com.example.demo.validators;

import com.example.demo.models.Job;
import com.example.demo.models.User;
import com.example.demo.repositories.JobRepo;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by student on 7/7/17.
 */
@Component
public class JobValidator implements Validator {
    @Autowired
    JobRepo jobRepository;
    public boolean supports(Class<?> clazz){
        return Job.class.isAssignableFrom(clazz);
    }
    public void validate(Object target, Errors errors){
        Job job = (Job) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "job.title.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employer", "job.employer.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "job.description.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salaryLow", "job.salaryLow.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salaryHigh", "job.salaryHigh.empty");
    }
}
