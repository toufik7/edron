package com.edron.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stringsGeneratedParams")
public class StrGeneratedParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private int min;
    private int max;
    private int strNumber;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "letters_strings",
            joinColumns = @JoinColumn(name = "stringsGeneratedParams_id"),
            inverseJoinColumns = @JoinColumn(name = "Letter_id")
    )
    private Set<Letter> lettersSet;

}
