package CobolSchool.controller;

import CobolSchool.DTOs.courses.RequestCourseDTO;
import CobolSchool.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @GetMapping
    void createCourse(@RequestBody RequestCourseDTO dto) {
        service.saveCourse(dto);
    }
}
