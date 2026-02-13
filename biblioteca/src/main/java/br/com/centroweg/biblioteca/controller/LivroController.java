package br.com.centroweg.biblioteca.controller;

import br.com.centroweg.biblioteca.model.Livro;
import br.com.centroweg.biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/biblioteca/livro")
public class LivroController {
    private final LivroService service;
    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public Livro save(@RequestBody Livro livro){
        try{
            return service.save(livro);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Livro> getAll(){
        try{
            return service.getAll();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Livro getById(@PathVariable Integer id){
        try{
            return service.getById(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Livro update(@PathVariable Integer id, @RequestBody Livro livro){
        try {
            return service.update(livro, id);
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
}
