package plano_saude.domain.cliente;

import plano_saude.domain.cliente.enums.Sexo;
import plano_saude.domain.cliente.enums.TipoPlano;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;


public class ClienteDAO {

        private final Connection conn;

        ClienteDAO(Connection connection){
                this.conn = connection;
        }

        public void createClient(DadosCliente dados){
                String query = "INSERT INTO cliente (nome, sexo, email, telefone, dt_nascimento, local_nascimento, endereco, empresa, tipo_plano)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
                try{
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.setString(1, dados.nome());
                        ps.setString(2, dados.sexo().toString());
                        ps.setString(3, dados.email());
                        ps.setString(4, dados.telefone());
                        ps.setString(5, dados.dt_nascimento());
                        ps.setString(6, dados.local_nascimento());
                        ps.setString(7, dados.endereco());
                        ps.setString(8, dados.empresa());
                        ps.setString(9, dados.tipo_plano().toString());

                        ps.execute();
                        ps.close();
                        conn.close();
                }catch (SQLException e ){
                        throw new RuntimeException(e);
                }
        }

        public Set<Cliente> getAll(){
                Set<Cliente> clientes = new HashSet<>();
                PreparedStatement ps;
                ResultSet resultSet;
                String query = "SELECT * FROM cliente";
                try{
                        ps = conn.prepareStatement(query);
                        resultSet = ps.executeQuery();
                        while(resultSet.next()){
                                int id = resultSet.getInt("id");
                                String nome = resultSet.getString("nome");
                                String sexoStr = resultSet.getString("sexo");
                                Sexo sexo = Sexo.valueOf(sexoStr);
                                String email = resultSet.getString("email");
                                String telefone = resultSet.getString("telefone");
                                String dt_nascimento = resultSet.getString("dt_nascimento");
                                String local_nascimento = resultSet.getString("local_nascimento");
                                String endereco = resultSet.getString("endereco");
                                String empresa = resultSet.getString("empresa");
                                String tipoPlanoStr = resultSet.getString("tipo_plano");
                                TipoPlano tipo_plano = TipoPlano.valueOf(tipoPlanoStr);
                                DadosCliente dados = new DadosCliente(nome, sexo, email, telefone, dt_nascimento, local_nascimento, endereco, empresa, tipo_plano);
                                Cliente cliente = new Cliente(dados, id);
                                clientes.add(cliente);

                        }
                        ps.close();
                        conn.close();
                        resultSet.close();
                }catch(SQLException e){
                        throw new RuntimeException(e);
                }
                return clientes;
        }

        public Cliente getById(int id){
                String query = "SELECT * FROM cliente WHERE id = ?";
                PreparedStatement ps;
                ResultSet resultSet;
                Cliente cliente = null;
                try{
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, id);
                        resultSet = ps.executeQuery();
                        if (resultSet.next()){
                                int number = resultSet.getInt("id");
                                String nome = resultSet.getString("nome");
                                String sexoStr = resultSet.getString("sexo");
                                Sexo sexo = Sexo.valueOf(sexoStr);
                                String email = resultSet.getString("email");
                                String telefone = resultSet.getString("telefone");
                                String dt_nascimento = resultSet.getString("dt_nascimento");
                                String local_nascimento = resultSet.getString("local_nascimento");
                                String endereco = resultSet.getString("endereco");
                                String empresa = resultSet.getString("empresa");
                                String tipoPlanoStr = resultSet.getString("tipo_plano");
                                TipoPlano tipo_plano = TipoPlano.valueOf(tipoPlanoStr);
                                DadosCliente dados = new DadosCliente(nome, sexo, email, telefone, dt_nascimento, local_nascimento, endereco, empresa, tipo_plano);
                                cliente = new Cliente(dados, number);
                        }else{
                                System.out.println("Cliente não encontrado");
                        }
                        conn.close();
                        ps.close();
                        resultSet.close();

                }catch(SQLException e){
                        throw new RuntimeException(e);
                }
                return cliente;
        }

        public void deleteClient(int id){
                String query = "DELETE FROM cliente WHERE id = ?";
                PreparedStatement ps;
                try{
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, id);
                        ps.execute();
                        ps.close();
                        conn.close();
                }catch (SQLException e){
                        throw new RuntimeException(e);
                }
        }

        public void updateClient(int id, int option, String atributo){
            String query = getString(option);
            try{
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.setInt(2, id);
                        ps.setString(1, atributo);
                        ps.execute();
                        ps.close();
                        conn.close();
                }catch (SQLException e){
                        throw new RuntimeException(e);
                }
        }

    private static String getString(int option) {
        String columnName = switch (option) {
            case 1 -> "nome";
            case 2 -> "sexo";
            case 3 -> "email";
            case 4 -> "telefone";
            case 5 -> "dt_nascimento";
            case 6 -> "local_nascimento";
            case 7 -> "endereco";
            case 8 -> "empresa";
            case 9 -> "tipo_plano";
            default -> "";
        };
        return "UPDATE cliente SET " + columnName +"= ? WHERE id = ?";
    }
}
