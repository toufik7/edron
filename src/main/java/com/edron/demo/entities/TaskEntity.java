package com.edron.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String start="";
    private String end="";
    private long min;
    private long sec;
    private long millisec;
    private String state ="RUNNING";

    public TaskEntity(String start){
        this.start = start;
    }

}
