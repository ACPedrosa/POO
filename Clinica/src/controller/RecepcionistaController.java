package controller;

import model.Recepcionista;
import model.Funcionario;
import model.Pessoa;

import java.sql.Date;

import dao.FuncionarioDAO;
import dao.PessoaDAO;
import dao.RecepcionistaDAO;

public class RecepcionistaController {

    public void createSimpleRecepcionista(int idFuncionario) throws Exception {
        if (idFuncionario > 0) {
            Recepcionista recepcionista = new Recepcionista(idFuncionario);
            recepcionista.createRecepcionista(recepcionista);
        } else {
            throw new Exception("Preencha os campos corretamente!");
        }
    }
    
    public void createFullRecepcionista(String nome, String telefone, String rg, String cpf, Date dataNascimento, String sexo, String profissao, String endereco, String login, String senha, String cargo) throws Exception {
        if (nome != null) {
        	Pessoa pessoa = new Pessoa(nome, telefone, rg, cpf, dataNascimento, sexo, profissao, endereco);
        	pessoa.createPessoa(pessoa);
        	pessoa = new PessoaDAO().getPessoaByCpf(pessoa.getCpf());
        	
        	Funcionario funcionario = new Funcionario(login, senha, cargo);
        	funcionario.createFuncionario(funcionario, pessoa.getIdPessoa());
        	funcionario = new FuncionarioDAO().getFuncionarioByCpf(pessoa.getCpf());
        	
        	
        	Recepcionista recepcionista = new Recepcionista(funcionario.getIdFuncionario());
            
            recepcionista.createRecepcionista(recepcionista);
        } else {
            throw new Exception("Preencha os campos corretamente!");
        }
    }

    public void updateRecepcionista(int idFuncionario) throws Exception {
        if (idFuncionario > 0) {
        	Recepcionista recepcionista = new Recepcionista(idFuncionario);
        	recepcionista.updateRecepcionista(recepcionista);
        } else {
            throw new Exception("Preencha os campos corretamente!");
        }
    }

    public void deleteRecepcionista(int idRecepcionista) throws Exception {
        if (idRecepcionista > 0) {
            Recepcionista recepcionista = new Recepcionista();
            recepcionista.deleteRecepcionista(idRecepcionista);
        } else {
            throw new Exception("ID do Recepcionista é inválido!");
        }
    }

    public Recepcionista getRecepcionistaByCpf(String cpf) throws Exception {
        if (cpf != null && !cpf.isEmpty()) {
        	Recepcionista recepcionista = new RecepcionistaDAO().getRecepcionistaByCpf(cpf);
            return recepcionista;
        } else {
            throw new Exception("CPF é inválido!");
        }
    }

    // Implement other methods as needed for fetching by CPF, etc.
}
