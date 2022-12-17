package br.com.molar.produtos.entities;

import br.com.molar.produtos.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matches")
public class Match extends BaseEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "imovel_ofertado_id")
    public ImovelOfertado imovelOfertado;

    @ManyToOne
    @JoinColumn(name = "imovel_desejado_id")
    public ImovelDesejado imovelDesejado;
}
