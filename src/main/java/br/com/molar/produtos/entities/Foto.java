package br.com.molar.produtos.entities;

import br.com.molar.produtos.entities.base.BaseEntity;
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

    @ManyToOne
    @JoinColumn(name = "imovel_ofertado_id")
    public ImovelOfertado imovelOfertado;
}
