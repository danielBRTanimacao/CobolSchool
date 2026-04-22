package CobolSchool.DTOs.courses;

import CobolSchool.entities.LessonEntity;

public record RequestUpdateCourseDTO(
        String title,
        String thumb,
        LessonEntity lesson
) {
}
