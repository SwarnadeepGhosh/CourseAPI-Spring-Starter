package com.swarna.courseapi.topic;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://courseapi.vercel.app")
@RequestMapping("/api")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TopicController.class);
	
	@RequestMapping("/topics") // GET is default
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	@Transactional(readOnly = true) // True for select, for update/insert, false for default
    @Cacheable("topic-cache")
	public Optional<Topic> getTopic(@PathVariable String id) {
		LOGGER.info("Fetching one topic for particular id " +id);
		return topicService.getTopic(id);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/topics")
	public void addTopic(@RequestBody Topic topic ) {
		topicService.addTopic(topic);
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/topics/{id}")
	public void updateTopic(@RequestBody Topic topic , @PathVariable String id) {
		topicService.updateTopic(id, topic);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/topics/{id}")
	@CacheEvict("topic-cache")
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}
	
}

