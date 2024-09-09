package com.as.rest.webservices.service;

import com.as.rest.webservices.dto.UserDTO;
import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO deleteUserById(int id);

}
