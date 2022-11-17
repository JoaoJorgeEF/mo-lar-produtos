package br.com.molar.produtos.controllers;

import br.com.molar.produtos.entities.ImovelDesejado;
import br.com.molar.produtos.services.ImovelDesejadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imoveis-desejados")
public class ImovelDesejadoController {

    @Autowired
    private ImovelDesejadoService service;

    @PostMapping
    public ImovelDesejado cadastrar(@RequestBody ImovelDesejado imovel){
        return service.cadastrar(imovel);
    }

    @GetMapping
    public List<ImovelDesejado> listar(){
        return service.listar();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ImovelDesejado consultar(@PathVariable("id") Long id) {return service.consultar(id);}

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {service.delete(id);}

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ImovelDesejado atualizar(@PathVariable("id") Long id, @RequestBody ImovelDesejado imovelDesejado) {return service.atualizarImovelDesejado(id, imovelDesejado).getBody();}



}
