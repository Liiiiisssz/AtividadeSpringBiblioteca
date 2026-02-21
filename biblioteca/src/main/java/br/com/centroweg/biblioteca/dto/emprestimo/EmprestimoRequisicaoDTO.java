package br.com.centroweg.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record EmprestimoRequisicaoDTO(
        Integer livroId,
        Integer usuarioId,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
