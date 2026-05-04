package CobolSchool.controller;

import CobolSchool.DTOs.lessons.RequestLessonDTO;
import CobolSchool.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> createLesson(@Valid @ModelAttribute RequestLessonDTO dto) {
        service.saveLesson(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
