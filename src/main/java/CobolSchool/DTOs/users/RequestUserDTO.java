package CobolSchool.DTOs.users;

public record RequestUserDTO(
        String username,
        String email,
        String password
) {
}
