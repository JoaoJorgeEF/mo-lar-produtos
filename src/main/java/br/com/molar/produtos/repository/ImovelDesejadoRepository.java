package br.com.molar.produtos.repository;

import br.com.molar.produtos.entities.ImovelDesejado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImovelDesejadoRepository extends JpaRepository<ImovelDesejado, Long> {

    @Query(value = "select * from imoveis_desejados idj where idj.usuario_id = :idUsuario", nativeQuery = true)
    List<ImovelDesejado> findByIdUsuario(int idUsuario);

    @Query(value = "select * from imoveis_desejados idj where idj.usuario_id = :idUsuario and idj.id = :id", nativeQuery = true)
    ImovelDesejado findByIdUsuarioEIdImovel(int idUsuario, long id);
}