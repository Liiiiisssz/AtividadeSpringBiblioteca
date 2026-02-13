package br.com.centroweg.biblioteca.service;


import br.com.centroweg.biblioteca.model.Livro;
import br.com.centroweg.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {
    private final LivroRepository repository;
    public LivroService(LivroRepository dao) {
        this.repository = dao;
    }

    public Livro save(Livro livro) throws SQLException{
        return repository.save(livro);
    }

    public List<Livro> getAll() throws SQLException{
        return repository.getAll();
    }

    public Livro getById(Integer id) throws SQLException{
        return repository.getById(id);
    }

    public Livro update(Livro livro, Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Livro não existe");
        }
        livro.setId(id);
        repository.update(livro);
        return livro;
    }

    public void delete(Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Livro não existe");
        }
        repository.delete(id);
    }
}
