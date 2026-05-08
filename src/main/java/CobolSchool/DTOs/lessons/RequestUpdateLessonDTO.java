package CobolSchool.DTOs.lessons;

import org.springframework.web.multipart.MultipartFile;

public record RequestUpdateLessonDTO(
        String name,
        MultipartFile image,
        MultipartFile video
) {
}
