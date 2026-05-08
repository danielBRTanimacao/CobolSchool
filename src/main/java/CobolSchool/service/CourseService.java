package CobolSchool.service;

import CobolSchool.DTOs.courses.RequestCourseDTO;
import CobolSchool.DTOs.courses.RequestUpdateCourseDTO;
import CobolSchool.entities.CourseEntity;
import CobolSchool.repository.CourseRepository;
import CobolSchool.repository.LessonRepository;
import CobolSchool.utils.StorageProcess;
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

    private final StorageProcess strProcess;

    public void saveCourse(RequestCourseDTO data) {
        CourseEntity course = new CourseEntity();
        course.setTitle(data.title());

        String fileName = strProcess.storeFile(data.image());
        course.setThumbnailPath(fileName);

        repository.save(course);
    }

    public void deleteCourse(Long id) {
        CourseEntity course = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found")
        );
        repository.delete(course);
    }

    public void updateCourse(RequestUpdateCourseDTO data, Long id) {
        CourseEntity course = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found")
        );

        if (data.title() != null) {
            course.setTitle(data.title());
        }

        if (data.thumb() != null) {
            String fileName = strProcess.storeFile(data.thumb());
            course.setThumbnailPath(fileName);
        }

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
