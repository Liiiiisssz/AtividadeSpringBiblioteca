package br.com.centroweg.biblioteca.controller;

import br.com.centroweg.biblioteca.dto.usuario.UsuarioRequisicaoDTO;
import br.com.centroweg.biblioteca.dto.usuario.UsuarioRespostaDTO;
import br.com.centroweg.biblioteca.model.Usuario;
import br.com.centroweg.biblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/biblioteca/usuario")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public UsuarioRespostaDTO save(@RequestBody UsuarioRequisicaoDTO requisicaoDto){
        try{
            return service.save(requisicaoDto);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<UsuarioRespostaDTO> getAll(){
        try{
            return service.getAll();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public UsuarioRespostaDTO getById(@PathVariable Integer id){
        try{
            return service.getByid(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public UsuarioRespostaDTO update(@PathVariable Integer id, @RequestBody UsuarioRequisicaoDTO usuario){
        try{
            return service.update(usuario, id);
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
