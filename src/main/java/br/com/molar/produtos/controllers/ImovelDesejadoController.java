package br.com.molar.produtos.controllers;

import br.com.molar.produtos.entities.ImovelDesejado;
import br.com.molar.produtos.entities.ImovelOfertado;
import br.com.molar.produtos.entities.Usuario;
import br.com.molar.produtos.services.ImovelDesejadoService;
import br.com.molar.produtos.services.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/imoveis-desejados")
public class ImovelDesejadoController {

    @Autowired
    private ImovelDesejadoService service;

    @CrossOrigin
    @PostMapping
    public ImovelDesejado cadastrar(@RequestBody @Valid ImovelDesejado imovel){
        try {
            return service.cadastrar(imovel);
        } catch(Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping
    public List<ImovelDesejado> listar(){
        return service.listar();
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ImovelDesejado consultar(@PathVariable("id") long id) {
        try {
            return service.consultar(id);
        } catch(Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @CrossOrigin
    @PutMapping(value = "/{id}")
    public ImovelDesejado atualizar(@PathVariable("id") Long id, @RequestBody @Valid ImovelDesejado imovelDesejado) {
        try {
            return service.atualizarImovelDesejado(id, imovelDesejado);
        } catch(Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        try {
            return service.delete(id);
        } catch(Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    //paths que envolve o id do usuário
    @CrossOrigin
    @GetMapping(value = "/usuario/{id}")
    public List<ImovelDesejado> listarImoveisDesejadosDoUsuario(@PathVariable("id") int id) {
        try {
            return service.listarImoveisDoUsuario(id);
        } catch(Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping(value = "/usuario/{idUsuario}/imovel/{id}")
    public ImovelDesejado consultarImoveldoUsuario(@PathVariable("idUsuario") int idUsuario ,@PathVariable("id") int id){
        try {
            return service.consultarImoveldoUsuario(idUsuario, id);
        } catch(Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @CrossOrigin
    @GetMapping(value = "/{id}/matches")
    public List<ImovelOfertado> consultarMatchesdoImovel(@PathVariable("id") long id){
        try {
            return service.listarMatchesImoveisOfertados(id);
        } catch(Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }


    @GetMapping("/{id}/enfileirar")
    public void enfileirar(@PathVariable long id){
        service.enfileirar(id);
    }
}
