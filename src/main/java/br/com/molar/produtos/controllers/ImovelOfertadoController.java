package br.com.molar.produtos.controllers;

import br.com.molar.produtos.entities.ImovelOfertado;
import br.com.molar.produtos.services.ImovelOfertadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/imoveis-ofertados")
public class ImovelOfertadoController {

    @Autowired
    private ImovelOfertadoService service;

    @GetMapping
    public List<ImovelOfertado> listar(){
        return service.listar();
    }

    @GetMapping(value = "/{id}")
    public ImovelOfertado buscar(@PathVariable long id){
        return service.consultar(id);
    }

    @GetMapping(value = "/usuario/{id}")
    public List<ImovelOfertado> listarImoveisOfertadosDoUsuario(@PathVariable("id") long id) {return service.listarImoveisDoUsuario(id);}

    @PostMapping
    public ImovelOfertado cadastrar(@RequestBody ImovelOfertado imovel){
        return service.cadastrar(imovel);
    }

    @PutMapping(value = "/{id}")
    public ImovelOfertado atualizar(@PathVariable long id, @RequestBody ImovelOfertado imovel){
        try{
            return service.atualizar(id, imovel);
        } catch(Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @DeleteMapping(value = "/{id}")
    public boolean excluir(@PathVariable long id){
        try{
            return service.excluir(id);
        }catch(Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
