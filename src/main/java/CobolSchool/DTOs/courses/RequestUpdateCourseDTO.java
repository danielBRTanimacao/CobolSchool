package CobolSchool.DTOs.courses;

import CobolSchool.entities.LessonEntity;

import java.util.List;

public record RequestUpdateCourseDTO(
        String title,
        String thumb,
        List<LessonEntity> lesson
) {
}
