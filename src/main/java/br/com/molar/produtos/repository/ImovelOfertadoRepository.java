package br.com.molar.produtos.repository;

import br.com.molar.produtos.entities.ImovelOfertado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImovelOfertadoRepository extends JpaRepository<ImovelOfertado, Long> {

    @Query(value = "select * from imoveis_ofertados idj where idj.usuario_id = :idUsuario", nativeQuery = true)
    List<ImovelOfertado> findByIdUsuario(long idUsuario);

    @Query(value = "select * from imoveis_ofertados idj where idj.id = :idImovel", nativeQuery = true)
    ImovelOfertado findByIdImovel(long idImovel);
}
