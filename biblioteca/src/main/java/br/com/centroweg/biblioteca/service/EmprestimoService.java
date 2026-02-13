package br.com.centroweg.biblioteca.service;

import br.com.centroweg.biblioteca.model.Emprestimo;
import br.com.centroweg.biblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {
    private final EmprestimoRepository repository;
    public EmprestimoService(EmprestimoRepository repository) {
        this.repository = repository;
    }

    public Emprestimo save(Emprestimo emprestimo) throws SQLException{
        if(!repository.livroEmprestado(emprestimo.getLivroId())){
            throw new RuntimeException("Livro já emprestado");
        }
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(null);
        return repository.save(emprestimo);
    }

    public List<Emprestimo> getAll() throws SQLException{
        return repository.getAll();
    }

    public Emprestimo getById(Integer id) throws SQLException{
        return repository.getById(id);
    }

    public Emprestimo update(Emprestimo emprestimo, Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Emprestimo não existe");
        }
        emprestimo.setId(id);
        repository.update(emprestimo);
        return emprestimo;
    }

    public void delete(Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Emprestimo não existe");
        }
        repository.delete(id);
    }

    public Emprestimo devolucao(Emprestimo emprestimo, Integer id) throws SQLException, IllegalAccessException {
        if(!repository.exists(id)){
            throw new RuntimeException("Emprestimo não existe");
        } else if(emprestimo.getDataDevolucao() == null){
            throw new IllegalAccessException("Data de devolução obrigatoria");
        }
        emprestimo.setId(id);
        repository.devolucao(emprestimo);
        return emprestimo;
    }

    public List<Emprestimo> emprestimosUsuario(Integer id) throws SQLException{
        if(!repository.exists(id)){
            throw new RuntimeException("Emprestimo não existe");
        }
        return repository.emprestimosUsuario(id);
    }
}
