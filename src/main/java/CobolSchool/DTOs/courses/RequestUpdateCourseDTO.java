package CobolSchool.DTOs.courses;

public record RequestUpdateCourseDTO(
        String title,
        String thumb,
        Long lesson
) {
}
