package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Profissional;

public class ProfissionalDAO {

    public void createProfissional(Profissional profissional) throws ExceptionDAO {
        String sql = "INSERT INTO Profissional (especialidade, registroConselho, dataInscricao, idFuncionario) VALUES (?, ?, ?, ?)";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, profissional.getEspecialidade());
            stmt.setString(2, profissional.getRegistroConselho());
            stmt.setDate(3, profissional.getDataInscricao());
            stmt.setInt(4, profissional.getIdFuncionario()); // ID do funcionario associado
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao cadastrar profissional: " + e.getMessage());
        }
    }

    public void updateProfisssional(Profissional profissional) throws ExceptionDAO {
        String sql = "UPDATE Profissional SET especialidade = ?, registroConselho = ?, dataInscricao = ? WHERE idProfissional = ?";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, profissional.getEspecialidade());
            stmt.setString(2, profissional.getRegistroConselho());
            stmt.setDate(3, profissional.getDataInscricao());
            stmt.setInt(4, profissional.getIdProfissional());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao atualizar profissional: " + e.getMessage());
        }
    }

    public void deleteProfissional(int idProfissional) throws ExceptionDAO {
        String sql = "DELETE FROM Profissional WHERE idProfissional = ?";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProfissional);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao excluir profissional: " + e.getMessage());
        }
    }
    
    public Profissional getProfissionalById(int idProfissional) throws ExceptionDAO {
        String sql = "SELECT * FROM Profissional WHERE idProfissional = ?";
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProfissional);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Profissional profissional = new Profissional();
                profissional.setIdProfissional(rs.getInt("idProfissional"));
                profissional.setEspecialidade(rs.getString("especialidade"));
                profissional.setRegistroConselho(rs.getString("registroConselho"));
                profissional.setDataInscricao(rs.getDate("dataInscricao"));
                return profissional;
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao buscar profissional: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar
    }

    public Profissional getProfissionalByRegistro(String registroConselho) throws ExceptionDAO {
        String sql = "SELECT * FROM Profissional WHERE registroConselho = ?";
        
        try (Connection conn = new ConexaoBD().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, registroConselho);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Profissional profissional = new Profissional();
                profissional.setIdProfissional(rs.getInt("idProfissional"));
                profissional.setEspecialidade(rs.getString("especialidade"));
                profissional.setRegistroConselho(rs.getString("registroConselho"));
                profissional.setDataInscricao(rs.getDate("dataInscricao"));
                return profissional;
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao buscar profissional: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar
    }

    public List<Profissional> getAllProfissionais() throws ExceptionDAO {
        String sql = "SELECT * FROM Profissional p";

        List<Profissional> profissionais = new ArrayList<>();

        try (Connection conn = new ConexaoBD().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Profissional profissional = new Profissional();
                profissional.setIdProfissional(rs.getInt("idProfissional"));
                profissional.setEspecialidade(rs.getString("especialidade"));
                profissional.setRegistroConselho(rs.getString("registroConselho"));
                profissional.setDataInscricao(rs.getDate("dataInscricao"));
                
                profissionais.add(profissional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao buscar todos os profissionais cadastrados: " + e.getMessage());
        }

        return profissionais;
    }
}
