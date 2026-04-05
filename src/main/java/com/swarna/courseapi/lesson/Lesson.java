package com.swarna.courseapi.lesson;

import com.swarna.courseapi.course.Course;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

	@Id
	private String id;
	private String name;
	private String description;
	
	@ManyToOne
	private Course course;
	
	
}
