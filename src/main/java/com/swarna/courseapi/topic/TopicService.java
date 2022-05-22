package com.swarna.courseapi.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository; 

//	private List<Topic> topics = new ArrayList<>(
//			Arrays.asList(new Topic("spring", "Spring Framework", "Spring Framework Description"),
//					new Topic("js", "JavaScript", "JavaScript Description"),
//					new Topic("java", "Core Java", "Core Java Description")));
	

	public List<Topic> getAllTopics() {
		//return topics;
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(t -> topics.add(t));
		return topics;
	}

	public Optional<Topic> getTopic(String id) {
		// return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		// The above one line code can be replaced by full code below.
//		Topic topic = null;
//		for (Topic t : topics) {
//			if (t.getId().equals(id)) {
//				topic = t;
//			}
//		}
//		return topic;
		
		return topicRepository.findById(id);
	}

	public void addTopic(Topic topic) {
		//topics.add(topic);
		topicRepository.save(topic);
	}

	public void updateTopic(String id, Topic topic) {
//		for (int i = 0; i < topics.size(); i++) {
//			Topic t = topics.get(i);
//			if(t.getId().equals(id)) {
//				topics.set(i, topic);
//				return;
//			}
//		}
		topicRepository.save(topic);
	}

	public void deleteTopic(String id) {
		//topics.removeIf(t -> t.getId().equals(id));	
		topicRepository.deleteById(id);
	}

}

