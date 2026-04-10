package CobolSchool.service;

import CobolSchool.DTOs.courses.RequestCourseDTO;
import CobolSchool.entities.CourseEntity;
import CobolSchool.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    public void saveCourse(RequestCourseDTO data) {

        CourseEntity course = new CourseEntity();
        course.setTitle(data.title());
        course.setImage(data.image());

        repository.save(course);
    }
}
