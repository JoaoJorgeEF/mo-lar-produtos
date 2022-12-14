package br.com.molar.produtos.entities;

import br.com.molar.produtos.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fotos")
public class Foto extends BaseEntity<Long> {

    public String nome;

    public String tipo;

    @Lob
    public byte[] data;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "imovel_ofertado_id")
    public ImovelOfertado imovelOfertado;
}
