package br.com.molar.produtos.repository;

import br.com.molar.produtos.entities.ImovelOfertado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelOfertadoRepository extends JpaRepository<ImovelOfertado, Long> {
}
