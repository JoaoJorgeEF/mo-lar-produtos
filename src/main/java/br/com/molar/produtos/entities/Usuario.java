package br.com.molar.produtos.entities;

import br.com.molar.produtos.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends BaseEntity<Integer> {
    public String nome;
    public String cpf;
    public String email;
    public String telefone;
}
