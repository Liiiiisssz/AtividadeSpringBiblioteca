package br.com.centroweg.biblioteca.controller;

import br.com.centroweg.biblioteca.dto.emprestimo.EmprestimoRequisicaoDTO;
import br.com.centroweg.biblioteca.dto.emprestimo.EmprestimoRespostaDTO;
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
    public EmprestimoRespostaDTO save(@RequestBody EmprestimoRequisicaoDTO requisicaoDTO){
        try{
            return service.save(requisicaoDTO);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<EmprestimoRespostaDTO> getAll(){
        try{
            return service.getAll();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public EmprestimoRespostaDTO getById(@PathVariable Integer id){
        try{
            return service.getById(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public EmprestimoRespostaDTO update(@PathVariable Integer id, @RequestBody EmprestimoRequisicaoDTO requisicaoDTO){
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

    @PutMapping("/{id}/devolucao")
    public EmprestimoRespostaDTO devolucao(@PathVariable Integer id, @RequestBody EmprestimoRequisicaoDTO requisicaoDTO){
        try {
            return service.devolucao(requisicaoDTO, id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        } catch (IllegalAccessException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/usuario/{id}")
    public List<EmprestimoRespostaDTO> emprestimosUsuario(@PathVariable Integer id){
        try{
            return service.emprestimosUsuario(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
