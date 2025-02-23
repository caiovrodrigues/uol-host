package com.caio.uol.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String argumentosNaoValidos(MethodArgumentNotValidException e, BindingResult bindingResult, Model model){
        log.error(e.getMessage());
        Map<String, String> erros = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldErr -> erros.put(fieldErr.getField(), fieldErr.getDefaultMessage()));
        model.addAttribute("erros", erros);
        return "cadastro";
    }

}
