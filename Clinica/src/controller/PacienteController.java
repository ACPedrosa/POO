package controller;

import dao.PacienteDAO;
import model.Paciente;
import model.Pessoa;

public class PacienteController {
	public void createPaciente(Pessoa pessoa, String etnia, String tipoSanguineo, boolean fatorRh, double peso,
			double altura, boolean doador, boolean fumante, String doencas, String limitacoes) throws Exception {
		if (pessoa.getIdPessoa() > 0 && etnia != null && tipoSanguineo != null) {
			Paciente paciente = new Paciente(etnia, tipoSanguineo, fatorRh, peso, altura, doador, fumante, doencas,
					limitacoes);
			paciente.createPaciente(paciente, pessoa.getIdPessoa());
		} else {
			throw new Exception("Preencha os campos corretamente!");
		}
	}

	public void updatePaciente(String etnia, String tipoSanguineo, boolean fatorRh, double peso, double altura,
			boolean doador, boolean fumante, String doencas, String limitacoes) throws Exception {
		if (etnia != null && tipoSanguineo != null) {
			Paciente paciente = new Paciente(etnia, tipoSanguineo, fatorRh, peso, altura, doador, fumante, doencas,
					limitacoes);
			paciente.updatePaciente(paciente);
		} else {
			throw new Exception("Preencha os campos corretamente!");
		}
	}

	public void deletePaciente(int idPaciente) throws Exception {
		if (idPaciente > 0) {
			Paciente paciente = new Paciente();
			paciente.deletePaciente(idPaciente);
		} else {
			throw new Exception("ID do Paciente é inválido!");
		}
	}

	public Paciente getPacienteByCpf(String cpf) throws Exception {
		if (cpf != null && cpf.length() > 0) {
			Paciente paciente = new PacienteDAO().getPacienteByCpf(cpf);
			return paciente;
		} else {
			throw new Exception("ID do Paciente é inválido!");
		}
	}

}