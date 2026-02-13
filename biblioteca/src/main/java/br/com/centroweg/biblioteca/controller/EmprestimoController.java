package br.com.centroweg.biblioteca.controller;

import br.com.centroweg.biblioteca.model.Emprestimo;
import br.com.centroweg.biblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/biblioteca/emprestimo")
public class EmprestimoController {
    private final EmprestimoService service;
    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public Emprestimo save(@RequestBody Emprestimo emprestimo){
        try{
            return service.save(emprestimo);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Emprestimo> getAll(){
        try{
            return service.getAll();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Emprestimo getById(@PathVariable Integer id){
        try{
            return service.getById(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Emprestimo update(@PathVariable Integer id, @RequestBody Emprestimo emprestimo){
        try {
            return service.update(emprestimo, id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        try{
            service.delete(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}/devolucao")
    public Emprestimo devolucao(@PathVariable Integer id, @RequestBody Emprestimo emprestimo){
        try {
            return service.devolucao(emprestimo, id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        } catch (IllegalAccessException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuario/{id}")
    public List<Emprestimo> emprestimosUsuario(@PathVariable Integer id){
        try{
            return service.emprestimosUsuario(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
