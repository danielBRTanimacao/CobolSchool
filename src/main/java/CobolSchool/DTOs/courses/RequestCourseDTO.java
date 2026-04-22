package CobolSchool.DTOs.courses;

import jakarta.validation.constraints.NotBlank;

public record RequestCourseDTO(
        @NotBlank
        String title
) {
}
