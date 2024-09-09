package com.as.rest.webservices.dto;

import com.as.rest.webservices.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserDeleteResponse {

    private LocalDateTime timestamp;
    private String message;
    private UserDTO deletedUser;
}
