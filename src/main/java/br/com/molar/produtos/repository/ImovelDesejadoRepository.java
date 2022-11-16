package br.com.molar.produtos.repository;

import br.com.molar.produtos.entities.ImovelDesejado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelDesejadoRepository extends JpaRepository<ImovelDesejado, Long> {
}
