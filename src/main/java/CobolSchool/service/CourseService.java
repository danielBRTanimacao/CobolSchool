package CobolSchool.service;

import CobolSchool.DTOs.courses.*;
import CobolSchool.entities.CourseEntity;
import CobolSchool.repository.CourseRepository;
import CobolSchool.repository.LessonRepository;
import CobolSchool.utils.StorageProcess;
import CobolSchool.utils.customs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository repository;
    private final LessonRepository lessonRepository;
    private final StorageProcess strProcess;

    public void saveCourse(RequestCourseDTO data) {
        log.info("Starting course creation for title: {}", data.title());

        CourseEntity course = new CourseEntity();
        course.setTitle(data.title());

        String fileName = strProcess.storeFile(data.image());
        course.setThumbnailPath(fileName);

        CourseEntity savedCourse = repository.save(course);
        log.info("Course successfully created with ID: {}", savedCourse.getId());
    }

    public CourseEntity getCourse(Long id) {
        log.info("Fetching course with ID: {}", id);
        return repository.findById(id).orElseThrow(() -> {
            log.warn("Fetch failed: Course with ID {} not found", id);
            return new NotFoundException("Course not found");
        });
    }

    public void deleteCourse(Long id) {
        log.info("Attempting to delete course with ID: {}", id);
        CourseEntity course = repository.findById(id).orElseThrow(() -> {
            log.warn("Delete failed: Course with ID {} not found", id);
            return new NotFoundException("Course not found");
        });

        repository.delete(course);
        log.info("Course with ID {} successfully deleted", id);
    }

    public void updateCourse(RequestUpdateCourseDTO data, Long id) {
        log.info("Starting update for course with ID: {}", id);

        CourseEntity course = repository.findById(id).orElseThrow(() -> {
            log.warn("Update failed: Course with ID {} not found", id);
            return new NotFoundException("Course not found");
        });

        if (data.title() != null) {
            log.debug("Updating title for course ID {} to: {}", id, data.title());
            course.setTitle(data.title());
        }

        if (data.thumb() != null) {
            log.debug("Updating thumbnail for course ID {}", id);
            String fileName = strProcess.storeFile(data.thumb());
            course.setThumbnailPath(fileName);
        }

        if (data.lesson() != null) {
            log.info("Attempting to add lesson ID {} to course ID {}", data.lesson(), id);
            var lesson = lessonRepository.findById(data.lesson()).orElseThrow(() -> {
                log.warn("Failed to add lesson: Lesson with ID {} not found", data.lesson());
                return new NotFoundException("Lesson not found");
            });
            course.addLesson(lesson);
        }

        repository.save(course);
        log.info("Course with ID {} successfully updated", id);
    }

    public Page<CourseEntity> getAllCourses(int page, int size) {
        log.info("Fetching paginated courses. Page: {}, Size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
}