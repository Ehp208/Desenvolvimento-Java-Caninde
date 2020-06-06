package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Agenciador;
import br.com.repository.interfaces.RepositoryAgenciador;
import br.com.srv.interfaces.SrvAgenciador;

@Controller
public class AgenciadorController extends ImplementacaoCrud<Agenciador> implements InterfaceCrud<Agenciador> {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvAgenciador srvAgenciador;
	
	@Resource
	private RepositoryAgenciador repositoryAgenciador;

	public void setSrvAgenciador(SrvAgenciador srvAgenciador) {
		this.srvAgenciador = srvAgenciador;
	}

	public void setRepositoryAgenciador(RepositoryAgenciador repositoryAgenciador) {
		this.repositoryAgenciador = repositoryAgenciador;
	}
	
	public List<SelectItem> getListAgenciador() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Agenciador> agenciadores = super.finListOrderByProperty(Agenciador.class, "age_nome");	
		for (Agenciador l : agenciadores) {			
			list.add(new SelectItem(l, l.getAge_nome()));
		}
		return list;
	}

	public List<Agenciador> pesquisarPorNome(String nome) throws Exception {
		List<Agenciador> list = (List<Agenciador>) super.findListByQueryDinamica("from Agenciador a where retira_acentos(upper(a.age_nome)) = retira_acentos(upper('" + nome + "'))");		
		return list;
	}
	
	public List<Agenciador> pesquisarPorNomeGenerico(String nome) throws Exception {
		List<Agenciador> list = (List<Agenciador>) super.findListByQueryDinamica("Select a.age_nome from Agenciador a where retira_acentos(upper(a.age_nome)) like retira_acentos(upper('%" + nome + "%'))");				
		return list;
	}
 
	public Boolean existeAgenciador(String nome) throws Exception {
		List<Agenciador> list = (List<Agenciador>) super.findListByQueryDinamica("Select l.age_nome from Agenciador l where retira_acentos(upper(l.age_nome)) = retira_acentos(upper('" + nome + "'))");			
		if (list.size() > 0)
     	{ return true; } 
		else { return false; }
	}
 
}
