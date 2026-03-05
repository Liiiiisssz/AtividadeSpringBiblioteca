package br.com.centroweg.biblioteca.dto.emprestimo;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record EmprestimoRequisicaoDTO(
        @NotNull(message = "O id do livro não pode ser nulo")
        Integer livroId,
        @NotNull(message = "O id do usuário não pode ser nulo")
        Integer usuarioId,
        @PastOrPresent(message = "A data do emprestimo não pode ser futura")
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
