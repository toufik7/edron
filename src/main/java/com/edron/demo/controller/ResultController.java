package com.edron.demo.controller;

import com.edron.demo.classes.Task;
import com.edron.demo.entities.StrGeneratedParams;
import com.edron.demo.service.FormService;
import com.edron.demo.service.JobService;
import com.edron.demo.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ResultController {

    @Autowired
    private ResultService resultService;
    @Autowired
    private JobService jobService;
    @Autowired
    private FormService formService;

    @GetMapping("/result")
    public String result(Model model) {

        return resultService.setFormModelAttribute(model);
    }



    @PostMapping("/result")
    public String jobSubmit(@ModelAttribute StrGeneratedParams s,
                            @RequestParam(value = "nbJobs", required = false) int nbJobs,
                            @RequestParam(value = "inResult", required = false) boolean inResult,
                            Model model) {

            boolean isValid = formService.valid(s, nbJobs, model);

            if (isValid) { // post form if valid

                // prepare tasks
                Runnable r = ()-> jobService.task(s);
                Task t = new Task(r, "");

                // execute tasks
                jobService.executeTask(t);

                nbJobs--;
                if (nbJobs == 0) { return resultService.setFormModelAttribute(model); }
            }
        return formService.setFormModelAttribute(nbJobs, model);
    }
}
