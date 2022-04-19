package com.validation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(Exception.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception e, WebRequest request) throws Exception {
        log.error(String.format(
                        "\n/********************************************************************************************************************/\n" +
                                "* %-114s *\n" +
                                "* %-114s *\n" +
                                "/********************************************************************************************************************/",
                        "В процессе выполнения программы возникло исключение",
                        e.getMessage()
                )
        );
        e.printStackTrace();
        return handleException(e, request);
    }
}
