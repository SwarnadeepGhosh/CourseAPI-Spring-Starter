# CourseAPI-Spring-Starter - README

Here I am created a CourseAPI complete backend using Spring Boot and used Apache Derby Database which is embedded in Spring Boot.



#### Live Link - [Hosted on Heroku](https://courseapi-spring-boot.herokuapp.com)



### APIs I have built

Here we have 3 Controllers 

- Topic APIs
- Course APIs 
- Lesson APIs (Currently in Development)

 

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

| Request Type | URI                                  | Description          |
| ------------ | ------------------------------------ | -------------------- |
| GET          | /topics/{topicId}/courses            | Get all Topics       |
| GET          | /topics/{topicId}/courses/{courseId} | Get a specific Topic |
| POST         | /topics/{topicId}/courses            | Create a new Topic   |
| PUT          | /topics/{topicId}/courses/{courseId} | Updates the topic.   |
| DELETE       | /topics/{topicId}/courses/{courseId} | Deletes the topic    |



### Lessons API details

Currently in Development stage.



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
    "id": "java-collections",
    "name": "Java Collections",
    "description": "Java Collections Description",
    "topic": {
      "id": "java",
      "name": "Core Java",
      "description": "Core Java Description"
    }
  },
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

