package com.edron.demo.controller;

import com.edron.demo.entities.TaskEntity;
import com.edron.demo.repositories.TaskRepository;
import com.edron.demo.service.FormService;
import com.edron.demo.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @Autowired
    private FormService formService;
    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String home(Model model) {
        return homeService.setFormModelAttribute(model);
    }

    @PostMapping("/")
    public String homeSubmit(@RequestParam(value = "nbJobs", required = false) int nbJobs
            , Model model) {

        formService.setFormModelAttribute(nbJobs, model);

        return "generator";
    }
}
