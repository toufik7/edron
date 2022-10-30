package com.edron.demo.service;

import com.edron.demo.entities.TaskEntity;
import com.edron.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    TaskRepository taskRepository;

    public String setFormModelAttribute(Model model) {

        List<TaskEntity> completedTasks = taskRepository.findTaskEntitiesByState("COMPLETED");
        List<TaskEntity> runningTasks = taskRepository.findTaskEntitiesByState("RUNNING");

        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("runningTasks", runningTasks);
        model.addAttribute("t", new TaskEntity());

        return "result";
    }
}