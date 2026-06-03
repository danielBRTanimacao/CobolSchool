package CobolSchool.controller;

import CobolSchool.DTOs.users.RequestLoginUserDTO;
import CobolSchool.DTOs.users.RequestUserDTO;
import CobolSchool.DTOs.users.ResponseUserDTO;
import CobolSchool.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
