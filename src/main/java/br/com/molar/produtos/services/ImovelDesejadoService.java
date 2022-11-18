package br.com.molar.produtos.services;

import br.com.molar.produtos.entities.ImovelDesejado;
import br.com.molar.produtos.repository.ImovelDesejadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void delete(long id) {repository.deleteById(id);}

    public ResponseEntity<ImovelDesejado> atualizarImovelDesejado(long id, ImovelDesejado imovel2) {

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
            imovelDesejado.setUsuario_id(imovel2.getUsuario_id());
            repository.save(imovelDesejado);
            return new ResponseEntity<ImovelDesejado>(imovelDesejado, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
    public List<ImovelDesejado> listarImoveisDoUsuario(int idUsuario) {return repository.findByIdUsuario(idUsuario);}

    public ImovelDesejado consultarImoveldoUsuario(int idUsuario, long id){
        return repository.findByIdUsuarioEIdImovel(idUsuario, id);
    }

}
