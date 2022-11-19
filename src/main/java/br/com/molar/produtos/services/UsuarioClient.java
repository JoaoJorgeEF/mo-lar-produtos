package br.com.molar.produtos.services;

import br.com.molar.produtos.entities.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "usuario", url = "http://localhost:3000")
public interface UsuarioClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/usuarios")
    List<Usuario> buscarUsuarios();

    @RequestMapping(method = RequestMethod.GET, value = "api/v1/usuarios/{id}")
    Usuario buscar(@PathVariable long id);

    default Usuario buscarUsuario(long id) {
        Usuario usuario = null;
        try{
            usuario = buscar(id);
        } catch(Exception ex){
            return null;
        }
        return usuario;
    }

}
