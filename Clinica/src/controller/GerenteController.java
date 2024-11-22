package controller;

import java.sql.Date;

import dao.FuncionarioDAO;
import dao.GerenteDAO;
import dao.PessoaDAO;
import model.Funcionario;
import model.Gerente;
import model.Pessoa;
import model.Gerente;

public class GerenteController {

    // Método para criar um novo gerente
    public void createGerente(Pessoa pessoa, String login, String senha, String cargo) throws Exception {
        if (pessoa.getIdPessoa() > 0 && login != null && senha != null && cargo != null) {
            Gerente gerente = new Gerente(login, senha, cargo);
            new GerenteDAO().createGerente(gerente);
        } else {
            throw new Exception("Preencha os campos corretamente!");
        }
    }
    
    public void createFullGerente(String nome, String telefone, String rg, String cpf, Date dataNascimento, String sexo, String profissao, String endereco, String login, String senha, String cargo) throws Exception {
        if (nome != null) {
        	Pessoa pessoa = new Pessoa(nome, telefone, rg, cpf, dataNascimento, sexo, profissao, endereco);
        	pessoa.createPessoa(pessoa);
        	pessoa = new PessoaDAO().getPessoaByCpf(pessoa.getCpf());
        	
        	Funcionario funcionario = new Funcionario(login, senha, cargo);
        	funcionario.createFuncionario(funcionario, pessoa.getIdPessoa());
        	funcionario = new FuncionarioDAO().getFuncionarioByCpf(pessoa.getCpf());
        	
        	
        	Gerente Gerente = new Gerente(funcionario.getIdFuncionario());
            
            Gerente.createGerente(Gerente);
        } else {
            throw new Exception("Preencha os campos corretamente!");
        }
    }

    // Método para atualizar um gerente existente
    public void updateGerente(int idGerente, String login, String senha, String cargo) throws Exception {
        if (idGerente > 0 && login != null && senha != null && cargo != null) {
            Gerente gerente = new Gerente();
            gerente.setIdGerente(idGerente);
            gerente.setLogin(login);
            gerente.setSenha(senha);
            gerente.setCargo(cargo);
            new GerenteDAO().updateGerente(gerente);
        } else {
            throw new Exception("Preencha os campos corretamente!");
        }
    }

    // Método para deletar um gerente
    public void deleteGerente(int idGerente) throws Exception {
        if (idGerente > 0) {
            new GerenteDAO().deleteGerente(idGerente);
        } else {
            throw new Exception("ID do Gerente é inválido!");
        }
    }

    // Método para buscar gerente por CPF
    public Gerente getGerenteByCpf(String cpf) throws Exception {
        if (cpf != null && !cpf.isEmpty()) {
            return new GerenteDAO().getGerenteByCpf(cpf);
        } else {
            throw new Exception("CPF é inválido!");
        }
    }
}
