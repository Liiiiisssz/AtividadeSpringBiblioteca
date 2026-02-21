package br.com.centroweg.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record EmprestimoRespostaDTO(
        Integer id,
        Integer livroId,
        Integer usuarioId,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
