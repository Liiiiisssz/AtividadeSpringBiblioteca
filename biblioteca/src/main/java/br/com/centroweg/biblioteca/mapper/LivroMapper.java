package br.com.centroweg.biblioteca.mapper;

import br.com.centroweg.biblioteca.dto.livro.LivroRequisicaoDTO;
import br.com.centroweg.biblioteca.dto.livro.LivroRespostaDTO;
import br.com.centroweg.biblioteca.model.Livro;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {
    public Livro toEntity(LivroRequisicaoDTO requisicao){
        return new Livro(
                requisicao.titulo(),
                requisicao.autor(),
                requisicao.anoPublicacao()
        );
    }

    public LivroRespostaDTO toDTO(Livro livro){
        return new LivroRespostaDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAnoPublicacao()
        );
    }

    public List<LivroRespostaDTO> toDTOList(List<Livro> livros){
        return livros.stream()
                .map(this::toDTO)
                .toList();
    }
}
