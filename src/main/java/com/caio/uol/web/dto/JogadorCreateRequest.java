package com.caio.uol.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record JogadorCreateRequest(
        @NotBlank(message = "nome não pode ser vazio")
        String nome,

        @Email(message = "email precisa ser bem formado")
        @NotBlank(message = "email não pode ser vazio")
        String email,

        @NotBlank(message = "telefone não pode ser vazio")
        String telefone,

        @NotBlank(message = "time precisa ser escolhido")
        String time
) {}