package com.swarna.courseapi.lesson;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swarna.courseapi.course.Course;

@RestController
public class LessonController {
	
	@Autowired
	private LessonService lessonService;
	
	@RequestMapping("/topics/{topicId}/courses/{courseId}/lessons")
	public List<Lesson> getAllLessons(@PathVariable String courseId) {
		return lessonService.getAllLessons(courseId);
	}
	
	@RequestMapping("/topics/{topicId}/courses/{courseId}/lessons/{lessonId}")
	public Optional<Lesson> getLesson(@PathVariable String lessonId) {	
		return lessonService.getLesson(lessonId);
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "/topics/{topicId}/courses/{courseId}/lessons")
	public void addLesson(@RequestBody Lesson lesson, @PathVariable String topicId, @PathVariable String courseId) {
		//Creating a new Topic object with the given name {topicId}
		lesson.setCourse(new Course(courseId, "", "",topicId));
		lessonService.addLesson(lesson);
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/topics/{topicId}/courses/{courseId}/lessons/{lessonId}")
	public void updateTopic(@RequestBody Lesson lesson, @PathVariable String topicId, @PathVariable String courseId) {
		//Creating a new Topic object with the given name {topicId}
		lesson.setCourse(new Course(courseId, "", "",topicId));
		lessonService.updateLesson(lesson);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/topics/{topicId}/courses/{courseId}/lessons/{lessonId}")
	public void deleteLesson(@PathVariable String lessonId) {
		lessonService.deleteLesson(lessonId);
	}
	
}

