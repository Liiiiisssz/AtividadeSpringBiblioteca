package br.com.centroweg.biblioteca.controller;

import br.com.centroweg.biblioteca.dto.livro.LivroRequisicaoDTO;
import br.com.centroweg.biblioteca.dto.livro.LivroRespostaDTO;
import br.com.centroweg.biblioteca.model.Livro;
import br.com.centroweg.biblioteca.service.LivroService;
import jakarta.validation.Valid;
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
    public LivroRespostaDTO save(@Valid @RequestBody LivroRequisicaoDTO requisicaoDTO){
        try{
            return service.save(requisicaoDTO);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<LivroRespostaDTO> getAll(){
        try{
            return service.getAll();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public LivroRespostaDTO getById(@PathVariable Integer id){
        try{
            return service.getById(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public LivroRespostaDTO update(@PathVariable Integer id, @Valid @RequestBody LivroRequisicaoDTO requisicaoDTO){
        try {
            return service.update(requisicaoDTO, id);
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
