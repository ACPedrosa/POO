package controller;

import java.sql.Date;

import dao.FuncionarioDAO;
import dao.GerenteDAO;
import dao.PessoaDAO;
import model.Funcionario;
import model.Gerente;
import model.Pessoa;

public class GerenteController {

    public void createGerente(int idFuncionario) throws Exception {
        if (idFuncionario > 0) {
        	Gerente gerente = new Gerente(idFuncionario);
            gerente.createGerente(gerente);
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
        	
        	
        	Gerente gerente = new Gerente(funcionario.getIdFuncionario());
            gerente.createGerente(gerente);
        } else {
            throw new Exception("Preencha os campos corretamente!");
        }
    }

    public void updateGerente(String nome, String telefone, String rg, String cpf, Date dataNascimento, String sexo, String profissao, String endereco, String login, String senha, String cargo) throws Exception {
        if (nome != null) {
        	Pessoa pessoa = new Pessoa(nome, telefone, rg, cpf, dataNascimento, sexo, profissao, endereco);
        	pessoa.setIdPessoa(new PessoaDAO().getPessoaByCpf(pessoa.getCpf()).getIdPessoa());
        	pessoa.updatePessoa(pessoa);
        	
        	Funcionario funcionario = new Funcionario(login, senha, cargo);
        	funcionario.setIdFuncionario(new FuncionarioDAO().getFuncionarioByCpf(pessoa.getCpf()).getIdFuncionario());
        	funcionario.updateFuncionario(funcionario);
        	
        	
        	Gerente gerente = new Gerente(funcionario.getIdFuncionario());
            gerente.updateGerente(gerente);
        } else {
             throw new Exception("Preencha os campos corretamente!");
         }
    }

    public void deleteGerente(String cpf) throws Exception {
    	Gerente gerente = new GerenteDAO().getGerenteByCpf(cpf);
        if (gerente.getIdGerente() > 0) {
            new GerenteDAO().deleteGerente(gerente.getIdGerente());
        } else {
            throw new Exception("ID do Gerente é inválido!");
        }
    }

    public Gerente getGerenteByCpf(String cpf) throws Exception {
        if (cpf != null && !cpf.isEmpty()) {
            return new GerenteDAO().getGerenteByCpf(cpf);
        } else {
            throw new Exception("CPF é inválido!");
        }
    }
    
    
}
