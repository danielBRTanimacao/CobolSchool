package CobolSchool.service;

import CobolSchool.DTOs.users.ResponseUserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public ResponseUserDTO login() {
        return new ResponseUserDTO("123");
    }

    public void createUser() {

    }

    public void updateUser() {

    }

    public void deleteUser() {

    }
}
