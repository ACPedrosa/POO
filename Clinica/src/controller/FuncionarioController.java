package controller;

import dao.FuncionarioDAO;
import model.Funcionario;
import model.Pessoa;

public class FuncionarioController {
    
    public void createFuncionario(Pessoa pessoa, String login, String senha, String cargo) throws Exception {
        if (pessoa.getIdPessoa() > 0 && login != null && senha != null && cargo != null) {
            Funcionario funcionario = new Funcionario(login, senha, cargo);
            funcionario.createFuncionario(funcionario, pessoa.getIdPessoa());
        } else {
            throw new Exception("Preencha os campos corretamente!");
        }
    }

    public void updateFuncionario(int idFuncionario, String login, String senha, String cargo) throws Exception {
        if (idFuncionario > 0 && login != null && senha != null && cargo != null) {
            Funcionario funcionario = new Funcionario();
            funcionario.updateFuncionario(funcionario);
        } else {
            throw new Exception("Preencha os campos corretamente!");
        }
    }

    public void deleteFuncionario(int idFuncionario) throws Exception {
        if (idFuncionario > 0) {
            Funcionario funcionario = new Funcionario();
            funcionario.deleteFuncionario(idFuncionario);
        } else {
            throw new Exception("ID do Funcionário é inválido!");
        }
    }

    public Funcionario getFuncionarioByCpf(String cpf) throws Exception {
        if (cpf != null && !cpf.isEmpty()) {
            Funcionario funcionario = new FuncionarioDAO().getFuncionarioByCpf(cpf);
            return funcionario;
        } else {
            throw new Exception("CPF é inválido!");
        }
    }
}
