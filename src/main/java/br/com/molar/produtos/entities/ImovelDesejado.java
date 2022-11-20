package br.com.molar.produtos.entities;

import br.com.molar.produtos.entities.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imoveis_desejados")
public class ImovelDesejado extends BaseEntity<Long> {


    @Column(name = "tipo_imovel")
    @NotNull(message = "É necessário informar o tipo do imóvel desejado")
    public TipoImovel tipoImovel;

    @NotBlank(message = "É necessario informar o bairro")
    public String bairro;

    @Min(value = 30, message = "O valor da área deve ser igual ou maior a 30m².")
    public double area;

    @Column(name = "numero_quartos", nullable = false)
    @Min(value = 1, message = "A quantidade mínima de quartos deve ser 1.")
    public int numeroQuartos;

    @Column(name = "numero_banheiros", nullable = false)
    @Min(value = 1, message = "O número mínimo de banheiros deve ser 1.")
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
