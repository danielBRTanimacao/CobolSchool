package CobolSchool.DTOs.courses;

import org.springframework.web.multipart.MultipartFile;

public record RequestUpdateCourseDTO(
        String title,
        MultipartFile thumb,
        Long lesson
) {
}
