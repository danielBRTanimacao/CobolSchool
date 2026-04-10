package CobolSchool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseController {

    @GetMapping
    void foo() {
        System.out.println("opa");
    }
}
