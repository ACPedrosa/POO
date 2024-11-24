package dao;

import model.Funcionario;
import model.Recepcionista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RecepcionistaDAO {

    public void createRecepcionista(Recepcionista recepcionista) throws ExceptionDAO {
        String sql = "INSERT INTO Recepcionista (idFuncionario) VALUES (?);";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setInt(1, recepcionista.getIdFuncionario());
			stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao criar Recepcionista: " + e.getMessage());
        }
    }


    public void updateRecepcionista(Recepcionista recepcionista) throws ExceptionDAO {
        String sql = "UPDATE Recepcionista SET login = ?, senha = ?, cargo = ? WHERE idRecepcionista = ?;";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, recepcionista.getLogin());
            stmt.setString(2, recepcionista.getSenha());
            stmt.setString(3, recepcionista.getCargo());
            stmt.setInt(4, recepcionista.getIdRecepcionista());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao atualizar Recepcionista: " + e.getMessage());
        }
    }

    public void deleteRecepcionista(int idRecepcionista) throws ExceptionDAO {
        String sql = "DELETE FROM Recepcionista WHERE idRecepcionista = ?;";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRecepcionista);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao excluir Recepcionista: " + e.getMessage());
        }
    }

    public Recepcionista getRecepcionistaByCpf(String cpf) throws ExceptionDAO {
        String sql = "SELECT * FROM Pessoa p \r\n"
        		+ "JOIN Funcionario f ON p.idPessoa = f.idPessoa\r\n"
        		+ "JOIN Recepcionista r on f.idFuncionario = r.idFuncionario WHERE p.cpf = ?";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.setIdRecepcionista(rs.getInt("idRecepcionista"));
                recepcionista.setIdPessoa(rs.getInt("idPessoa"));
                recepcionista.setNome(rs.getString("nome"));
                recepcionista.setTelefone(rs.getString("telefone"));
                recepcionista.setCpf(rs.getString("cpf"));
				
                return recepcionista;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao buscar Recepcionista: " + e.getMessage());
        }
        return null;
    }
    
    public List<Recepcionista> getAllRecepcionistas() throws ExceptionDAO{
        String sql = "SELECT * FROM Pessoa p "
                 + "JOIN Funcionario f ON p.idPessoa = f.idPessoa "
                 + "JOIN Recepcionista r ON f.idFuncionario = r.idFuncionario";
  
        List<Recepcionista> recepcionistas = new ArrayList<>();
  
        try(
           Connection conn = new ConexaoBD().getConnection();
           PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
           ResultSet rs = stmt.executeQuery();
  
           while (rs.next()) {
              Recepcionista recepcionista = new Recepcionista();
              recepcionista.setIdRecepcionista(rs.getInt("idRecepcionista"));
              recepcionista.setIdPessoa(rs.getInt("idPessoa"));
              recepcionista.setNome(rs.getString("nome"));
              recepcionista.setTelefone(rs.getString("telefone"));
              recepcionista.setCpf(rs.getString("cpf"));
  
              recepcionistas.add(recepcionista);
           }
        } catch (SQLException e) {
           e.printStackTrace();
           throw new ExceptionDAO("Erro ao buscar todos os recepcionistas cadastrados: " + e.getMessage());
        }
  
        return recepcionistas;
    }
}