package br.com.molar.produtos.controllers;

import br.com.molar.produtos.entities.ImovelOfertado;
import br.com.molar.produtos.services.ImovelOfertadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imoveis-ofertados")
public class ImovelOfertadoController {

    @Autowired
    private ImovelOfertadoService service;

    @PostMapping
    public ImovelOfertado cadastrar(@RequestBody ImovelOfertado imovel){
        return service.cadastrar(imovel);
    }

    @GetMapping
    public List<ImovelOfertado> listar(){
        return service.listar();
    }
}
