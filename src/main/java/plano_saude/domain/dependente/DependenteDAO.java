package plano_saude.domain.dependente;

import plano_saude.domain.dependente.enums.Parentesco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DependenteDAO {

        private final Connection conn;

        DependenteDAO(Connection connection){
                this.conn = connection;
        }

        public Set<Dependente> getAllDependents(int titular){
                Set<Dependente> dependentes = new HashSet<>();
                PreparedStatement ps;
                ResultSet resultSet;
                String query = "SELECT * FROM dependentes WHERE titular = ?";
                try{
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, titular);
                        resultSet = ps.executeQuery();
                        while(resultSet.next()){
                                int id = resultSet.getInt("id");
                                String nome = resultSet.getString("nome");
                                String parentescoStr = resultSet.getString("parentesco");
                                Parentesco parentesco = Parentesco.valueOf(parentescoStr);
                                DadosDependente dados = new DadosDependente(nome, titular, parentesco);
                                Dependente dependente = new Dependente(dados, id);
                                dependentes.add(dependente);
                        }
                        ps.close();
                        conn.close();
                        resultSet.close();
                }catch (SQLException e){
                        throw new RuntimeException(e);
                }
                return dependentes;
        }

        public void createDependent(DadosDependente dados){
                String query = "INSERT INTO dependentes (nome, titular, parentesco)" +
                        "VALUES (?, ?, ?);";
                try{
                        PreparedStatement ps = conn.prepareStatement(query);
                        ps.setString(1, dados.nome());
                        ps.setInt(2, dados.titular());
                        ps.setString(3, dados.parentesco().toString());
                        ps.execute();
                        ps.close();
                        conn.close();
                }catch (SQLException e ){
                        throw new RuntimeException(e);
                }
        }

        public boolean verifyClientId(int id) {
                boolean verify = false;
                String sql = "SELECT COUNT(*) AS count FROM cliente WHERE id = ?";

                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                        preparedStatement.setInt(1, id);

                        try (ResultSet rs = preparedStatement.executeQuery()) {
                                if (rs.next()) {
                                        int count = rs.getInt("count");
                                        verify = count > 0;
                                }
                        }
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }

                return verify;
        }

        public void deleteDependent(int id){
                String query = "DELETE FROM dependentes WHERE id = ?";
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

        public void updateDependent(int id, int option, String atributo){
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
                        case 2 -> "parentesco";
                        default -> "";
                };
                return "UPDATE dependentes SET " + columnName +"= ? WHERE id = ?";
        }
}
