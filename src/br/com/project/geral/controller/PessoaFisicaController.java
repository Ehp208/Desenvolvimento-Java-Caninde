package br.com.project.geral.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.PessoaFisica;
import br.com.repository.interfaces.RepositoryPessoaFisica;
import br.com.srv.interfaces.SrvPessoaFisica;

@Controller
public class PessoaFisicaController extends ImplementacaoCrud<PessoaFisica> implements InterfaceCrud<PessoaFisica> {

	private static final long serialVersionUID = 1L;

	@Resource
	private SrvPessoaFisica srvPessoaFisica;

	@Resource
	private RepositoryPessoaFisica repositoryPessoaFisica;

	public void setSrvPessoaFisica(SrvPessoaFisica srvPessoaFisica) {
		this.srvPessoaFisica = srvPessoaFisica;
	}

	public void setRepositoryPessoaFisica(RepositoryPessoaFisica repositoryPessoaFisica) {
		this.repositoryPessoaFisica = repositoryPessoaFisica;
	}

	/**
	 * não tenho o campo nome public List<PessoaFisica> pesquisarPorNome(String
	 * nome) throws Exception { List<PessoaFisica> list = (List<PessoaFisica>)
	 * getSession().createQuery("select pf from PessoaFisica pf where pf.pfi_nome
	 * like '%" + nome + "%'").list(); return list; }
	 */

	public boolean existeCpf(String pfi_cpf, Long pfi_id) throws Exception {
		List<PessoaFisica> list = (List<PessoaFisica>) super.findListByQueryDinamica(
				"from PessoaFisica where pfi_cpf = '" + pfi_cpf + "'");
		/**
		 * verifico se pode incluir registro = 0 e id = null
		 * 
		 * verifico se pode alterar o cpf registro = 1 e cpfvelho != cpfnovo
		 * 
		 * verifico se pode alterar qualquer campo registro = 1 e idvelho = idnovo
		 * 
		 *
		 * if((list.size() == 0 && pfi_id == null) || (list.size() == 1 && pfi_cpf ==
		 * list.get(0).getPfi_cpf()) || (list.size() == 0 || (list.size() == 1 && pfi_id
		 * == list.get(0).getPfi_id()))) { return false; } else { return true; }
		 * 

		System.out.println(list.size() == 0 && pfi_id != null);
		System.out.println(list.size() == 1 && pfi_id != list.get(0).getPfi_id()
				&& pfi_cpf.replaceAll("[^0-9]", "").equals(list.get(0).getPfi_cpf().replaceAll("[^0-9]", "")));

		System.out.println("CPF ID é igual a nulo " + (pfi_id == null));
		System.out.println("Registro igual a 1 " + (list.size() == 1));
		System.out.println("Valor do list.size() " + (list.size()));

		if (list.size() > 0 && pfi_id != null) {
			System.out.println("ID antigo é igual ao ID novo " + (pfi_id == list.get(0).getPfi_id()));

			System.out.println("CPF antigo é igual ao CPF novo "
					+ (pfi_cpf.replaceAll("[^0-9]", "").equals(list.get(0).getPfi_cpf().replaceAll("[^0-9]", ""))));

			System.out.println("Quantidade de Registros: " + list.size());
			System.out.println("ID Antigo: " + pfi_id);
			System.out.println("ID Novo: " + list.get(0).getPfi_id());
			System.out.println("CPF Antigo: " + pfi_cpf.replaceAll("[^0-9]", ""));
			System.out.println("CPF Novo: " + list.get(0).getPfi_cpf().replaceAll("[^0-9]", ""));
			System.out.println("Carteira de Identidade Números: " + list.get(0).getPfi_rg().replaceAll("[^0-9]", ""));
			System.out.println(
					"Carteira de Identidade Letras Maiúsculas: " + list.get(0).getPfi_rg().replaceAll("[^A-Z]", ""));

			String cpfDigitado = list.get(0).getPfi_cpf().replaceAll("[^0-9]", "");
			String cpfEnviado = pfi_cpf.replaceAll("[^0-9]", "");

			System.out.println("CPF Digitado: " + cpfDigitado);
			System.out.println("CPF Enviado : " + cpfEnviado);

			System.out.println("CPF Identicos: " + cpfDigitado.equals(cpfEnviado));
		}
*/

		
		if (list.size() > 0 &&			 
			pfi_id != list.get(0).getPfi_id() && 
			pfi_cpf.replaceAll("[^0-9]", "").equals(list.get(0).getPfi_cpf().replaceAll("[^0-9]", "")))
     	{
			return true;
		} else {
			return false;
		}

		/**
		 * return super.findListByQueryDinamica("from PessoaFisica where pfi_cpf = '" +
		 * pfi_cpf + "'").size() > 0;
		 */
	}

	public List procuraCpf(String pfi_cpf) throws Exception {
		List<PessoaFisica> list = (List<PessoaFisica>) super.findListByQueryDinamica(
				"from PessoaFisica where pfi_cpf = '" + pfi_cpf + "'");
		return list;
	}

	public boolean existeRg(String pfi_rg, Long pfi_id) throws Exception {
		List<PessoaFisica> list = (List<PessoaFisica>) super.findListByQueryDinamica(
				"from PessoaFisica where pfi_rg = '" + pfi_rg + "'");
		/**
		 * 
		 * 
		 * System.out.println(list.size()); System.out.println(list.get(0).getPfi_id());
		 * 
		 */

		if ((list.size() == 0 && pfi_id == null)
				|| (list.size() == 0 || (list.size() == 1 && pfi_id == list.get(0).getPfi_id()))) {
			return false;
		} else {
			return true;
		}
		/**
		 * return super.findListByQueryDinamica("from PessoaFisica where pfi_rg = '" +
		 * pfi_rg + "'").size() > 1;
		 */
	}
}
