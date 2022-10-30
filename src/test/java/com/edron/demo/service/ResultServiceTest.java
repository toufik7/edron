package com.edron.demo.service;

import com.edron.demo.entities.TaskEntity;
import com.edron.demo.repositories.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class ResultServiceTest {
    @TestConfiguration
    static class ResultServiceImplTestContextConfiguration {
        @Bean
        public ResultService resultService() {
            return new ResultService() {
                // implement methods
            };
        }
    }

    @Autowired
    private ResultService resultService;
    @MockBean
    private TaskRepository taskRepository;

    private Logger logger = LoggerFactory.getLogger(FormServiceTest.class);


    @Test
    public void setFormModelAttributeForResult() throws Exception{
        logger.info("Running ResultServiceTest: setFormModelAttributeForResult()");

        Model model = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
        String viewName = "result";
        assertEquals(viewName, resultService.setFormModelAttribute(model));
    }

    @Test
    public void completed_running_tasks() {
        logger.info("Running ResultServiceTest: completed_running_tasks()");

        TaskEntity taskEntity1 = new TaskEntity(1L,"","","",0,0,0,"COMPLETED");
        TaskEntity taskEntity2 = new TaskEntity(2L,"","","",0,0,0,"RUNNING");
        TaskEntity taskEntity3 = new TaskEntity(3L,"","","",0,0,0,"COMPLETED");
        List<TaskEntity> completed_tasks = new ArrayList<>();
        List<TaskEntity> running_tasks = new ArrayList<>();
        completed_tasks.add(taskEntity1); completed_tasks.add(taskEntity3);
        running_tasks.add(taskEntity2);


        when(taskRepository.findTaskEntitiesByState("COMPLETED")).thenReturn(completed_tasks);
        when(taskRepository.findTaskEntitiesByState("RUNNING")).thenReturn(running_tasks);

        assertEquals(completed_tasks, taskRepository.findTaskEntitiesByState("COMPLETED"));
        assertEquals(running_tasks, taskRepository.findTaskEntitiesByState("RUNNING"));
    }
}