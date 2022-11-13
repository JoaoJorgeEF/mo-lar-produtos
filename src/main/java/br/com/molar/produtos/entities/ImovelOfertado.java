package br.com.molar.produtos.entities;

import br.com.molar.produtos.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @Column(nullable = true)
    public String extras;

    @OneToMany(mappedBy = "imovelOfertado")
    public List<Foto> fotos;

    public int usuario_id;

    @Transient
    public Usuario usuario;
}
