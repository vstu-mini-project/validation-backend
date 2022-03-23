package com.validation.controller;

import com.validation.dto.UserDto;
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
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/users/", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Возвращает список всех пользователей")
    @ApiResponse(
            responseCode = "200",
            description = "Список был получен",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.class))})
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.findAll().stream().map(UserDto::fromUser).collect(Collectors.toList()));
    }

    @Operation(summary = "Возвращает пользователя с указанным id")
    @ApiResponse(
            responseCode = "200",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDto.class))})
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Регистрирует пользователя")
    @ApiResponse(
            responseCode = "201",
            description = "Пользователь зарегистрирован",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        if (user.getUsername().isEmpty() || user.getUsername().isBlank()
                || user.getPassword().isEmpty() || user.getPassword().isBlank())
            return ResponseEntity.badRequest().build();
        if (userService.findByUsername(user.getUsername()) != null)
            return ResponseEntity.badRequest().build();
        User createdUser = userService.register(user);
        if (createdUser == null)
            return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<User> removeUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
        if (userService.findById(id) == null)
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
