package CobolSchool.service;

import CobolSchool.DTOs.courses.RequestCourseDTO;
import CobolSchool.entities.CourseEntity;
import CobolSchool.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<CourseEntity> getAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
}
