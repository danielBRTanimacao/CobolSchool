package CobolSchool.service;

import CobolSchool.DTOs.users.RequestLoginUserDTO;
import CobolSchool.DTOs.users.RequestUpdateUserDTO;
import CobolSchool.DTOs.users.RequestUserDTO;
import CobolSchool.DTOs.users.ResponseUserDTO;
import CobolSchool.entities.UserEntity;
import CobolSchool.repository.UserRepository;
import CobolSchool.utils.TokenGenerator;
import CobolSchool.utils.customs.AccessDeniedException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final TokenService tokenService;

    private final TokenGenerator generator;

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

    @Transactional
    public void createUser(RequestUserDTO data) {
        UserEntity user = new UserEntity();
        String token = generator.generateRandomCode();

        boolean emailSubmit = true;

        user.setUsername(data.username());
        user.setEmail(data.email());
        if (emailSubmit) {
            user.setTokenMail(token);
        }

    }

    public void updateUser(UUID id, Authentication auth, RequestUpdateUserDTO data) {

    }

    public void deleteUser(UUID id, Authentication authentication) {
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        assert currentUser != null;
        UUID currentUserId = currentUser.getId();

        log.info("User ID {} is requesting account deletion for target ID {}", currentUserId, id);

        if (!id.equals(currentUserId)) {
            log.warn("Access denied: User ID {} attempted to delete user ID {}", currentUserId, id);
            throw new AccessDeniedException("You cannot delete other users.");
        }

        repository.delete(currentUser);
        log.info("User ID {} successfully deleted their own account", id);
    }
}
