package br.com.centroweg.biblioteca.service;

import br.com.centroweg.biblioteca.dto.usuario.UsuarioRequisicaoDTO;
import br.com.centroweg.biblioteca.dto.usuario.UsuarioRespostaDTO;
import br.com.centroweg.biblioteca.mapper.UsuarioMapper;
import br.com.centroweg.biblioteca.model.Usuario;
import br.com.centroweg.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UsuarioRespostaDTO save(UsuarioRequisicaoDTO requisicaoDto) throws SQLException{
        Usuario user = mapper.toEntity(requisicaoDto);
        return mapper.toDTO(repository.save(user));
    }

    public List<UsuarioRespostaDTO> getAll() throws SQLException{
        return mapper.toDTOList(repository.getAll());
    }

    public UsuarioRespostaDTO getByid(Integer id) throws SQLException{
        return mapper.toDTO(repository.getById(id));
    }

    public UsuarioRespostaDTO update(UsuarioRequisicaoDTO requisicaoDTO, Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Usuário não existe");
        }
        Usuario usuario = mapper.toEntity(requisicaoDTO);
        usuario.setId(id);
        repository.update(usuario);
        return mapper.toDTO(usuario);
    }

    public void delete(Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Usuário não existe");
        }
        repository.delete(id);
    }
}
