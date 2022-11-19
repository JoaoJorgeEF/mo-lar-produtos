package br.com.molar.produtos.entities;

import br.com.molar.produtos.entities.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imoveis_desejados")
public class ImovelDesejado extends BaseEntity<Long> {


    @Column(name = "tipo_imovel")
    @NotNull(message = "Informe um tipo de imóvel válido")
    public TipoImovel tipoImovel;

    @NotBlank(message = "O bairro deve ser informado")
    public String bairro;

    @Min(30)
    public double area;

    @Column(name = "numero_quartos", nullable = false)
    @Min(1)
    public int numeroQuartos;

    @Column(name = "numero_banheiros", nullable = false)
    @Min(1)
    public int numeroBanheiros;

    @Column(name = "numero_vagas_garagem", nullable = false)
    public int numeroVagasGaragem;

    @Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
    public BigDecimal preco;

    @NotNull(message = "É necessário informar o id do cliente")
    public int usuario_id;

    @Transient
    public Usuario usuario;
}
