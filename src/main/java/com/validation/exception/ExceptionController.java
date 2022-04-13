package com.validation.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class ExceptionController implements ErrorController {
    private static final String PATH = "/error";

    private final ErrorAttributes errorAttributes;

    public ExceptionController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(ExceptionController.PATH)
    public ResponseEntity<ExceptionDto> error(WebRequest request) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(
                request,
                ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.EXCEPTION,
                        ErrorAttributeOptions.Include.MESSAGE
                )
        );

        return ResponseEntity
                .status((Integer) attributes.get("status"))
                .body(
                        ExceptionDto.builder()
                                .error((String) attributes.get("error"))
                                .errorDescription((String) attributes.get("message"))
                                .build()
                );
    }
}
