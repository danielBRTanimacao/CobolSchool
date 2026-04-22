package CobolSchool.service;

import CobolSchool.DTOs.courses.RequestCourseDTO;
import CobolSchool.DTOs.courses.RequestUpdateCourseDTO;
import CobolSchool.entities.CourseEntity;
import CobolSchool.repository.CourseRepository;
import CobolSchool.repository.LessonRepository;
import CobolSchool.utils.customs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final LessonRepository lessonRepository;

    public void saveCourse(RequestCourseDTO data) {
        CourseEntity course = new CourseEntity();
        course.setTitle(data.title());
        course.setThumbnailPath(data.image());

        repository.save(course);
    }

    public void updateCourse(RequestUpdateCourseDTO data, Long id) {
        CourseEntity course = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found")
        );

        course.setTitle(data.title());
        course.setThumbnailPath(data.thumb());

        if (data.lesson() != null) {
            var lesson = lessonRepository.findById(data.lesson())
                    .orElseThrow(() -> new NotFoundException("Lesson not found"));
            course.addLesson(lesson);
        }

        repository.save(course);
    }

    public Page<CourseEntity> getAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
}
