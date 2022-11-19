package br.com.molar.produtos.services;

import br.com.molar.produtos.entities.ImovelOfertado;
import br.com.molar.produtos.repository.ImovelOfertadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelOfertadoService {

    @Autowired
    private ImovelOfertadoRepository repository;

    @CachePut(value = "imoveisOfertados", key = "#imovel.id")
    public ImovelOfertado cadastrar(ImovelOfertado imovel){
       return repository.save(imovel);
    }

    public ImovelOfertado consultar(long id){
        return repository.findById(id).stream().findFirst().orElse(null);
    }

    @Cacheable(value = "imoveisOfertados")
    public List<ImovelOfertado> listar(){
        return repository.findAll();
    }
}
