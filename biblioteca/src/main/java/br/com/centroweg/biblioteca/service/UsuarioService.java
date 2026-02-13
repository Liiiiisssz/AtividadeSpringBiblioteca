package br.com.centroweg.biblioteca.service;

import br.com.centroweg.biblioteca.model.Usuario;
import br.com.centroweg.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario save(Usuario usuario) throws SQLException{
        return repository.save(usuario);
    }

    public List<Usuario> getAll() throws SQLException{
        return repository.getAll();
    }

    public Usuario getByid(Integer id) throws SQLException{
        return repository.getById(id);
    }

    public Usuario update(Usuario usuario, Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Usuário não existe");
        }
        usuario.setId(id);
        repository.update(usuario);
        return usuario;
    }

    public void delete(Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Usuário não existe");
        }
        repository.delete(id);
    }
}
