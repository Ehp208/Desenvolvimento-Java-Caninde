package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Cidade;
import br.com.project.model.classes.Cidade;
import br.com.project.model.classes.Logradouro;
import br.com.repository.interfaces.RepositoryCidade;
import br.com.srv.interfaces.SrvCidade;

@Controller
public class CidadeController extends ImplementacaoCrud<Cidade> implements InterfaceCrud<Cidade> {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvCidade srvCidade;
	
	@Resource
	private RepositoryCidade repositoryCidade;

	public void setSrvCidade(SrvCidade srvCidade) {
		this.srvCidade = srvCidade;
	}

	public void setRepositoryCidade(RepositoryCidade repositoryCidade) {
		this.repositoryCidade = repositoryCidade;
	}
	
	public List<SelectItem> getListCidade() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Cidade> cidades = super.finListOrderByProperty(Cidade.class, "cid_nome");
		for (Cidade c : cidades) {
			list.add(new SelectItem(c, c.getCid_nome()));
		}
		return list;
	}

	public List<Cidade> pesquisarPorNome(String nome) throws Exception {
		List<Cidade> list = (List<Cidade>) getSession().createQuery("select c from Cidade c where c.cid_nome like '%" + nome + "%'").list();
		return list;
	}	
}
