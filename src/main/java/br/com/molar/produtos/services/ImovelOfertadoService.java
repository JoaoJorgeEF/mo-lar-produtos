package br.com.molar.produtos.services;

import br.com.molar.produtos.entities.ImovelOfertado;
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

    @Cacheable(value = "imoveisOfertados", key = "#id")
    public ImovelOfertado consultar(long id){
        return repository.findById(id).stream().findFirst().orElse(null);
    }

    @Cacheable(value = "imoveisOfertados")
    public List<ImovelOfertado> listar(){
        return repository.findAll();
    }

    @CachePut(value = "imoveisOfertados", key = "#imovel.id")
    public ImovelOfertado cadastrar(ImovelOfertado imovel){
       return repository.save(imovel);
    }

    @CachePut(value = "imoveisOfertados", key = "#id")
    public ImovelOfertado atualizar(long id, ImovelOfertado imovel) throws Exception {
        if (imovel == null || imovel.getId() <= 0 || id <= 0) throw new Exception("Id de imóvel informado não existe");
        if (imovel.getId() != id) throw new Exception("Id informado no Path difere do Id informado no body");

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

    public List<ImovelOfertado> listarImoveisDoUsuario(long idUsuario) {return repository.findByIdUsuario(idUsuario);}

}
