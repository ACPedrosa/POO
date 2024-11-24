package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Gerente;
import model.Recepcionista;

public class GerenteDAO {

    // Método para criar um novo gerente
    public void createGerente(Gerente gerente) throws ExceptionDAO {
        String sql = "INSERT INTO Gerente (idFuncionario) VALUES (?)";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gerente.getIdFuncionario());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao criar Gerente: " + e.getMessage());
        }
    }

    // Método para atualizar um gerente existente
    public void updateGerente(Gerente gerente) throws ExceptionDAO {
        String sql = "UPDATE Gerente SET login = ?, senha = ?, cargo = ? WHERE idGerente = ?";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gerente.getLogin());
            stmt.setString(2, gerente.getSenha());
            stmt.setString(3, gerente.getCargo());
            stmt.setInt(4, gerente.getIdGerente());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao atualizar Gerente: " + e.getMessage());
        }
    }

    // Método para deletar um gerente
    public void deleteGerente(int idGerente) throws ExceptionDAO {
        String sql = "DELETE FROM Gerente WHERE idGerente = ?";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGerente);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao excluir Gerente: " + e.getMessage());
        }
    }

    public Gerente getGerenteByCpf(String cpf) throws ExceptionDAO {
        Gerente gerente = null;
        String sql = "SELECT * FROM Gerente g JOIN Pessoa p ON g.idPessoa = p.idPessoa WHERE p.cpf = ?";
        
        try (Connection conn = new ConexaoBD().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                gerente = new Gerente();
                gerente.setIdGerente(rs.getInt("idGerente"));
                gerente.setLogin(rs.getString("login"));
                gerente.setSenha(rs.getString("senha"));
                gerente.setCargo(rs.getString("cargo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao buscar Gerente: " + e.getMessage());
        }
        return gerente;
    }

    public List<Gerente> getAllGerentes() throws ExceptionDAO{
        String sql = "SELECT * FROM Gerente g JOIN Pessoa p ON g.idPessoa = p.idPessoa";
  
        List<Gerente> gerentes = new ArrayList<>();
  
        try (Connection conn = new ConexaoBD().getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        
                ResultSet rs = stmt.executeQuery();
        
                while (rs.next()) {
                    Gerente gerente = new Gerente();
                    gerente.setIdGerente(rs.getInt("idGerente"));
                    gerente.setLogin(rs.getString("login"));
                    gerente.setSenha(rs.getString("senha"));
                    gerente.setCargo(rs.getString("cargo"));
  
                    gerentes.add(gerente);
                }
        } catch (SQLException e) {
           e.printStackTrace();
           throw new ExceptionDAO("Erro ao buscar todos os gerentes cadastrados: " + e.getMessage());
        }
  
        return gerentes;
    }
}