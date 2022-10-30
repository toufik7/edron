package com.edron.demo.service;

import com.edron.demo.entities.Letter;
import com.edron.demo.entities.StrGeneratedParams;
import com.edron.demo.repositories.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FormService {

    @Autowired
    LetterRepository letterRepository;

    public String setFormModelAttribute(int nbJobs, Model model) {

        List<Letter> letters = letterRepository.findAll();
        ArrayList<StrGeneratedParams> strGeneratedParamsArray = new ArrayList<>();

        // populate array with tasks
        for (int i = 1; i <= nbJobs; i++) {
            StrGeneratedParams s = new StrGeneratedParams();
            s.setId((long) i);
            strGeneratedParamsArray.add(s);
        }

        model.addAttribute("slist", strGeneratedParamsArray);
        model.addAttribute("s", new StrGeneratedParams());
        model.addAttribute("letters", letters);
        model.addAttribute("nbJobs", nbJobs);

        return "generator";
    }

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
}
