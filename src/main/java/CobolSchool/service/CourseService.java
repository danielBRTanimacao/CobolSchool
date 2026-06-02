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
        log.debug("Creating course entity");
        CourseEntity course = new CourseEntity();
        course.setTitle(data.title());

        log.debug("generating file save root path");
        String fileName = strProcess.storeFile(data.image());
        course.setThumbnailPath(fileName);

        repository.save(course);
    }

    public CourseEntity getCourse(Long id) {
        log.debug("find specific course by Id");
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found")
        );
    }

    public void deleteCourse(Long id) {
        log.debug("Deleting course by Id");
        CourseEntity course = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found")
        );
        repository.delete(course);
    }

    public void updateCourse(RequestUpdateCourseDTO data, Long id) {
        log.debug("Find course by id");
        CourseEntity course = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found")
        );

        log.debug("Find not null data to set new characters");
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
