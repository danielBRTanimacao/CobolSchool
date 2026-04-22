package CobolSchool.DTOs.courses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestCourseDTO(
        @NotBlank
        String title,
        @NotNull
        String image
) {
}
