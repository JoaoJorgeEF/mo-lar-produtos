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
    @NotNull(message = "É necessário informar o título do imóvel ofertado")
    public String titulo;

    @Column(name = "tipo_imovel")
    @NotNull(message = "É necessário informar o tipo do imóvel desejado")
    public TipoImovel tipoImovel;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "É necessario informar o bairro")
    public String bairro;

    @Column(nullable = false)
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

    @Column(nullable = false)
    @Min(value = 1, message = "O valor mínimo para preço é 1.")
    public BigDecimal preco;

    @Column(nullable = true)
    public String extras;

    @Column
//    @JsonIgnore
    @OneToMany(mappedBy = "imovelOfertado", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Foto> fotos;

    @OneToMany(mappedBy = "imovelOfertado")
    @JsonIgnore
    public List<Match> matches;

    public boolean hasMatches;
    
    @NotNull(message = "É necessário informar o id do cliente")
    public int usuario_id;

    @Transient
    public Usuario usuario;
}
