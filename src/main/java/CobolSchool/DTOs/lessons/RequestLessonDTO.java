package CobolSchool.DTOs.lessons;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record RequestLessonDTO(
        @NotBlank
        String name,
        MultipartFile image,
        @NotNull
        MultipartFile video
) {
}
