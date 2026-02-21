package br.com.centroweg.biblioteca.service;


import br.com.centroweg.biblioteca.dto.livro.LivroRequisicaoDTO;
import br.com.centroweg.biblioteca.dto.livro.LivroRespostaDTO;
import br.com.centroweg.biblioteca.mapper.LivroMapper;
import br.com.centroweg.biblioteca.model.Livro;
import br.com.centroweg.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {
    private final LivroRepository repository;
    private final LivroMapper mapper;
    public LivroService(LivroRepository dao, LivroMapper mapper) {
        this.repository = dao;
        this.mapper = mapper;
    }

    public LivroRespostaDTO save(LivroRequisicaoDTO requisicaoDTO) throws SQLException{
        Livro livro = mapper.toEntity(requisicaoDTO);
        return mapper.toDTO(repository.save(livro));
    }

    public List<LivroRespostaDTO> getAll() throws SQLException{
        return mapper.toDTOList(repository.getAll());
    }

    public LivroRespostaDTO getById(Integer id) throws SQLException{
        return mapper.toDTO(repository.getById(id));
    }

    public LivroRespostaDTO update(LivroRequisicaoDTO requisicaoDTO, Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Livro não existe");
        }
        Livro livro = mapper.toEntity(requisicaoDTO);
        livro.setId(id);
        repository.update(livro);
        return mapper.toDTO(livro);
    }

    public void delete(Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Livro não existe");
        }
        repository.delete(id);
    }
}
