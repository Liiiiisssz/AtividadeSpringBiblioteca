package br.com.centroweg.biblioteca.mapper;

import br.com.centroweg.biblioteca.dto.emprestimo.EmprestimoRequisicaoDTO;
import br.com.centroweg.biblioteca.dto.emprestimo.EmprestimoRespostaDTO;
import br.com.centroweg.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmprestimoMapper {
    public Emprestimo toEntity(EmprestimoRequisicaoDTO requisicao){
        return new Emprestimo(
                requisicao.livroId(),
                requisicao.usuarioId(),
                requisicao.dataEmprestimo(),
                requisicao.dataDevolucao()
        );
    }

    public EmprestimoRespostaDTO toDTO(Emprestimo emprestimo){
        return new EmprestimoRespostaDTO(
                emprestimo.getId(),
                emprestimo.getLivroId(),
                emprestimo.getUsuarioId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()
        );
    }

    public List<EmprestimoRespostaDTO> toDTOList(List<Emprestimo> emprestimos){
        return emprestimos.stream()
                .map(this::toDTO)
                .toList();
    }
}
