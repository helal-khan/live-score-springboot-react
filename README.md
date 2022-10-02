# Live Score - Spring Boot + React

## Technology Stack

1. Java EE.
2. Spring Boot.
3. Spring Security - JWT.
4. Spring Data JPA.
5. Scheduler.
6. MySQL.
7. Unit Testing.
8. Maven
9. React.
10. TypeScript.
11. Ant Design.
12. Axios.


## Instruction to run the project

1. Clone https://github.com/helal-khan/live-score-springboot-react.git and open it your favorite IDE. I use IntelliJ IDEA.
2. Create a mysql database (live_score) and change (DB name, Username, Password) accordingly in application.yml file
3. Run the below command to install maven project. (It may takes few times to install frontend project as well)
```bash
mvn install
```
4. Then run the project manually or run the following command.
```bash
mvn spring-boot:run
```


## Feature Completed

1. A scheduler service parse data from an XML source after every
   5 minutes.
2. Persist all the live records into the MySQL Database. Duplicate data must not be inserted.
3. In a single dashboard(Table) shows latest scores from Database.
4. There is a search option where users are able to search any keyword to find any specific news of cricket.
5. Users are able to view this Dashboard if he/ she are logged in.
6. If user not logged in then, shows a Login Page and user will not be able to view this Dashboard page.
7. User can register by himself from Registration Page and get an access to view latest scores.


## Author
- [@helal](https://github.com/helal-khan)
