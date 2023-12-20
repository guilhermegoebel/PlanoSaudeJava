package plano_saude.domain.dependente;

import plano_saude.domain.dependente.enums.Parentesco;

public record DadosDependente(String nome,
                              Integer titular,
                              Parentesco parentesco) {
}