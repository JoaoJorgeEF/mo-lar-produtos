package br.com.molar.produtos.controllers;

import br.com.molar.produtos.entities.ImovelDesejado;
import br.com.molar.produtos.entities.Usuario;
import br.com.molar.produtos.services.ImovelDesejadoService;
import br.com.molar.produtos.services.UsuarioClient;
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

    @GetMapping(value = "/{id}")
    public ImovelDesejado consultar(@PathVariable("id") Long id) {return service.consultar(id);}

    @PutMapping(value = "/{id}")
    public ImovelDesejado atualizar(@PathVariable("id") Long id, @RequestBody ImovelDesejado imovelDesejado) {return service.atualizarImovelDesejado(id, imovelDesejado).getBody();}

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) {service.delete(id);}

    //paths que envolve o id do usuário
    @GetMapping(value = "/usuario/{id}")
    public List<ImovelDesejado> listarImoveisDesejadosDoUsuario(@PathVariable("id") int id) {return service.listarImoveisDoUsuario(id);}

    @GetMapping(value = "/usuario/{idUsuario}/imovel/{id}")
    public ImovelDesejado consultarImoveldoUsuario(@PathVariable("idUsuario") int idUsuario ,@PathVariable("id") int id){
        return service.consultarImoveldoUsuario(idUsuario, id);
    }

//    @GetMapping(value= "/usuarios")
//    public List<Usuario> teste(){
//        return usuarioClient.buscarUsuarios();
//    }

}
