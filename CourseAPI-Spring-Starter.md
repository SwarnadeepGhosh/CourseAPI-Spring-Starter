# CourseAPI-Spring-Starter - Tutorial

[TOC]

**Prerequisites and Installation**

- STS 4
- JDK 8 or higher
- Maven download and setup path for `JAVA_HOME` ,`M2_HOME`, `MAVEN_HOME`
- Add Maven Bin folder and Java Bin folder in the end of path under system variable



### Creating Spring Boot Project

1. Created a Maven project (Simple project checkbox selected)

2. Given proper group/ artifact id and finish

```xml
<groupId>com.swarna.springboot</groupId>
<artifactId>CourseAPI-Spring-Starter</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>CourseAPI-Spring-Starter</name>
<description>CourseAPI-Spring Boot Starter project created took help from Java Brains</description>
```

3. Added below dependencies for Spring boot

```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.12.RELEASE</version>
</parent>

<dependencies>
    <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>2.6.1</version>
    </dependency>
</dependencies>

<properties>
    <java.version>1.8</java.version>
</properties>
```



### Starting a Spring Boot App

***Startup Steps***

- Sets up Default Configuration

- Starts Spring Application context

- Performs class path scan (Using different annotations)

- Starts Tomcat Server

  

1. Created a java class `CourseApiApp.java` in `\src\main\java\com\swarna\springboot`

```java
@SpringBootApplication //annotation to tell Spring , this is a Boot Application
public class CourseApiApp {
	public static void main(String[] args) {
// In background, this will start a servlet container and host my application there
		SpringApplication.run(CourseApiApp.class, args);
	}
}
```

Now run Spring Boot Application using **Run as => Run as Java Application**

If you got error, *"The port may already be in use or the connector may be misconfigured"*. You have to stop that port.

1. Find the process ID (PID) for the port (e.g.: **8080**)

   On Windows: `netstat -ao | find "8080"`

   Other Platforms other than windows : `lsof -i:8080`

2. Kill the process ID you found (e.g.: 20712)

   On Windows: `Taskkill /PID  4624 /F`

   Other Platforms other than windows : `kill -9 20712`  or `kill 20712`





### Adding a REST Controller

Controller is responsible for taking http requests and provide certain http responses.

A java class marked with annotations. It has info about 

- what url it triggers to.
- what method to run when accessed.

```java
@RestController // This will tell Spring that this is a Controller
public class HelloController {

	@RequestMapping("/hello") // This will map the path with the sayHi() function
	public String sayHi() {
		return "Hi"; // We will get "Hi" in http://localhost:8080/hello
	}
}
```



### Returning Objects From Controller

Spring automatically converts REST Controller returning object into JSON. We dont have to do anything.

Created a Topic class named ***Topic.java***

```java
public class Topic {
	private String id;
	private String name;
	private String description;

 // Generated Constructor using fields and without fields
// Also generated Getters and setters and constructor
}
```



Created a controller named ***`TopicConntroller.java`*** , which will return a list of Topics

```java
@RestController
public class TopicController {
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return Arrays.asList(
				new Topic("spring","Spring Framework","Spring Framework Description"),
				new Topic("js","JavaScript","JavaScript Description"),
				new Topic("java","Core Java","Core Java Description")
				);
	}
}

```



### Behind the Scenes 

##### Bill Of Materials

BOM stands for Bill Of Materials. **A BOM is a special kind of POM that is used to control the versions of a project’s dependencies and provide a central place to define and update those versions.**

BOM provides the flexibility to add a dependency to our module without worrying about the version that we should depend on.



##### Embedded Servlet Container

- Convenient 
- Servet container config is now application config
- Standalone application
- Useful for microservices architecture





### Spring MVC- The View Tier

A Spring MVC is a Java framework which is used to build web applications. It follows the Model-View-Controller design pattern. It implements all the basic features of a core spring framework like Inversion of Control, Dependency Injection.

A Spring MVC provides an elegant solution to use MVC in spring framework by the help of **DispatcherServlet**. Here, **DispatcherServlet** is a class that receives the incoming request and maps it to the right resource such as controllers, models, and views.

![Spring MVC Tutorial](https://static.javatpoint.com/sppages/images/spring-web-model-view-controller.png)





### Topic API - The 1st REST Api we'll build

- Resources: **Topic, Course, Lesson**
- A topic can consists of several courses.
- A course can consists of several lessons.

| Request Type | URI        | Description          |
| ------------ | ---------- | -------------------- |
| GET          | /topics    | Get all Topics       |
| GET          | /topics/id | Get a specific Topic |
| POST         | /topics    | Create a new Topic   |
| PUT          | /topics/id | Updates the topic.   |
| DELETE       | /topics/id | Deletes the topic    |



### Creating Business Service

**@Autowired** : Used to tell spring to inject Service class dependency whenever required. We don't have to inject manually as like Angular, we just have to define it.

```java
@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
    ...
}
```

Now we creating Business Service to handle data and rules.

***TopicService.java***

```java
@Service
public class TopicService {

	private List<Topic>  topics= Arrays.asList(
			new Topic("spring","Spring Framework","Spring Framework Description"),
			new Topic("js","JavaScript","JavaScript Description"),
			new Topic("java","Core Java","Core Java Description")
			);
	
	public List<Topic> getAllTopics() {
		return topics;
	}
}
```



***TopicController.java***

```java
@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}
}
```



#### Getting a specific Topic

Add this in ***TopicService.java***

```java
public Topic getTopic(String id) {
    //return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    // The above one line code can be replaced by full code below.
    Topic topic = null;
    for (Topic t : topics) {
        if(t.getId().equals(id)) {
            topic = t;
        }
    }
    return topic;
}
```



Add this in ***TopicController.java***

```java
@RequestMapping("/topics/{id}")
public Topic getTopic(@PathVariable String id) {	
	return topicService.getTopic(id);
}
```

Now, Response I get going into `http://localhost:8080/topics/spring`

```json
{"id":"spring","name":"Spring Framework","description":"Spring Framework Description"}
```



#### Adding new resource using POST

First we need to use mutable string i.e. ArrayList instead of Arrays.List. Unless we will get 500 Error.

Update the List in ***TopicService.java***

```java
private List<Topic> topics = new ArrayList<>(Arrays.asList(
    new Topic("spring", "Spring Framework", "Spring Framework Description"),
    new Topic("js", "JavaScript", "JavaScript Description"),
    new Topic("java", "Core Java", "Core Java Description")));

//Add below code at the end 
public void addTopic(Topic topic) {
    topics.add(topic); //This will add a topic into existing ArrayList topics
}
```

Add this in ***TopicController.java*** to send a POST request.

```java
@RequestMapping(method = RequestMethod.POST,value = "/topics")
public void addTopic(@RequestBody Topic topic ) {
	topicService.addTopic(topic);
}
```

To test this, send 

```json
POST URL : http://localhost:8080/topics
Headers: Content-Type (application/json)

Body : 
{
    "id": "javaee",
    "name": "Java Enterprise",
    "description": "Java Enterprise Description"
}
Response : 200 OK

Now send GET : http://localhost:8080/topics
You will get the added Topic in topics List
```



#### Implementing Update using PUT

Add this in ***TopicController.java*** to send a PUT request.

```java
@RequestMapping(method = RequestMethod.PUT,value = "/topics/{id}")
public void updateTopic(@RequestBody Topic topic , @PathVariable String id) {
    topicService.updateTopic(id, topic);
}
```

Add this in ***TopicService.java*** to UPDATE

```java
public void updateTopic(String id, Topic topic) {
	for (int i = 0; i < topics.size(); i++) {
		Topic t = topics.get(i);
		if(t.getId().equals(id)) {
			topics.set(i, topic);
			return;
		}
	}	
}
```

To test this, send 

```json
PUT URL : http://localhost:8080/topics/js
Headers: Content-Type (application/json)

Body : 
{
    "id": "js",
    "name": "Updated JavaScript",
    "description": "Updated JavaScript Description"
}
Response : 200 OK

Now send GET : http://localhost:8080/topics
You will get the updated Topic in topics List
```



#### Implementing DELETE using DELETE

Add this in ***TopicController.java*** to send a DELETE request.

```java
@RequestMapping(method = RequestMethod.DELETE,value = "/topics/{id}")
public void deleteTopic(@PathVariable String id) {
    topicService.deleteTopic(id);
}
```

Add this in ***TopicService.java*** to DELETE

```java
public void deleteTopic(String id) {
    topics.removeIf(t -> t.getId().equals(id));	
}
```

To test this, send 

```json
DELETE URL : http://localhost:8080/topics/javaee
Headers: Not Required
Body : Not Required

Response : 200 OK

Now send GET : http://localhost:8080/topics
You will get the updated Topics in topics List
```



### Ways to Start a Spring Boot App

- [Spring Initializr](https://start.spring.io/)

- [Spring Boot CLI](https://www.baeldung.com/spring-boot-cli)
- [STS IDE](https://spring.io/tools)



### Using application properties

Created `application.properties` in src/main/resources

```properties
server.port=8080

#used to ignore some error message from apache derby DB
spring.jpa.hibernate.ddl-auto=update
```

Now the app will start using port 8081

Here are some good resources for application.properties file.

- [docs4dev.com](https://www.docs4dev.com/docs/en/spring-boot/2.1.1.RELEASE/reference/common-application-properties.html)

- [Spring Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)





### Spring Data JPA 

To connect our server with database , we need to use Spring Data JPA.

JPA = JAVA Persistence API

Object - Relational Mapping

Added Spring Web, **Data JPA** and **Apache Derby** DB as Database.

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.apache.derby</groupId>
			<artifactId>derby</artifactId>
			<scope>runtime</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
```





#### Creating a Spring Data JPA Repository

Added **@Entity** and **@Id** in ***Topic.java***

@Entity - Used for creating a entity(or Table) named Topic , which having 3 columns - id, name, description

@Id - specify id as a primary key of the table.

```java
@Entity
public class Topic {

	@Id
	private String id;
	private String name;
	private String description;
```



Created a interface ***TopicRepository.java*** which extends ***CrudRepository*** which is having all essential methods for CRUD operations. 

```java
import org.springframework.data.repository.CrudRepository;
public interface TopicRepository extends CrudRepository<Topic, String> {

	//getAllTopics()
	//getTopic(String id)
	//addTopic(Topic topic )
	//updateTopic(@Topic topic ,String id) 
	//deleteTopic(String id)
	// Above methods will be handled by CrudRepository
}
```



#### Making CRUD operations with Repository

- First in ***TopicService.java*** , we have to inject TopicRepository using *@Autowired*

- Delete topics list initialization line. 
- Rewrite code within various methods for GET, POST, PUT, DELETE

***TopicService.java***

```java
@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository; 
    
//	private List<Topic> topics = new ArrayList<>( Arrays.asList(new Topic("spring", "Spring Framework", "Spring Framework Description"), new Topic("js", "JavaScript", "JavaScript Description"),new Topic("java", "Core Java", "Core Java Description")));

	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(t -> topics.add(t)); //findAll returns iterable
		return topics;
	}

	public Optional<Topic> getTopic(String id) {
		return topicRepository.findById(id);
	}

	public void addTopic(Topic topic) {
		topicRepository.save(topic);
	}

	public void updateTopic(String id, Topic topic) {
		topicRepository.save(topic);
	}

	public void deleteTopic(String id) {	
		topicRepository.deleteById(id);
	}
}
```





### Course APIs - Creation

| Request Type | URI                                  | Description          |
| ------------ | ------------------------------------ | -------------------- |
| GET          | /topics/{topicId}/courses            | Get all Topics       |
| GET          | /topics/{topicId}/courses/{courseId} | Get a specific Topic |
| POST         | /topics/{topicId}/courses            | Create a new Topic   |
| PUT          | /topics/{topicId}/courses/{courseId} | Updates the topic.   |
| DELETE       | /topics/{topicId}/courses/{courseId} | Deletes the topic    |



Copied full package  and pasted as 

Renamed Topic to Course accordingly in 4 files. Renamed the URI properly.

Changes of ***Course.java*** : added topic as a column in Course table.

```java
@Entity
public class Course {
	...
	private Topic topic;
	...
	public Course(String id, String name, String description,String topicId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		
        this.topic = new Topic(topicId, "", ""); 
        //This is just for convinience and not really required. creating this to make it easy to create new Course Objects with given topic
	}
    
    public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
```

Changes of ***CourseService.java***

```java
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getAllCourses(String id) {
		List<Course> courses = new ArrayList<>();
		courseRepository.findAll().forEach(c -> courses.add(c));
		return courses;
	}
	public Optional<Course> getCourse(String id) {
		return courseRepository.findById(id);
	}
	public void addCourse(Course course) {
		courseRepository.save(course);
	}
	public void updateCourse(Course course) {
		courseRepository.save(course);
	}
	public void deleteCourse(String id) {
		courseRepository.deleteById(id);
	}
}
```



Changes of ***CourseController.java***

```java
@RestController
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@RequestMapping("/topics/{id}/courses")
	public List<Course> getAllCourses(@PathVariable String id) {
		return courseService.getAllCourses(id);
	}
	
	@RequestMapping("/topics/{topicId}/courses/{id}")
	public Optional<Course> getCourse(@PathVariable String id) {	
		return courseService.getCourse(id);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/topics/{topicId}/courses")
	public void addCourse(@RequestBody Course course, @PathVariable String topicId) {
        //Creating a new Topic object with the given name {topicId}
		course.setTopic(new Topic(topicId, "", ""));
		courseService.addCourse(course);
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/topics/{topicId}/courses/{id}")
	public void updateTopic(@RequestBody Course course , @PathVariable String topicId) {
        //Creating a new Topic object with the given name {topicId}
		course.setTopic(new Topic(topicId, "", ""));
		courseService.updateCourse(course);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/topics/{topicId}/courses/{id}")
	public void deleteCourse(@PathVariable String id) {
		courseService.deleteCourse(id);
	}
}
```



### Adding Entity Relationship and Extending Repository

- Many Courses are linked to same Topic. So this is Many Courses to one Topic relationship.

***Course.java***

```java
@ManyToOne
private Topic topic;
```

- ***CrudRepository*** will automatically takes the portion after **findBy** and search if there is a variable match in Course.java, If yes, then it will implement a method which will find all courses by name. 

```java
public List<Course> findByName(String name);
//This will work for all string properties.
```



- If we want to filter by Topic Id, this not work, because we Topic is a class at its own. So we have to tell spring go in between Topic Class and take its id. 

**findByTopicId = findBy (Syntax for CrudRepository) + Topic(ClassName) + Id(Property Name)**
//CrudRepo will automatically implement this.

**CourseRepository.java** -- we need only define *findByTopicId*(String topicId)

```java
public List<Course> findByTopicId(String topicId);
// Spring JPA will automatically implement this in background
```



To get all the courses under a specific topic, edit **CourseService.java**

```java
public List<Course> getAllCourses(String id) {
    //		List<Course> courses = new ArrayList<>();
    //		courseRepository.findAll().forEach(c -> courses.add(c));
    //		return courses;

    return courseRepository.findByTopicId(id);
}
```





### Packaging Production Ready App

There is a line in `pom.xml`. You can choose the packaging format. JAR dosent need to run on Servlet container. But WAR need servlet container to run.

```xml
<packaging>jar</packaging>
```



To package the Spring Boot app , go to project folder , 

1. list all files and make sure you can see pom.xml

2. ```bash
   swarna@swarna MINGW64 /e/JAVA-Backend/CourseAPI-Spring-Starter (master)
   $ mvn clean install
   ```

3. jar will be created in `target/CourseAPI-Spring-Starter-0.0.1-SNAPSHOT.jar`

You can run the jar file in any *java installed environment* using : 

```bash
swarna@swarna MINGW64 /e/JAVA-Backend/CourseAPI-Spring-Starter (master)
$ java -jar target/CourseAPI-Spring-Starter-0.0.1-SNAPSHOT.jar
```



### Actuator - Monitoring App

Monitoring our app, gathering metrics, understanding traffic, or the state of our database become trivial with this dependency. Actuator is a production-grade monitoring tool

Actuator is mainly used to **expose operational information about the running application** — health, metrics, info, dump, env, etc. It uses HTTP endpoints or JMX beans to enable us to interact with it.

Adding to ***POM.xml***

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

**Actuator Links** 

```json
http://localhost:8080/actuator
http://localhost:8080/actuator/health
http://localhost:8080/beans
```


