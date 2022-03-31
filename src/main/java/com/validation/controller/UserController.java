package com.validation.controller;

import com.validation.dto.request.RegistrationRequestDto;
import com.validation.dto.UserDto;
import com.validation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @ApiResponse(responseCode = "200", description = "Список пользователей был получен",
            content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            })
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(
                userService.getAllUsers().stream()
                .map(UserDto::fromUser)
                        .collect(Collectors.toList())
        );
    }

    @Operation(summary = "Возвращает пользователя с указанным id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно найден",
                    content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Пользователь с указанным id не существует",
                    content = @Content
            )
    })
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(
                UserDto.fromUser(userService.getUser(id))
        );
    }

    @Operation(summary = "Регистрация пользователя")
    @ApiResponse(responseCode = "201", description = "Пользователь зарегистрирован",
            content = {
            @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDto.class))
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> registerUser(@RequestBody RegistrationRequestDto request) {
        userService.register(request);
        return ResponseEntity.ok(UserDto.fromUser(userService.getUserByUsername(request.getUsername())));
    }

    @Operation(summary = "Удаляет пользователя с указанным id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно удален",
                    content = @Content
            )}
    )
    @DeleteMapping(value = "{id}")
    public void removeUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }

}
