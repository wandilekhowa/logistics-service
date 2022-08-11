package com.klasha.logistics.controller;

import com.klasha.logistics.common.Crypto;
import com.klasha.logistics.common.Swagger2Config;
import com.klasha.logistics.dto.LoginDto;
import com.klasha.logistics.dto.UserRegistrationDto;
import com.klasha.logistics.model.User;
import com.klasha.logistics.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
@Api(tags = {Swagger2Config.TAG_1}, value = "Administer all actions performed on a user")
public class UserServiceController {

    @Value("${encryption.auth.salt}")
    public String salt;

    private final UserService userService;

    public UserServiceController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created new User", response = User.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 502, message = "Gateway error")
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDto request) {
        if (request == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(request.getEmail()))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(request.getPassword()))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(request.getName()))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        User user = userService.getUser(request.getEmail());
        if (user != null)
            return new ResponseEntity<>(userService.updateUser(user, request), HttpStatus.OK);

        return new ResponseEntity<>(userService.register(request), HttpStatus.OK);
    }

    @ApiOperation(value = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged in as User", response = Boolean.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 502, message = "Gateway error")
    })
    @PostMapping(value = "/login",consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> login(@RequestBody LoginDto request) {
        if (request == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        User user = userService.getUser(request.getEmail());

        if(user != null && user.getPassword().equals(Crypto.getHashPassword(request.getPassword(), salt))) {
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }

        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.UNAUTHORIZED);
    }

}
