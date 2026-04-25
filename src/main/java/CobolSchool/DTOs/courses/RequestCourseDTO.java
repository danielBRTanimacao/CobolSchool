package CobolSchool.DTOs.courses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record RequestCourseDTO(
        @NotBlank
        String title,
        @NotNull
        MultipartFile image
) {
}
