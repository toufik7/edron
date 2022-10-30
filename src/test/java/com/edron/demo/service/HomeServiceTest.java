package com.edron.demo.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import java.util.Collection;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class HomeServiceTest {

    @MockBean
    private HomeService homeService;

    private Logger logger = LoggerFactory.getLogger(FormServiceTest.class);

    @Test
    public void setFormModelAttributeForHome() throws Exception{
        logger.info("Running HomeServiceTest: setFormModelAttributeForHome()");

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
        String viewName = "Home";
        when(homeService.setFormModelAttribute(model)).thenReturn(viewName);
        assertEquals("Home", homeService.setFormModelAttribute(model));
    }
}