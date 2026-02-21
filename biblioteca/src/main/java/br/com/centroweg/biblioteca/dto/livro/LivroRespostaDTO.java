package br.com.centroweg.biblioteca.dto.livro;

public record LivroRespostaDTO(
        Integer id,
        String titulo,
        String autor,
        int anoPublicacao
) {
}
