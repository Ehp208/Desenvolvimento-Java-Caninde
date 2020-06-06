package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.enums.TipoPessoa;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Logradouro;
import br.com.repository.interfaces.RepositoryLogradouro;
import br.com.srv.interfaces.SrvLogradouro;

@Controller
public class LogradouroController extends ImplementacaoCrud<Logradouro> implements InterfaceCrud<Logradouro> {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvLogradouro srvLogradouro;
	
	@Resource
	private RepositoryLogradouro repositoryLogradouro;

	public void setSrvLogradouro(SrvLogradouro srvLogradouro) {
		this.srvLogradouro = srvLogradouro;
	}

	public void setRepositoryLogradouro(RepositoryLogradouro repositoryLogradouro) {
		this.repositoryLogradouro = repositoryLogradouro;
	}
	
	public List<SelectItem> getListLogradouro() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Logradouro> logradouros = super.finListOrderByProperty(Logradouro.class, "log_nome");	
		for (Logradouro l : logradouros) {			
			list.add(new SelectItem(l, l.getLog_nome()));
		}
		return list;
	}

	public List<Logradouro> pesquisarPorNome(String nome) throws Exception {
		List<Logradouro> list = (List<Logradouro>) super.findListByQueryDinamica("from Logradouro l where retira_acentos(upper(l.log_nome)) = retira_acentos(upper('" + nome + "'))");		
		return list;
	}
	
	public List<Logradouro> pesquisarPorNomeGenerico(String nome) throws Exception {
		List<Logradouro> list = (List<Logradouro>) super.findListByQueryDinamica("Select l.log_nome from Logradouro l where retira_acentos(upper(l.log_nome)) like retira_acentos(upper('%" + nome + "%'))");				
		return list;
	}
 
	public Boolean existeLogradouro(String nome) throws Exception {
		List<Logradouro> list = (List<Logradouro>) super.findListByQueryDinamica("Select l.log_nome from Logradouro l where retira_acentos(upper(l.log_nome)) = retira_acentos(upper('" + nome + "'))");			
		if (list.size() > 0)
     	{ return true; } 
		else { return false; }
	}
 
}
