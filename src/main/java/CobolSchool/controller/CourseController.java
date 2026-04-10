package CobolSchool.controller;

import CobolSchool.DTOs.courses.RequestCourseDTO;
import CobolSchool.entities.CourseEntity;
import CobolSchool.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @GetMapping
    ResponseEntity<Page<CourseEntity>> listAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok().body(service.getAllCourses(page, size));
    }

    @PostMapping
    ResponseEntity<Void> createCourse(@RequestBody RequestCourseDTO dto) {
        service.saveCourse(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
