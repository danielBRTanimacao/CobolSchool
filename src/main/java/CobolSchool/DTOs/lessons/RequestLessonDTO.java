package CobolSchool.DTOs.lessons;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record RequestLessonDTO(
        @NotBlank
        String name,
        MultipartFile image,
        String video
) {
}
