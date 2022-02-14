# CourseAPI - README

Here I am created a CourseAPI complete backend using Spring Boot and used Apache Derby Database which is embedded in Spring Boot.

#### Live Link - [Hosted on Heroku](https://courseapi-spring-boot.herokuapp.com)



### Sample APIs to check the functionalities

- GetAllTopics - https://courseapi-spring-boot.herokuapp.com/topics
- GetAllCourses for Topic Id "java" - https://courseapi-spring-boot.herokuapp.com/topics/java/courses

- GetAllLessons for Course Id "java-8"- https://courseapi-spring-boot.herokuapp.com/topics/java/courses/java-8/lessons

- Get API health - https://courseapi-spring-boot.herokuapp.com/actuator/health

- Swagger UI to Test APIs - https://courseapi-spring-boot.herokuapp.com/swagger-ui.html



### APIs I have built

Here we have 3 Controllers 

- Topic APIs
- Course APIs - A topic may consists of many courses
- Lesson APIs - A course may consists of many lessons

 

### Simple Example to understand

- Lets say Java as a Topic. 

- Java has many courses like Java 8 , Java Collections and so on.
- Java 8 has many lessons like Lambda, Functional Interface etc.



### Topic API details

| Request Type | URI        | Description          |
| ------------ | ---------- | -------------------- |
| GET          | /topics    | Get all Topics       |
| GET          | /topics/id | Get a specific Topic |
| POST         | /topics    | Create a new Topic   |
| PUT          | /topics/id | Updates the topic.   |
| DELETE       | /topics/id | Deletes the topic    |



### Course API details

| Request Type | URI                                  | Description           |
| ------------ | ------------------------------------ | --------------------- |
| GET          | /topics/{topicId}/courses            | Get all Courses       |
| GET          | /topics/{topicId}/courses/{courseId} | Get a specific Course |
| POST         | /topics/{topicId}/courses            | Create a new Course   |
| PUT          | /topics/{topicId}/courses/{courseId} | Updates the Course    |
| DELETE       | /topics/{topicId}/courses/{courseId} | Deletes the Course    |



### Lessons API details

| Request Type | URI                                                     | Description           |
| ------------ | ------------------------------------------------------- | --------------------- |
| GET          | /topics/{topicId}/courses/{courseId}/lessons            | Get all Lessons       |
| GET          | /topics/{topicId}/courses/{courseId}/lessons/{lessonId} | Get a specific Lesson |
| POST         | /topics/{topicId}/courses/{courseId}/lessons            | Create a new Lesson   |
| PUT          | /topics/{topicId}/courses/{courseId}/lessons/{lessonId} | Updates the Lesson    |
| DELETE       | /topics/{topicId}/courses/{courseId}/lessons/{lessonId} | Deletes the Lesson    |



### Demo

Fetching all Topics : https://courseapi-spring-boot.herokuapp.com/topics

```json
[
  {
    "id": "java",
    "name": "Core Java",
    "description": "Core Java Description"
  },
  {
    "id": "js",
    "name": "JavaScript",
    "description": "JavaScript Description"
  }
]
```



Fetching All courses under Topic Id "java" : https://courseapi-spring-boot.herokuapp.com/topics/java/courses

```json
[
  {
    "id": "java-8",
    "name": "Java 8",
    "description": "Java 8 Description",
    "topic": {
      "id": "java",
      "name": "Core Java",
      "description": "Core Java Description"
    }
  }
]
```



Fetching All lessons under Course Id "java-8" : https://courseapi-spring-boot.herokuapp.com/topics/java/courses/java-8/lessons

```json
[
    {
        "id": "java-lambda",
        "name": "Java lambda",
        "description": "Java lambda updated Description",
        "course": {
            "id": "java-8",
            "name": "Java 8",
            "description": "Java 8 Description",
            "topic": {
                "id": "java",
                "name": "Core Java",
                "description": "Core Java Description"
            }
        }
    }
]
```



### Credits

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Lombok](https://projectlombok.org/)

- [STS IDE](https://spring.io/tools)
- [Heroku](https://www.heroku.com/)
- [MySQL](https://www.mysql.com/)
- [Git](https://git-scm.com/)

