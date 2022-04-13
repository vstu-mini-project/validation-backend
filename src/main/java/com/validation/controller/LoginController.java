package com.validation.controller;

import com.validation.dto.documents.request.AuthenticationRequestDto;
import com.validation.model.User;
import com.validation.security.jwt.JwtTokenProvider;
import com.validation.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/auth/", produces = APPLICATION_JSON_VALUE)
@CrossOrigin
public class LoginController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LoginController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "Проверка пользователя и авторизация")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Авторизация прошла успешно",
                    content = {
                    @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AuthenticationRequestDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Ошибка: Данные запроса не действительны",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Ошибка: Пользователь не найден",
                    content = @Content
            )
    })
    @PostMapping("login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto) {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findUserByUsername(username);
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
    }
}
