package br.com.molar.produtos.entities;

import br.com.molar.produtos.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imoveis_desejados")
public class ImovelDesejado extends BaseEntity<Long> {

    @Column(name = "tipo_imovel", nullable = false)
    public TipoImovel tipoImovel;

    @Column(nullable = false, length = 50)
    public String bairro;

    @Column(nullable = false)
    public double area;

    @Column(name = "numero_quartos", nullable = false)
    public int numeroQuartos;

    @Column(name = "numero_banheiros", nullable = false)
    public int numeroBanheiros;

    @Column(name = "numero_vagas_garagem", nullable = false)
    public int numeroVagasGaragem;

    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    public BigDecimal preco;

    public int usuario_id;

    @Transient
    public Usuario usuario;
}
