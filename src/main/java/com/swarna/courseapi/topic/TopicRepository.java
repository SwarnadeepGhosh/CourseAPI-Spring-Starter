package com.swarna.courseapi.topic;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String> {

	//getAllTopics()
	//getTopic(String id)
	//addTopic(Topic topic )
	//updateTopic(@Topic topic ,String id) 
	//deleteTopic(String id)
	// Above methods will be handled by CrudRepository automatically
	
	
}
