package com.edron.demo.service;

import com.edron.demo.entities.Letter;
import com.edron.demo.entities.StrGeneratedParams;
import com.edron.demo.repositories.LetterRepository;
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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class FormServiceTest {

    @TestConfiguration
    static class FormServiceImplTestContextConfiguration {
        @Bean
        public FormService formService() {
            return new FormService() {
                // implement methods

                public char[] getChars(StrGeneratedParams strGeneratedParams) {
                    // get letters composing the strings
                    Iterator iterator = strGeneratedParams.getLettersSet().iterator();
                    char[] chars = new char[strGeneratedParams.getLettersSet().size()];
                    for (int i = 0; i < chars.length; i++) {
                        Letter letter = (Letter) iterator.next();
                        chars[i] = letter.getLetter();
                    }
                    return chars;
                }

                public double getNbCombinations(StrGeneratedParams s) {
                    char[] set = getChars(s);
                    int min = s.getMin();
                    int max = s.getMax();
                    int n = set.length;

                    double nbCombinations = 0;
                    for (int i = min; i <= max; i++) {
                        nbCombinations = nbCombinations + Math.pow(n, i);
                    }
                    return nbCombinations;
                }

                public boolean valid(StrGeneratedParams s, int nbjobs, Model model) {
                    boolean valid = true;

                    if (s.getMin() > s.getMax()) {  // if min > max error
                        setFormModelAttribute(nbjobs, model);
                        valid = false;
                    } else {
                        double nbCombinations = getNbCombinations(s);

                        if (s.getStrNumber() > nbCombinations) {  // if  number strings (entered bu user) > possible combinations  error
                            setFormModelAttribute(nbjobs, model);
                            valid = false;
                        }
                    }
                    return valid;
                }
            };
        }
    }
    @Autowired
    private FormService formService;

    @MockBean
    private LetterRepository letterRepository;

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
    private Logger logger = LoggerFactory.getLogger(FormServiceTest.class);

    @Test
    public void setFormModelAttribute() throws Exception{
        logger.info("Running FormServiceTest: setFormModelAttribute()");

        String viewName = "generator";
        int nbJobs  = 1; // generate a form of One Job
        assertEquals(viewName, formService.setFormModelAttribute(nbJobs, model));
    }

    @Test
    public void getChars() {
        logger.info("Running FormServiceTest: getChars()");

        StrGeneratedParams s = new StrGeneratedParams();
        s = setLettersExemple(s);
        char[] checkedLetters = {'a','b'};

        //when(formService.getChars(s)).thenReturn(checkedLetters);
        assertArrayEquals(checkedLetters, formService.getChars(s));
    }

    @Test
    public void getNbCombinations() {
        logger.info("Running FormServiceTest: getNbCombinations()");

        StrGeneratedParams s = new StrGeneratedParams();
        s.setMax(2); s.setMin(1);
        s = setLettersExemple(s);
        double nbCombinations = 6;

        assertEquals(nbCombinations, formService.getNbCombinations(s));

    }

    @Test
    public void valid() {
        logger.info("Running FormServiceTest: valid()");

        StrGeneratedParams s1 = new StrGeneratedParams();
        s1.setMax(4); s1.setMin(1);
        s1 = setLettersExemple(s1);

        StrGeneratedParams s2 = new StrGeneratedParams();
        s2.setMax(2); s2.setMin(4);
        s2 = setLettersExemple(s2);

        boolean valid1 = true;
        boolean valid2 = false;

        assertEquals(valid1, formService.valid(s1, 1, model));
        assertEquals(valid2, formService.valid(s2, 1, model));

    }

    public StrGeneratedParams setLettersExemple(StrGeneratedParams s){
        Letter a = new Letter(); Letter b = new Letter();
        a.setId(1L); a.setLetter('a'); b.setId(2L); b.setLetter('b');
        s.setLettersSet(new HashSet<>());
        s.getLettersSet().add(a); s.getLettersSet().add(b);

        return s;
    }
}