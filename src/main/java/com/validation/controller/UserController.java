package com.validation.controller;

import com.validation.model.User;
import com.validation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            description = "User was updated",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))})
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Save new user to DB")
    @ApiResponse(
            responseCode = "201",
            description = "User is created",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        if(user.getUsername().isEmpty() || user.getUsername().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
