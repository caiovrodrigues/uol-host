package com.caio.uol.web.dto;

import jakarta.validation.constraints.NotBlank;

public record JogadorCreateRequest(
        @NotBlank(message = "nome não pode ser vazio")
        String nome,
        @NotBlank(message = "time precisa ser escolhido")
        String time
) {}