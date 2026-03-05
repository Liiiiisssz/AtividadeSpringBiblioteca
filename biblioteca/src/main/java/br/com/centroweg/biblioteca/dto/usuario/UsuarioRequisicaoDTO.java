package br.com.centroweg.biblioteca.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequisicaoDTO(
        @NotBlank(message = "O nome não pode estar vazio")
        String nome,
        @Email(message = "Formato de email inválido")
        String email
) {
}
