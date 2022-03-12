package com.validation.controller;

import com.validation.model.User;
import com.validation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "api/v1/users", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Returns a list of users")
    @ApiResponse(
            responseCode = "200",
            description = "Order was updated",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))})
    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    public Response addUser(User user) {

    }
}
