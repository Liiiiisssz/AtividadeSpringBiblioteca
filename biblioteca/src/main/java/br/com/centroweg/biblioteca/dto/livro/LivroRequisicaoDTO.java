package br.com.centroweg.biblioteca.dto.livro;

public record LivroRequisicaoDTO(
        String titulo,
        String autor,
        int anoPublicacao
) {
}
