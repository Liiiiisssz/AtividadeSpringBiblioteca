package br.com.centroweg.biblioteca.dto.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequisicaoDTO(
        @NotBlank(message = "O titulo do livro não pode estar vazio")
        String titulo,
        @NotBlank(message = "O autor do livro não pode estar vazio")
        String autor,
        @NotNull(message = "O ano de publicação não pode ser nulo")
        int anoPublicacao
) {
}
