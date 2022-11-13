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

    @Column(name = "tipo_imovel")
    public TipoImovel tipoImovel;

    @Column(nullable = false, length = 50)
    public String bairro;

    public double area;

    @Column(name = "numero_quartos")
    public int numeroQuartos;

    @Column(name = "numero_banheiros")
    public int numeroBanheiros;

    @Column(name = "numero_vagas_garagem")
    public int numeroVagasGaragem;

    public BigDecimal preco;

    public int usuario_id;

    @Transient
    public Usuario usuario;
}
