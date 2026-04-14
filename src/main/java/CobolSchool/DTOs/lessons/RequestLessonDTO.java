package CobolSchool.DTOs.lessons;

import jakarta.validation.constraints.NotBlank;

public record RequestLessonDTO(
        @NotBlank
        String name
) {
}
