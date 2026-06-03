package CobolSchool.service;

import CobolSchool.DTOs.users.ResponseUserDTO;
import CobolSchool.entities.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public ResponseUserDTO login(RequestUserDTO data) {
        UserEntity user = new UserEntity();

        log.info("Login attempt: {}", user.getEmail());
        AdminEntity adminEntity = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> {
                    log.error("User not found: {}", user.getEmail());
                    return new EntityNotFoundException("User with email " + user.getEmail() + " not found");
                });

        if (!this.passwordEncoder.matches(user.getPassword(), adminEntity.getPassword())) {
            log.error("Incorrect password for user {}", adminEntity.getEmail());
            throw new IllegalArgumentException("Invalid email or password");
        }

        userRepository.save(adminEntity);
        log.info("User {} successfully verified.", adminEntity.getEmail());

        String token = this.tokenService.generateToken(adminEntity);
        log.info("JWT generated for user {}", adminEntity.getEmail());

        return new ResponseLoginDTO(adminEntity.getId(), adminEntity.getUsername(), token);
    }

    public void createUser() {

    }

    public void updateUser() {

    }

    public void deleteUser() {

    }
}
