package br.com.centroweg.biblioteca.service;

import br.com.centroweg.biblioteca.dto.emprestimo.EmprestimoRequisicaoDTO;
import br.com.centroweg.biblioteca.dto.emprestimo.EmprestimoRespostaDTO;
import br.com.centroweg.biblioteca.mapper.EmprestimoMapper;
import br.com.centroweg.biblioteca.model.Emprestimo;
import br.com.centroweg.biblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {
    private final EmprestimoRepository repository;
    private final EmprestimoMapper mapper;
    public EmprestimoService(EmprestimoRepository repository, EmprestimoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EmprestimoRespostaDTO save(EmprestimoRequisicaoDTO requisicaoDTO) throws SQLException{
        Emprestimo emprestimo = mapper.toEntity(requisicaoDTO);
        if(!repository.livroEmprestado(emprestimo.getLivroId())){
            throw new RuntimeException("Livro já emprestado");
        }
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(null);
        return mapper.toDTO(repository.save(emprestimo));
    }

    public List<EmprestimoRespostaDTO> getAll() throws SQLException{
        return mapper.toDTOList(repository.getAll());
    }

    public EmprestimoRespostaDTO getById(Integer id) throws SQLException{
        return mapper.toDTO(repository.getById(id));
    }

    public EmprestimoRespostaDTO update(EmprestimoRequisicaoDTO requisicaoDTO, Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Emprestimo não existe");
        }
        Emprestimo emprestimo = mapper.toEntity(requisicaoDTO);
        emprestimo.setId(id);
        repository.update(emprestimo);
        return mapper.toDTO(emprestimo);
    }

    public void delete(Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Emprestimo não existe");
        }
        repository.delete(id);
    }

    public EmprestimoRespostaDTO devolucao(EmprestimoRequisicaoDTO requisicaoDTO, Integer id) throws SQLException, IllegalAccessException {
        Emprestimo emprestimo = mapper.toEntity(requisicaoDTO);
        if(!repository.exists(id)){
            throw new RuntimeException("Emprestimo não existe");
        } else if(emprestimo.getDataDevolucao() == null){
            throw new IllegalAccessException("Data de devolução obrigatoria");
        }
        emprestimo.setId(id);
        repository.devolucao(emprestimo);
        return mapper.toDTO(emprestimo);
    }

    public List<EmprestimoRespostaDTO> emprestimosUsuario(Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Emprestimo não existe");
        }
        return mapper.toDTOList(repository.emprestimosUsuario(id));
    }
}
