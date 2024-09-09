package com.as.rest.webservices.service;

import com.as.rest.webservices.dto.UserDTO;
import com.as.rest.webservices.exception.UserCreationException;
import com.as.rest.webservices.mapper.DTOMapper;
import com.as.rest.webservices.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger("UserServiceImpl");

    private final List<User> users = new ArrayList<>(
            Arrays.asList(
                    new User(1, "root", LocalDate.now().minusYears(29)),
                    new User(2, "admin", LocalDate.now().minusYears(28)),
                    new User(3, "aman", LocalDate.now().minusYears(27)))
    );
    private int userCounter = 3;

    private int userIdGenerator(){
        return ++userCounter;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        logger.info("getAllUsers | status : started");
        List<UserDTO> userDTOS = new ArrayList<>();
        this.users.forEach(user -> userDTOS.add(DTOMapper.INSTANCE.userToUserDTO(user)));
        logger.info("getAllUsers | status : done");
        return userDTOS;
    }

    @Override
    public UserDTO getUserById(int id) {
        logger.info("getUserById | status : started");
        Predicate<User> predicate = user -> user.getId() == id;
        Optional<User> user = users.stream().filter(predicate).findFirst();
        logger.info("getUserById | status : done");
        return user.map(DTOMapper.INSTANCE::userToUserDTO).orElse(null);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        logger.info("createUser | status : started | userDTO : {}", userDTO);
        String userName = userDTO.getName();
        try{
            Predicate<User> predicate = user -> user.getName().equals(userName);
            Optional<User> userOptional = users.stream().filter(predicate).findFirst();
            if(userOptional.isPresent()){
                throw new UserCreationException(String.format("User with name %s already exists", userDTO.getName()));
            }else{
                userDTO.setId(userIdGenerator());
                users.add(DTOMapper.INSTANCE.userDTOToUser(userDTO));

            }
        }catch(UserCreationException e){
            logger.error("createUser | status : error | message : userAlreadyExists");
            throw e;
        } catch (Exception e){
            logger.error("createUser | status : error | message : uncaught exception");
            throw new UserCreationException(String.format("Error occurred while creating for user %s", userName), e);
        }
        logger.info("createUser | status : done");
        return userDTO;
    }

    @Override
    public UserDTO deleteUserById(int id) {
        UserDTO userDTO = null;
        logger.info("deleteUserById | status : started");
        OptionalInt optionalInt = IntStream.range(0, users.size()).filter(index -> users.get(index).getId() == id).findFirst();
        if(optionalInt.isPresent()){
            logger.info("optionalInt : {}", optionalInt.getAsInt());
            userDTO = DTOMapper.INSTANCE.userToUserDTO(users.get(optionalInt.getAsInt()));
            logger.info("optionalInt : {}", optionalInt.getAsInt());
            users.remove(optionalInt.getAsInt());
            logger.info("deleteUserById | status : deleted | deleted user : {}", userDTO);
        }else{
            logger.info("deleteUserById | status : error |user with id {} not found", id);
        }
        return userDTO;
    }
}
