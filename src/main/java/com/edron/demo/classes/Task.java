package com.edron.demo.classes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {
    private Runnable task;
    private String state;

    public Task(Runnable task, String state) {
        this.task = task;
        this.state = state;
    }
}
