package com.swarna.courseapi.lesson;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

	@Autowired
	private LessonRepository lessonRepository;

	public List<Lesson> getAllLessons(String id) {
//		List<Lesson> courses = new ArrayList<>();
//		courseRepository.findAll().forEach(c -> courses.add(c));
//		return courses;
		
		return lessonRepository.findByCourseId(id);
	}

	public Optional<Lesson> getLesson(String id) {
		return lessonRepository.findById(id);
	}

	public void addLesson(Lesson course) {
		lessonRepository.save(course);
	}

	public void updateLesson(Lesson course) {
		lessonRepository.save(course);
	}

	public void deleteLesson(String id) {
		lessonRepository.deleteById(id);
	}

}
