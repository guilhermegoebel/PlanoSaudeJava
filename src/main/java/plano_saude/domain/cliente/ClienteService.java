package plano_saude.domain.cliente;

import plano_saude.ConnectionFactory;
import plano_saude.domain.BusinessRuleException;

import java.sql.Connection;
import java.util.Set;

public class ClienteService {

        private final ConnectionFactory connection;

        public ClienteService(){
                this.connection = new ConnectionFactory();
        }

        public void createClient(DadosCliente dados){
                Connection conn = connection.getConnection();
                new ClienteDAO(conn).createClient(dados);
        }

        public Set<Cliente> getAll(){
                Connection conn = connection.getConnection();
                return new ClienteDAO(conn).getAll();
        }

        public Cliente getById(int id){
                Connection conn = connection.getConnection();
                Cliente cliente = new ClienteDAO(conn).getById(id);
                if(cliente == null){
                        throw new BusinessRuleException("Nenhum cliente com o id informado foi encontrado!");
                }else{
                        return  cliente;
                }
        }

        public void deleteClient(int id){
                Connection conn = connection.getConnection();
                new ClienteDAO(conn).deleteClient(id);
        }

        public void updateClient(int id, int option, String atributo){
                Connection conn = connection.getConnection();
                new ClienteDAO(conn).updateClient(id, option, atributo);
        }
}
