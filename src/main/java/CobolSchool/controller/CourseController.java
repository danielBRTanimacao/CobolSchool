package CobolSchool.controller;

import CobolSchool.DTOs.courses.RequestCourseDTO;
import CobolSchool.DTOs.courses.RequestUpdateCourseDTO;
import CobolSchool.entities.CourseEntity;
import CobolSchool.service.CourseService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @GetMapping
    ResponseEntity<Page<CourseEntity>> listAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok().body(service.getAllCourses(page, size));
    }

    @PostMapping
    ResponseEntity<Void> createCourse(@Valid @RequestBody RequestCourseDTO dto) {
        service.saveCourse(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updtCourse(@RequestBody RequestUpdateCourseDTO dto, @PathVariable Long id) {
        service.updateCourse(dto, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
