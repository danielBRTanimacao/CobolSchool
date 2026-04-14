package CobolSchool.controller;

import CobolSchool.DTOs.lessons.RequestLessonDTO;
import CobolSchool.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService service;

    @PostMapping
    ResponseEntity<Void> createLesson(@Valid @RequestBody RequestLessonDTO dto) {
        service.saveLesson(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
