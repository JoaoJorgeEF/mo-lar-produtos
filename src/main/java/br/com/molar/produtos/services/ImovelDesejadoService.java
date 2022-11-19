package br.com.molar.produtos.services;

import br.com.molar.produtos.entities.ImovelDesejado;
import br.com.molar.produtos.entities.ImovelOfertado;
import br.com.molar.produtos.entities.Usuario;
import br.com.molar.produtos.repository.ImovelDesejadoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelDesejadoService {

    @Autowired
    private ImovelDesejadoRepository repository;

    @Autowired
    private UsuarioClient usuarioClient;

    @CachePut(value = "imoveisDesejados", key = "#imovel.id")
    public ImovelDesejado cadastrar(ImovelDesejado imovel) throws Exception {
        Usuario usuario = usuarioClient.buscarUsuario(imovel.getUsuario_id());
        if (usuario == null) throw new Exception("Não foi encontrado usuário com este Id");
        imovel.setUsuario(usuario);
        imovel.setUsuario_id(usuario.getId());

        return repository.save(imovel);
    }

    @Cacheable(value = "imoveisDesejados", key = "#id")
    public ImovelDesejado consultar(long id) throws Exception{
        ImovelDesejado imovel = repository.findById(id).stream().findFirst().orElse(null);
        if (imovel != null){
            Usuario usuario = usuarioClient.buscarUsuario(imovel.getUsuario_id());
            if (usuario == null) throw new Exception("Não foi encontrado usuário com este Id");
            imovel.setUsuario(usuario);
        }
        return imovel;
    }

    @Cacheable(value = "imoveisDesejados")
    public List<ImovelDesejado> listar(){
        List<ImovelDesejado> imoveis = repository.findAll();
        for (ImovelDesejado imovel : imoveis) {
            Usuario usuario = usuarioClient.buscarUsuario(imovel.getUsuario_id());
            if (usuario != null){
                imovel.setUsuario(usuario);
            }
        }

        return imoveis;
    }

    @CacheEvict(value = "imoveisDesejados", allEntries = true)
    public boolean delete(long id) throws Exception {
        if (id <= 0) throw new Exception("Id informado não existe");
        if (!repository.existsById(id)) throw new ObjectNotFoundException(Long.class, "Objeto com Id informado não encontrado na base");

        repository.deleteById(id);

        return !repository.existsById(id);
    }

    @CachePut(value = "imoveisDesejados", key = "#id")
    public ImovelDesejado atualizarImovelDesejado(long id, ImovelDesejado imovel2) throws Exception {

        Usuario usuario = usuarioClient.buscarUsuario(imovel2.getUsuario_id());
        if (usuario == null) throw new Exception("Não foi encontrado usuário com este Id");

        Optional<ImovelDesejado> imovel = repository.findById(id);
        if(imovel.isPresent()){
            ImovelDesejado imovelDesejado = imovel.get();
            imovelDesejado.setTipoImovel(imovel2.getTipoImovel());
            imovelDesejado.setBairro(imovel2.getBairro());
            imovelDesejado.setArea(imovel2.getArea());
            imovelDesejado.setNumeroQuartos(imovel2.getNumeroQuartos());
            imovelDesejado.setNumeroBanheiros(imovel2.getNumeroBanheiros());
            imovelDesejado.setNumeroVagasGaragem(imovel2.getNumeroVagasGaragem());
            imovelDesejado.setPreco(imovel2.getPreco());
            imovelDesejado.setUsuario_id(usuario.getId());
            imovelDesejado.setUsuario(usuario);
            imovelDesejado = repository.save(imovelDesejado);

            return imovelDesejado;
        }
        else
            return null;

    }

    public List<ImovelDesejado> listarImoveisDoUsuario(int idUsuario) throws Exception {
        Usuario usuario = usuarioClient.buscarUsuario(idUsuario);
        if (usuario == null) throw new Exception("Não foi encontrado usuário com este Id");

        List<ImovelDesejado> imoveis = repository.findByIdUsuario(usuario.getId());
        if (imoveis != null) {
            for (ImovelDesejado imovel : imoveis) {
                imovel.setUsuario(usuario);
            }
        }

        return imoveis;
    }

    public ImovelDesejado consultarImoveldoUsuario(int idUsuario, long id) throws Exception{
        Usuario usuario = usuarioClient.buscarUsuario(idUsuario);
        if (usuario == null) throw new Exception("Não foi encontrado usuário com este Id");
        ImovelDesejado imovel = repository.findByIdUsuarioEIdImovel(usuario.getId(), id);
        if (imovel != null){
            imovel.setUsuario(usuario);
        }

        return imovel;
    }

}
