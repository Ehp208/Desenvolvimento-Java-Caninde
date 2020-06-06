package br.com.project.geral.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.PessoaFisica;
import br.com.project.model.classes.PessoaJuridica;
import br.com.repository.interfaces.RepositoryPessoaJuridica;
import br.com.srv.interfaces.SrvPessoaJuridica;

@Controller
public class PessoaJuridicaController extends ImplementacaoCrud<PessoaJuridica> implements InterfaceCrud<PessoaJuridica> {

	private static final long serialVersionUID = 1L;

	@Resource
	private SrvPessoaJuridica srvPessoaJuridica;

	@Resource
	private RepositoryPessoaJuridica repositoryPessoaJuridica;

	public void setSrvPessoaJuridica(SrvPessoaJuridica srvPessoaJuridica) {
		this.srvPessoaJuridica = srvPessoaJuridica;
	}

	public void setRepositoryPessoaJuridica(RepositoryPessoaJuridica repositoryPessoaJuridica) {
		this.repositoryPessoaJuridica = repositoryPessoaJuridica;
	}

	/**
	 * não tenho o campo nome public List<PessoaJuridica> pesquisarPorNome(String
	 * nome) throws Exception { List<PessoaJuridica> list = (List<PessoaJuridica>)
	 * getSession().createQuery("select pf from PessoaJuridica pf where pf.pju_nome
	 * like '%" + nome + "%'").list(); return list; }
	 */

	public List procuraCnpj(String pju_cnpj) throws Exception {
		List<PessoaJuridica> list = (List<PessoaJuridica>) super.findListByQueryDinamica(
				"from PessoaJuridica where pju_cnpj = '" + pju_cnpj + "'");
		return list;
	}
	public boolean existeCnpj(String pju_cnpj, Long pju_id) throws Exception {
		List<PessoaJuridica> list = (List<PessoaJuridica>) super.findListByQueryDinamica(
				"from PessoaJuridica where pju_cnpj = '" + pju_cnpj + "'");
		
		if (list.size() > 0 &&			 
			pju_id != list.get(0).getPju_id() && 
			pju_cnpj.replaceAll("[^0-9]", "").equals(list.get(0).getPju_cnpj().replaceAll("[^0-9]", "")))
     	{
			return true;
		} else {
			return false;
		}
	}

	public boolean existeIe(String pju_ie, Long pju_id) throws Exception {
		List<PessoaJuridica> list = (List<PessoaJuridica>) super.findListByQueryDinamica(
				"from PessoaJuridica where pju_ie = '" + pju_ie + "'");

		if ((list.size() == 0 && pju_id == null)
				|| (list.size() == 0 || (list.size() == 1 && pju_id == list.get(0).getPju_id()))) {
			return false;
		} else {
			return true;
		}
	}
}
