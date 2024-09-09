package com.as.rest.webservices.controller;

import com.as.rest.webservices.dto.UserDTO;
import com.as.rest.webservices.dto.UserDeleteResponse;
import com.as.rest.webservices.exception.UserNotFoundException;
import com.as.rest.webservices.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class SocialAppController {

    private final UserService userService;

    @Autowired
    public SocialAppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping( "/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping( "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id){
        UserDTO userDTO = userService.getUserById(id);
        if(userDTO != null){
            return ResponseEntity.ok(userDTO);
        }else{
            throw new UserNotFoundException(String.format("No user found with id %d", id));
        }
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.createUser(userDTO));
    }

    @DeleteMapping( "/{id}")
    public ResponseEntity<UserDeleteResponse> deleteUserById(@PathVariable("id") int id){
        UserDTO userDTO = userService.deleteUserById(id);
        if(userDTO != null){
            return ResponseEntity.ok(UserDeleteResponse.builder()
                            .timestamp(LocalDateTime.now())
                            .message("User deleted")
                            .deletedUser(userDTO)
                            .build());
        }else{
            throw new UserNotFoundException(String.format("No user found with id %d to delete", id));
        }
    }

}
