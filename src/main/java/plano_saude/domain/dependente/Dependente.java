package plano_saude.domain.dependente;


import plano_saude.domain.dependente.enums.Parentesco;

public class Dependente {
    private int id;
    private String nome;
    private int titular;
    private Parentesco parentesco;

    public Dependente(DadosDependente dados, int id) {
        this.id = id;
        this.nome = dados.nome();
        this.titular = dados.titular();
        this.parentesco = dados.parentesco();
    }

    @Override
    public String toString() {
        return "\nDependente{" +
                " id='" + this.id + '\'' +
                ", nome='" + this.nome + '\'' +
                ", titular=" + this.titular +
                ", parentesco='" + this.parentesco + '\'' +
                '}' + "\n";
    }


}
