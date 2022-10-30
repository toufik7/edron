# edron
String Generator Web Application

## Start Application In One Command

- download `edron.zip` and extract files 

- download zip file `demo.zip` [link](https://drive.google.com/file/d/1rbEzNylI-qBup01lrFimsX_ekMVgAiuL/view?usp=share_link)  and extract it to get `demo.war`

- add `demo.war` under `target` diractory
- start `Docker`
- in root diractory run : `docker-compose up -d`
- this will download necessary images and start the following services :
  - `mysql database` with exsisting samples for testing on port 3036
  - `phpMyAdmin GUI` on port 8081 [localhost:8081](http://localhost:8081)
    - login with `root` as `username` and `password`.
  - `Tomcat` web application on port 8082
  
  - go to [localhost:8082/demo](http://localhost:8082/demo/) to get to Home page.
  
## Run Tests In One Command

 - in root diractory run : `mvn test`

#### PS: to view generated files please run project using an IDE (intellij... ) , file diractory is not created when running war file therefor files are not saved on local machine, but jobs metadata is still present in the database.
 
 ## OVERVIEW
 
 ### Home
![Home](https://user-images.githubusercontent.com/52804863/198896524-0fe5831c-846e-40e9-a548-6d6d201328e3.png)
 ### multiple jobs Fom valid
![multiple jobs Fom valid](https://user-images.githubusercontent.com/52804863/198896529-c3c703c8-6203-4ae0-8a3a-a0532a406a04.png)
 ### Single job form_ valid
![Single job form_ valid](https://user-images.githubusercontent.com/52804863/198896531-f853b805-b334-4198-bc00-2380a32a8f29.png)
 ### Alert_Error_nbStrings
![Alert_Error_nbStrings](https://user-images.githubusercontent.com/52804863/198896536-88ea9b8e-1ac9-4935-aaa9-bfa1266de8d0.png)
 ### Alert_Error_minMax
![Alert_Error_minMax](https://user-images.githubusercontent.com/52804863/198896537-53f3cfb9-c0c3-44f5-b709-5bfd66487552.png)
 ### tasks_table
![tasks_table](https://user-images.githubusercontent.com/52804863/198896539-c948feac-cea0-48df-8167-f9469a1bf6b0.png)
 ### Running_Completed_jobs
![Running_Completed_jobs](https://user-images.githubusercontent.com/52804863/198896541-b91cbe73-c530-4ae9-bcba-39ceab78e5c1.png)
 ### Generated Files 
 -exemple of [45 million String]() of [f, g, h, i characters]() generated , notice the number of lines.
 ![generatedFile45M](https://user-images.githubusercontent.com/52804863/198896838-d053c03e-2601-4df8-88af-e23aa5ee7c55.png)


