package com.edron.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class HomeService {

    public String setFormModelAttribute(Model model) {

        int nbJobs = 1;// default value
        model.addAttribute("nbJobs", nbJobs);
        return "Home";
    }
}