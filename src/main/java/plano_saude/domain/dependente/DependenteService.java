package plano_saude.domain.dependente;

import plano_saude.ConnectionFactory;
import plano_saude.domain.cliente.ClienteDAO;

import java.sql.Connection;
import java.util.Set;

public class DependenteService {

        private final ConnectionFactory connection;

        public DependenteService(){
                this.connection = new ConnectionFactory();
        }

        public Set<Dependente> getAllDependents(int titular){
                Connection conn = connection.getConnection();
                return new DependenteDAO(conn).getAllDependents(titular);
        }

        public void createDependent(DadosDependente dados){
                Connection conn = connection.getConnection();
                new DependenteDAO(conn).createDependent(dados);
        }

        public boolean verifyClientId(int id){
                Connection conn = connection.getConnection();
                return new DependenteDAO(conn).verifyClientId(id);
        }

        public void deleteDependent(int id){
                Connection conn = connection.getConnection();
                new DependenteDAO(conn).deleteDependent(id);
        }

        public void updateDependent(int id, int option, String atributo){
                Connection conn = connection.getConnection();
                new DependenteDAO(conn).updateDependent(id, option, atributo);
        }

}
