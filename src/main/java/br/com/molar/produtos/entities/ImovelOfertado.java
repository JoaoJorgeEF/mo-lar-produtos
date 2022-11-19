package br.com.molar.produtos.entities;

import br.com.molar.produtos.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imoveis_ofertados")
public class ImovelOfertado extends BaseEntity<Long> {

    @Column(nullable = false, length = 100)
    public String titulo;

    @Column(name = "tipo_imovel", nullable = false)
    public TipoImovel tipoImovel;

    @Column(nullable = false, length = 50)
    @Size(min = 1, max = 50)
    @NotBlank(message = "É necessario informar o bairro")
    public String bairro;

    @Column(nullable = false)
    @Min(30)
    @NotNull(message = "É necessário informar a área do imóvel")
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

    @Column(nullable = true)
    public String extras;

    @Column
    @JsonIgnore
    @OneToMany(mappedBy = "imovelOfertado", fetch = FetchType.EAGER)
    public List<Foto> fotos;

    public int usuario_id;

    @Transient
    public Usuario usuario;
}
