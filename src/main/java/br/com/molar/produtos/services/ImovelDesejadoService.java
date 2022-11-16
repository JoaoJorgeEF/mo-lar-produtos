package br.com.molar.produtos.services;

import br.com.molar.produtos.entities.ImovelDesejado;
import br.com.molar.produtos.repository.ImovelDesejadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelDesejadoService {

    @Autowired
    private ImovelDesejadoRepository repository;

    public ImovelDesejado cadastrar(ImovelDesejado imovel){
       return repository.save(imovel);
    }

    public ImovelDesejado consultar(long id){
        return repository.findById(id).stream().findFirst().orElse(null);
    }

    public List<ImovelDesejado> listar(){
        return repository.findAll();
    }

    public void delete(Long id) {repository.deleteById(id);}

}
