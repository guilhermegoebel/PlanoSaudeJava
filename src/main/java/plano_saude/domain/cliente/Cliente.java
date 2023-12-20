package plano_saude.domain.cliente;

import plano_saude.domain.cliente.enums.Sexo;
import plano_saude.domain.cliente.enums.TipoPlano;


public class Cliente {
    private int id;
    private String nome;
    private Sexo sexo;
    private String email;
    private String telefone;
    private String dt_nascimento;
    private String local_nascimento;
    private String endereco;
    private String empresa;
    private TipoPlano tipo_plano;

    public Cliente(DadosCliente dados, int id) {
        this.id = id;
        this.nome = dados.nome();
        this.sexo = dados.sexo();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dt_nascimento = dados.dt_nascimento();
        this.local_nascimento = dados.local_nascimento();
        this.endereco = dados.endereco();
        this.empresa = dados.empresa();
        this.tipo_plano = dados.tipo_plano();
    }

    @Override
    public String toString() {
        return "\nCliente{" +
                " id='" + this.id + '\'' +
                ", nome='" + this.nome + '\'' +
                ", sexo=" + this.sexo +
                ", email='" + this.email + '\'' +
                ", telefone='" + this.telefone + '\'' +
                ", dt_nascimento=" + this.dt_nascimento +
                ", local_nascimento='" + this.local_nascimento + '\'' +
                ", endereco='" + this.endereco + '\'' +
                ", empresa='" + this.empresa + '\'' +
                ", tipo_plano=" + this.tipo_plano +
                '}' + "\n";
    }


}
