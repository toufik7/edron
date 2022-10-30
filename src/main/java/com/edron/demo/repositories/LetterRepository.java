package com.edron.demo.repositories;

import com.edron.demo.entities.Letter;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LetterRepository extends JpaRepository<Letter, Long> {
    Letter findLetterById(Long id);

}
