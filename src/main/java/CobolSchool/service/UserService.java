package CobolSchool.service;

import CobolSchool.DTOs.users.RequestLoginUserDTO;
import CobolSchool.DTOs.users.RequestUserDTO;
import CobolSchool.DTOs.users.ResponseUserDTO;
import CobolSchool.entities.UserEntity;
import CobolSchool.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    public ResponseUserDTO login(RequestLoginUserDTO user) {
        log.info("Login attempt: {}", user.username());
        UserEntity userEntity = repository.findByUsername(user.username())
                .orElseThrow(() -> {
                    log.error("User not found: {}", user.username());
                    return new EntityNotFoundException("User with name " + user.username() + " not found");
                });

        if (!this.passwordEncoder.matches(user.password(), userEntity.getPassword())) {
            log.error("Incorrect password for user {}", userEntity.getEmail());
            throw new IllegalArgumentException("Invalid email or password");
        }

        repository.save(userEntity);
        log.info("User {} successfully verified.", userEntity.getEmail());

        String token = this.tokenService.generateToken(userEntity);
        log.info("JWT generated for user {}", userEntity.getEmail());

        return new ResponseUserDTO(token);
    }

    public void createUser(RequestUserDTO user) {

    }

    public void updateUser() {

    }

    public void deleteUser() {

    }
}
