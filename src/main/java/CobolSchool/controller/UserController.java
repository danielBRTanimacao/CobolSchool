package CobolSchool.controller;

import CobolSchool.DTOs.users.RequestLoginUserDTO;
import CobolSchool.DTOs.users.RequestUserDTO;
import CobolSchool.DTOs.users.ResponseUserDTO;
import CobolSchool.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RequestUserDTO data) {
        service.createUser(data);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseUserDTO> loginUser(@RequestBody RequestLoginUserDTO data) {
        return ResponseEntity.ok().body(service.login(data));
    }

    @PutMapping
    public ResponseEntity<Void> updateUser() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> delUser(@PathVariable UUID id, Authentication auth) {
        service.deleteUser(id, auth);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
