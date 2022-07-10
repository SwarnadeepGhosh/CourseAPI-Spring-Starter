package com.swarna.courseapi.course;

import java.util.List;

// org.springframework.data.repository.Repository
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

	/* getAllTopics() 
	 * getTopic(String id) 
	 * addTopic(Topic topic ) 
	 * updateTopic(@Topic topic ,String id) 
	 * deleteTopic(String id) 
	 * Above methods will be handled by CrudRepository automatically
	 */
	
	/* CrudRepository will automatically takes the portion after findBy and
	 * search if there is a variable match in Course.java, If yes, then it will
	 * implement a method which will find all courses by name. */
	//public List<Course> findByName(String name);
	//This will work for all string properties.

	/*
	 * If we want to filter by Topic Id, this not work, because Topic is a
	 * class at its own. So we have to tell spring go in between Topic Class and take its id. 
	 * findByTopicId = findBy (Syntax for CrudRepository) + Topic (ClassName) + Id (Property Name)
	 * CrudRepo will automatically implement this.
	 */
	public List<Course> findByTopicId(String topicId);
	
	
}
