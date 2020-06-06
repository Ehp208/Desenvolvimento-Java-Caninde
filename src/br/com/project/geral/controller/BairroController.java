package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Logradouro;
import br.com.repository.interfaces.RepositoryBairro;
import br.com.srv.interfaces.SrvBairro;

@Controller
public class BairroController extends ImplementacaoCrud<Bairro> implements InterfaceCrud<Bairro> {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvBairro srvBairro;
	
	@Resource
	private RepositoryBairro repositoryBairro;

	public void setSrvBairro(SrvBairro srvBairro) {
		this.srvBairro = srvBairro;
	}

	public void setRepositoryBairro(RepositoryBairro repositoryBairro) {
		this.repositoryBairro = repositoryBairro;
	}
	
	public List<SelectItem> getListBairro() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Bairro> bairros = super.finListOrderByProperty(Bairro.class, "bai_nome");
		for (Bairro b : bairros) {
			list.add(new SelectItem(b, b.getBai_nome()));
		}
		return list;
	}
	
	public List<Bairro> pesquisarPorNome(String nome) throws Exception {
		List<Bairro> list = (List<Bairro>) super.findListByQueryDinamica("from Bairro b where retira_acentos(upper(b.bai_nome)) = retira_acentos(upper('" + nome + "'))");		
		return list;
	}

	public List pesquisarPorNomeGenerico(String nome) throws Exception {	
		List<Bairro> list = (List<Bairro>) super.findListByQueryDinamica("Select b.bai_nome from Bairro b where retira_acentos(upper(b.bai_nome)) like retira_acentos(upper('%" + nome + "%'))");	
		return list;
	}

	public Boolean existeBairro(String nome) throws Exception {
		List<Bairro> list = (List<Bairro>) super.findListByQueryDinamica("Select b.bai_nome from Bairro b where retira_acentos(upper(b.bai_nome)) = retira_acentos(upper('" + nome + "'))");			
		if (list.size() > 0)
     	{ return true; } 
		else { return false; }
	}

}
