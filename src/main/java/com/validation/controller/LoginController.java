package com.validation.controller;

import com.validation.model.User;
import com.validation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/auth")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Check if user exist and login")
    @ApiResponse(
            responseCode = "200",
            description = "Авторизация прошла успешно",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))})
    @PostMapping
    public ResponseEntity<User> login(@Valid @RequestBody User user) {
        return ResponseEntity.ok(user);
    }
}
