# edron
String Generator Web Application

## Start Application 

- download the following war file [demo.zip](https://drive.google.com/file/d/1FfmK-IFF-qcJic7gq0zYO_LwwMTa8VPG/view?usp=share_link)  and extract to get `demo.war`

- add `demo.war` under `target` diractory
- in root diractory press : `docker-compose up -d`
- this will start the following services : 
  - `mysql database` with exsisting samples for testing on port 3036
  - `phpMyAdmin GUI` on port 8081 [localhost:8081](http://localhost:8081)
  - `Tomcat` web application on port 8082
  
  - go to [localhost:8082/demo](http://localhost:8082/demo/) to get to Home page.
