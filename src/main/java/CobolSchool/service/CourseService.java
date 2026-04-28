package CobolSchool.service;

import CobolSchool.DTOs.courses.RequestCourseDTO;
import CobolSchool.DTOs.courses.RequestUpdateCourseDTO;
import CobolSchool.entities.CourseEntity;
import CobolSchool.repository.CourseRepository;
import CobolSchool.repository.LessonRepository;
import CobolSchool.utils.StorageProcess;
import CobolSchool.utils.customs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


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
