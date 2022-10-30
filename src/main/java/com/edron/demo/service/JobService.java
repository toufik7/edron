package com.edron.demo.service;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import com.edron.demo.classes.Executor;
import com.edron.demo.classes.Task;
import com.edron.demo.entities.StrGeneratedParams;
import com.edron.demo.entities.TaskEntity;
import com.edron.demo.repositories.LetterRepository;
import com.edron.demo.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    LetterRepository letterRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    FormService formService;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final String charset = "UTF-8";
    private Logger logger = LoggerFactory.getLogger(JobService.class);


    public void task(StrGeneratedParams s){
        logger.info(" Message--- TASK "+ s.getFileName() +" START");

        TaskEntity taskEntity = new TaskEntity(formatter.format(new Date())); // create new task wth start time
        taskRepository.save(taskEntity);

        long startTime = System.nanoTime(); // start time

        printAllPossibleCombinations(s, taskEntity); // task

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        long minutes = (duration / 1000) / 60;
        long seconds = (duration / 1000) % 60;
        long milliseconds = duration - ((minutes*60)+seconds)*1000 ;

        taskEntity = taskRepository.findById(taskEntity.getId()).orElse(null);
        if (taskEntity != null){
            taskEntity.setState("COMPLETED");
            taskEntity.setEnd(formatter.format(new Date()));
            taskEntity.setMin(minutes);
            taskEntity.setSec(seconds);
            taskEntity.setMillisec(milliseconds);
            taskRepository.save(taskEntity);
        }
        // Print the output

        logger.info(" Message--- TASK "+ s.getFileName() +" COMPLETED in : " +
                        + minutes + " minutes "
                        + seconds + " seconds and " +
                        + milliseconds +  " milliseconds.");
    }

    public void executeTask(Task task){

        AtomicBoolean processing = new AtomicBoolean(true);
        Executor.Builder builder = new Executor.Builder();

        // add tasks here
        builder.add(task);

        builder.callback(()->{
            processing.set(false); // processing finished
            task.setState("Completed");
            logger.info(" Message--- PROGRAM TERMINATED");
        })
                .build()
                .execute();

    }

    public void printAllPossibleCombinations(StrGeneratedParams s, TaskEntity taskEntity) {

        char[] set = formService.getChars(s);
        int min = s.getMin();
        int max = s.getMax();
        int n = set.length;
        int i=min;
        int nbString = 0;
        String filePath = "C:\\Users\\pc cam\\Desktop\\project string\\";
        PrintWriter pw;

        try {
            String fileName = System.currentTimeMillis() +"_task_"+s.getFileName()+".txt"; // here you change file name
            taskEntity.setFileName(fileName); // set task fileName
            taskRepository.save(taskEntity);

            pw = new PrintWriter(filePath+fileName, charset); // open file

            while (i <= max && nbString < s.getStrNumber()){
                nbString = printAllPossibleCombinationsRec(set, "", n,  i, nbString, s.getStrNumber(), pw);
                i++;
            }

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int printAllPossibleCombinationsRec(char[] set, String prefix, int n, int m, int nbString,int nbStringUser, PrintWriter pw) {

        // m is 0, print prefix
        if (m == 0) {
            /**
             * here add file writing
             */
            if (nbString < nbStringUser) {
                pw.println(prefix);
                nbString++;
            }
            return nbString;
        }

            // One by one add all characters from set
            // recursively call for k equals to m-1
            for (int i = 0; i < n; ++i) {

                // Next character of input added
                String newPrefix = prefix + set[i];

                // m is decreased, because we have added a new character
                nbString = printAllPossibleCombinationsRec(set, newPrefix, n, m - 1, nbString, nbStringUser, pw);
            }
            return nbString;
        }
    }
