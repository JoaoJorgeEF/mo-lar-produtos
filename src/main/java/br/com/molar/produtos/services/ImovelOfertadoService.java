package br.com.molar.produtos.services;

import br.com.molar.produtos.entities.Foto;
import br.com.molar.produtos.entities.ImovelOfertado;
import br.com.molar.produtos.entities.Usuario;
import br.com.molar.produtos.repository.FotoRepository;
import br.com.molar.produtos.repository.ImovelOfertadoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelOfertadoService {

    @Autowired
    private ImovelOfertadoRepository repository;

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Cacheable(value = "imoveisOfertados", key = "#id")
    public ImovelOfertado consultar(long id) throws Exception{
        if (id <= 0) throw new Exception("Id informado não existe");
        ImovelOfertado imovel = repository.findById(id).orElseThrow( () -> new Exception("Imóvel não encontrado para o ID informado") );
        if (imovel != null){
            Usuario usuario = usuarioClient.buscarUsuario(imovel.getUsuario_id());
            if (usuario == null) throw new Exception("Não foi encontrado usuário com este Id");
            imovel.setUsuario(usuario);
        }
        return imovel;
    }

//    @Cacheable(value = "imoveisOfertados", key= "#root.method.name")
    public List<ImovelOfertado> listar(){
        List<ImovelOfertado> imoveis = repository.findAll();
        for (ImovelOfertado imovel : imoveis) {
            Usuario usuario = usuarioClient.buscarUsuario(imovel.getUsuario_id());
            if (usuario != null){
                imovel.setUsuario(usuario);
            }
        }

        return imoveis;
    }

    @CachePut(value = "imoveisOfertados", key = "#imovel.id")
    public ImovelOfertado cadastrar(ImovelOfertado imovel) throws Exception{
        Usuario usuario = usuarioClient.buscarUsuario(imovel.getUsuario_id());
        if (usuario == null) throw new Exception("Não foi encontrado usuário com este Id");
        imovel.setUsuario(usuario);
        imovel.setUsuario_id(usuario.getId());

        if (imovel.getFotos() != null && imovel.getFotos().size() > 0){
            for (Foto foto : imovel.getFotos()) {
                foto.setImovelOfertado(imovel);
                fotoRepository.save(foto);
            }
        }

        return repository.save(imovel);
    }

    @CachePut(value = "imoveisOfertados", key = "#id")
    public ImovelOfertado atualizar(long id, ImovelOfertado imovel) throws Exception {
        if (imovel == null || imovel.getId() <= 0 || id <= 0) throw new Exception("Id de imóvel informado não existe");
        if (imovel.getId() != id) throw new Exception("Id informado no Path difere do Id informado no body");

        Usuario usuario = usuarioClient.buscarUsuario(imovel.getUsuario_id());
        if (usuario == null) throw new Exception("Não foi encontrado usuário com este Id");
        imovel.setUsuario(usuario);
        imovel.setUsuario_id(usuario.getId());

        ImovelOfertado imovelDoRepo = repository.findById(imovel.getId()).stream().findFirst().orElse(null);
        if (imovelDoRepo == null) throw new ObjectNotFoundException(ImovelOfertado.class, "Objeto para atualizar não encontrado na base");
        imovel = repository.save(imovel);
        return imovel;
    }

    @CacheEvict(value = "imoveisOfertados", allEntries = true)
    public boolean excluir(long id) throws Exception {
        if (id <= 0) throw new Exception("Id informado não existe");
        if (!repository.existsById(id)) throw new ObjectNotFoundException(Long.class, "Objeto com Id informado não encontrado na base");

        repository.deleteById(id);

        return !repository.existsById(id);
    }

    public List<ImovelOfertado> listarImoveisDoUsuario(long idUsuario) throws Exception {
        Usuario usuario = usuarioClient.buscarUsuario(idUsuario);
        if (usuario == null) throw new Exception("Não foi encontrado usuário com este Id");

        List<ImovelOfertado> imoveis = repository.findByIdUsuario(usuario.getId());
        if (imoveis != null) {
            for (ImovelOfertado imovel : imoveis) {
                imovel.setUsuario(usuario);
            }
        }
        return imoveis;
    }

}
