package br.com.centroweg.biblioteca.mapper;

import br.com.centroweg.biblioteca.dto.usuario.UsuarioRequisicaoDTO;
import br.com.centroweg.biblioteca.dto.usuario.UsuarioRespostaDTO;
import br.com.centroweg.biblioteca.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {
    public Usuario toEntity(UsuarioRequisicaoDTO requisicao){
        return new Usuario(
                requisicao.nome(),
                requisicao.email()
        );
    }

    public UsuarioRespostaDTO toDTO(Usuario user){
        return new UsuarioRespostaDTO(
                user.getId(),
                user.getNome(),
                user.getEmail()
        );
    }

    public List<UsuarioRespostaDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toDTO)
                .toList();
    }
}
