package plano_saude.domain.cliente;

import plano_saude.domain.cliente.enums.Sexo;
import plano_saude.domain.cliente.enums.TipoPlano;



public record DadosCliente(String nome,
                           Sexo sexo,
                           String email,
                           String telefone,
                           String dt_nascimento,
                           String local_nascimento,
                           String endereco,
                           String empresa,
                           TipoPlano tipo_plano) {
}
