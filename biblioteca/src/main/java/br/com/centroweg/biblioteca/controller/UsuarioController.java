package br.com.centroweg.biblioteca.controller;

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
    public Usuario save(@RequestBody Usuario usuario){
        try{
            return service.save(usuario);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario> getAll(){
        try{
            return service.getAll();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Integer id){
        try{
            return service.getByid(id);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Integer id, @RequestBody Usuario usuario){
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
